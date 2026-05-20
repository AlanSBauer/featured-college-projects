package service;

import adapter.PlaywrightScraper;
import domain.Price;
import domain.Product;
import domain.ProductLink;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CrawlerService {

    private final ProductService productService;
    private final PlaywrightScraper scraper;

    public CrawlerService(ProductService productService) {
        this.productService = productService;
        this.scraper = new PlaywrightScraper();
    }

    public void runCrawler() {
        System.out.println("--- Iniciando Crawler ---");
        List<Product> products = productService.getAllProducts();

        for (Product product : products) {
            System.out.println("Verificando produto: " + product.getName() + " (SKU: " + product.getSku() + ")");
            List<ProductLink> links = product.getLinks();

            if (links == null || links.isEmpty()) {
                System.out.println("Produto sem links cadastrados.");
                continue;
            }

            Float lowestPrice = null;
            String bestStore = null;

            for (ProductLink link : links) {
                Float price = scraper.getPrice(link.getStoreName(), link.getUrl());
                if (price != null) {
                    System.out.println("  " + link.getStoreName() + ": R$ "
                            + String.format("%.2f", price));
                    if (lowestPrice == null || price < lowestPrice) {
                        lowestPrice = price;
                        bestStore = link.getStoreName();
                    }
                }
            }

            if (lowestPrice != null) {
                System.out.println("Menor preco encontrado para " + product.getName() + ": " + lowestPrice + " na loja " + bestStore);

                if (shouldAddHistory(product, lowestPrice, bestStore)) {
                    Price newHistory = new Price(lowestPrice, new Date(), bestStore);
                    product.addHistoricalPrice(newHistory);
                    System.out.println("Historico atualizado (preco ou loja alterados).");
                } else {
                    System.out.println("Preco e loja inalterados; historico nao duplicado.");
                }

                product.setPrice(lowestPrice);
                productService.create(product);
            } else {
                System.out.println("Nao foi possivel encontrar precos para este produto no momento.");
            }
            System.out.println("-------------------------");
        }

        System.out.println("--- Crawler Finalizado ---");
    }

    /**
     * Adiciona histórico apenas se o menor preço ou a loja vencedora mudaram
     * em relação ao último registro salvo.
     */
    private boolean shouldAddHistory(Product product, Float lowestPrice, String bestStore) {
        Price latest = getLatestHistory(product);
        if (latest == null) {
            return true;
        }
        boolean priceChanged = !samePrice(latest.getPrice(), lowestPrice);
        boolean storeChanged = !sameStore(latest.getStoreName(), bestStore);
        return priceChanged || storeChanged;
    }

    private Price getLatestHistory(Product product) {
        List<Price> history = product.getHistoricalPrice();
        if (history == null || history.isEmpty()) {
            return null;
        }
        Price latest = history.get(0);
        for (Price entry : history) {
            if (entry.getDate() == null) {
                continue;
            }
            if (latest.getDate() == null || entry.getDate().after(latest.getDate())) {
                latest = entry;
            }
        }
        return latest;
    }

    private boolean samePrice(Float a, Float b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return Math.abs(a - b) < 0.01f;
    }

    private boolean sameStore(String a, String b) {
        return Objects.equals(
                a == null ? null : a.trim(),
                b == null ? null : b.trim()
        );
    }

}
