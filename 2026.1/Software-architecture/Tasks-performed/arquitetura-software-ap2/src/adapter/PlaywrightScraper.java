package adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Adaptador para Web Scraping usando CURL (requisicao HTTP, sem navegador).
 */
public class PlaywrightScraper {

    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                    + "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    private static final String CURL_COMMAND = resolveCurlCommand();

    private static final Pattern BRL_PRICE = Pattern.compile(
            "R\\$\\s*(\\d{1,3}(?:\\.\\d{3})*(?:,\\d{2})?|\\d+(?:,\\d{2})?)",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern JSON_LD_PRICE = Pattern.compile(
            "\"price\"\\s*:\\s*\"?(\\d+(?:[.,]\\d+)?)\"?",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern JSON_LD_OFFER = Pattern.compile(
            "\"offers\"\\s*:\\s*\\{[\\s\\S]*?\"availability\"\\s*:\\s*\"([^\"]+)\"",
            Pattern.CASE_INSENSITIVE
    );

    private static String resolveCurlCommand() {
        String os = System.getProperty("os.name", "").toLowerCase();
        return os.contains("win") ? "curl.exe" : "curl";
    }

    public Float getPrice(String storeName, String url) {
        if (storeName == null || storeName.isBlank() || url == null || url.isBlank()) {
            System.err.println("Loja ou URL invalida para busca de preco.");
            return null;
        }

        System.out.println("Buscando preco em " + storeName + " -> " + url);

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    CURL_COMMAND,
                    "-s",
                    "-L",
                    "--compressed",
                    "-A",
                    USER_AGENT,
                    "-H",
                    "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
                    "-H",
                    "Accept-Language: pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7",
                    url
            );
            pb.redirectErrorStream(true);
            Process process = pb.start();

            StringBuilder htmlContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("CURL retornou codigo de erro: " + exitCode);
                return null;
            }

            String html = htmlContent.toString();
            if (html.isBlank()) {
                System.out.println("Resposta HTML vazia.");
                return null;
            }

            if (isAntiBotPage(html)) {
                System.out.println("Loja bloqueou acesso automatico (captcha/403).");
                return null;
            }

            if (!isProductInStock(html)) {
                System.out.println("Produto sem estoque nesta loja (nao sera usado na comparacao).");
                return null;
            }

            if (storeName != null && storeName.equalsIgnoreCase("Amazon") && isAmazonUnavailable(html)) {
                System.out.println("Produto indisponivel na Amazon no momento.");
                return null;
            }

            String priceText = extractPrice(storeName, html);

            if (priceText != null && !priceText.isEmpty()) {
                System.out.println("Preco bruto encontrado: " + priceText);
                return parsePriceText(priceText);
            }

            System.out.println("Nenhum preco no HTML (" + html.length()
                    + " bytes). Conteudo provavelmente carregado via JavaScript.");

        } catch (Exception e) {
            System.err.println("Erro ao executar CURL para " + storeName + " (" + url + "): " + e.getMessage());
        }

        return null;
    }

    private Float parsePriceText(String priceText) {
        try {
            String normalized = priceText.replaceAll("[^0-9,.]", "");
            if (normalized.isEmpty()) {
                return null;
            }

            int lastComma = normalized.lastIndexOf(',');
            int lastDot = normalized.lastIndexOf('.');

            if (lastComma > lastDot) {
                normalized = normalized.replace(".", "").replace(",", ".");
            } else if (lastDot > lastComma) {
                normalized = normalized.replace(",", "");
            } else if (lastComma >= 0) {
                normalized = normalized.replace(",", ".");
            }

            float value = Float.parseFloat(normalized);
            return value > 0f ? value : null;
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter preco '" + priceText + "': " + e.getMessage());
            return null;
        }
    }

    private boolean isAntiBotPage(String html) {
        return html.contains("verifyChallenge")
                || html.contains("micro-landing-container")
                || html.contains("Não é possível acessar a página")
                || html.contains("Nao e possivel acessar a pagina")
                || html.contains("bot detection")
                || html.contains("Enter the characters you see below")
                || html.contains("Type the characters you see in this image");
    }

    private boolean isAmazonUnavailable(String html) {
        return html.contains("Currently unavailable")
                || html.contains("Não disponível.")
                || html.contains("Nao disponivel.");
    }

    /** Verifica disponibilidade no HTML (JSON-LD da Kabum e textos comuns). */
    private boolean isProductInStock(String html) {
        Matcher offer = JSON_LD_OFFER.matcher(html);
        if (offer.find()) {
            return offer.group(1).contains("InStock");
        }

        if (html.contains("schema.org/OutOfStock") && !html.contains("schema.org/InStock")) {
            return false;
        }

        if (Pattern.compile(">\\s*Esgotado\\s*<", Pattern.CASE_INSENSITIVE).matcher(html).find()) {
            return false;
        }

        if (Pattern.compile("produto\\s+esgotado", Pattern.CASE_INSENSITIVE).matcher(html).find()) {
            return false;
        }

        return true;
    }

    private String extractPrice(String storeName, String html) {
        try {
            String offerPrice = extractInStockOfferPrice(html);
            if (offerPrice != null) {
                return offerPrice;
            }

            if (storeName.equalsIgnoreCase("Kabum")) {
                String price = findFirstMatch(html, Pattern.compile(
                        "finalPrice\\s*[:=]\\s*['\"]?(\\d{1,3}(?:\\.\\d{3})*(?:,\\d{2})?|\\d+(?:,\\d{2})?)",
                        Pattern.CASE_INSENSITIVE
                ));
                if (price != null) {
                    return price;
                }
            } else if (storeName.equalsIgnoreCase("Amazon")) {
                String price = findFirstMatch(html, Pattern.compile(
                        "customerVisiblePrice\\]\\[amount\\]\"\\s+value=\"(\\d+(?:\\.\\d+)?)\"",
                        Pattern.CASE_INSENSITIVE
                ));
                if (price != null) {
                    return price;
                }
                price = findFirstMatch(html, Pattern.compile(
                        "<span class=\"a-offscreen\">\\s*R\\$\\s*([\\d.,]+)\\s*</span>",
                        Pattern.CASE_INSENSITIVE
                ));
                if (price != null) {
                    return price;
                }
            } else if (storeName.equalsIgnoreCase("Magazine Luiza")
                    || storeName.equalsIgnoreCase("Magalu")) {
                String price = findFirstMatch(html, Pattern.compile(
                        "property=[\"']product:price:amount[\"'][^>]+content=[\"'](\\d+(?:\\.\\d+)?)[\"']",
                        Pattern.CASE_INSENSITIVE
                ));
                if (price != null) {
                    return price;
                }
                price = findFirstMatch(html, Pattern.compile(
                        "content=[\"'](\\d+(?:\\.\\d+)?)[\"'][^>]+property=[\"']product:price:amount[\"']",
                        Pattern.CASE_INSENSITIVE
                ));
                if (price != null) {
                    return price;
                }
            }

            return findFirstMatch(html, BRL_PRICE);

        } catch (Exception e) {
            System.err.println("Erro ao parsear preco para " + storeName + ": " + e.getMessage());
        }

        return null;
    }

    /**
     * Preço do bloco JSON-LD "offers" apenas quando InStock (evita parcela/frete/outros).
     */
    private String extractInStockOfferPrice(String html) {
        Pattern priceThenAvailability = Pattern.compile(
                "\"offers\"\\s*:\\s*\\{[\\s\\S]*?\"price\"\\s*:\\s*\"?(\\d+(?:[.,]\\d+)?)\"?\\s*,\\s*[\\s\\S]*?"
                        + "\"availability\"\\s*:\\s*\"([^\"]+)\"",
                Pattern.CASE_INSENSITIVE
        );
        Matcher matcher = priceThenAvailability.matcher(html);
        if (matcher.find() && matcher.group(2).contains("InStock")) {
            return matcher.group(1);
        }

        Pattern availabilityThenPrice = Pattern.compile(
                "\"offers\"\\s*:\\s*\\{[\\s\\S]*?\"availability\"\\s*:\\s*\"([^\"]+)\"[\\s\\S]*?"
                        + "\"price\"\\s*:\\s*\"?(\\d+(?:[.,]\\d+)?)\"?",
                Pattern.CASE_INSENSITIVE
        );
        matcher = availabilityThenPrice.matcher(html);
        if (matcher.find() && matcher.group(1).contains("InStock")) {
            return matcher.group(2);
        }

        return null;
    }

    private String findFirstMatch(String html, Pattern pattern) {
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
