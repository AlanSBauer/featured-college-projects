import domain.Product;
import domain.ProductLink;
import domain.Price;
import service.CrawlerService;
import service.ProductService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

  /**
   * v7: 2 links Kabum (preco via CURL) + ML (link correto, consulta manual).
   * Mercado Livre bloqueia CURL; comparacao automatica entre anuncios Kabum.
   */
  private static final int CATALOG_VERSION = 7;

  public static void main(String[] args) {
    quietHibernateLogs();

    System.out.println("====================================================");
    System.out.println("     SISTEMA DE COMPARACAO DE PRECOS");
    System.out.println("====================================================\n");

    ensureCatalogVersion();

    ProductService productService = new ProductService();
    CrawlerService crawlerService = new CrawlerService(productService);

    List<Product> existingProducts = productService.getAllProducts();

    if (existingProducts.isEmpty()) {
      System.out.println("Banco de dados vazio. Cadastrando produtos de exemplo...\n");
      System.out.println("Nota: precos automaticos via CURL na Kabum.");
      System.out.println("Links do Mercado Livre sao para conferencia no navegador.\n");

      System.out.println("Cadastrando: PlayStation 5");
      Product ps5 = new Product("PS5-001", "PlayStation 5", 0f);
      ps5.addLink(new ProductLink("Kabum",
          "https://www.kabum.com.br/produto/939944/console-playstation-5-slim-digital-edition-825gb-usb-hdmi-branco"));
      ps5.addLink(new ProductLink("Kabum Marketplace",
          "https://www.kabum.com.br/produto/542929/console-playstation-5-slim-ssd-1tb-edicao-digital-branco-2-jogos-digitais-1000038914"));
      ps5.addLink(new ProductLink("Mercado Livre",
          "https://produto.mercadolivre.com.br/MLB-4901851404-console-playstation-5-slim-edico-digital-sony-1-tb-_JM"));
      productService.create(ps5);
      System.out.println("PlayStation 5 -> 2x Kabum (crawler) + ML (manual)\n");

      System.out.println("Cadastrando: Xbox Series X");
      Product xbox = new Product("XBOX-001", "Xbox Series X", 0f);
      xbox.addLink(new ProductLink("Kabum",
          "https://www.kabum.com.br/produto/678178/console-xbox-series-x-1tb-microsoft-bivolt-branco"));
      xbox.addLink(new ProductLink("Kabum Marketplace",
          "https://www.kabum.com.br/produto/128560/console-microsoft-xbox-series-x-1tb-preto-rrt-00006"));
      xbox.addLink(new ProductLink("Mercado Livre",
          "https://www.mercadolivre.com.br/microsoft-xbox-series-x-1tb/p/MLB16160759"));
      productService.create(xbox);
      System.out.println("Xbox Series X -> 2x Kabum (crawler) + ML (manual)\n");

      System.out.println("Cadastrando: Monitor LG 27 polegadas");
      Product monitor = new Product("MON-001", "Monitor LG 27 polegadas", 0f);
      monitor.addLink(new ProductLink("Kabum",
          "https://www.kabum.com.br/produto/323895/monitor-gamer-lg-27-full-hd-ips-hdmi-e-vesa-freesync-ajuste-de-angulo-bordas-finas-preto-27mp400-b"));
      monitor.addLink(new ProductLink("Kabum Marketplace",
          "https://www.kabum.com.br/produto/299641/monitor-lg-27-full-hd-75hz-5ms-ps-hdmi-preto-27mp400-b-awzm"));
      monitor.addLink(new ProductLink("Mercado Livre",
          "https://produto.mercadolivre.com.br/MLB-18645884-monitor-gamer-lg-27mp400-27-cor-preto-100v240v-_JM"));
      productService.create(monitor);
      System.out.println("Monitor LG -> 2x Kabum (crawler) + ML (manual)\n");

      markCatalogSeeded();
      System.out.println("Todos os produtos foram cadastrados com sucesso!");

    } else {
      System.out.println("Produtos ja cadastrados. Atualizando precos via crawler...\n");
    }

    System.out.println("====================================================");
    crawlerService.runCrawler();
    System.out.println("====================================================\n");

    System.out.println("====================================================");
    System.out.println("              RESUMO FINAL DOS PRODUTOS");
    System.out.println("====================================================\n");

    List<Product> allProducts = productService.getAllProducts();
    for (Product product : allProducts) {
      System.out.println("Produto: " + product.getName());
      System.out.println("SKU: " + product.getSku());

      if (product.getPrice() != null && product.getPrice() > 0) {
        System.out.println("Menor preço atual (via CURL/Kabum): R$ "
            + String.format("%.2f", product.getPrice()));
      } else {
        System.out.println("Menor preço atual: (nenhum preço coletado nesta execução)");
      }

      System.out.println("Links cadastrados:");
      for (ProductLink link : product.getLinks()) {
        System.out.println("  - " + link.getStoreName() + ": " + link.getUrl());
      }

      if (!product.getHistoricalPrice().isEmpty()) {
        System.out.println("Histórico de preços:");
        for (Price priceHistory : product.getHistoricalPrice()) {
          System.out.println("  - R$ " + String.format("%.2f", priceHistory.getPrice()) +
              " na " + priceHistory.getStoreName() +
              " em " + priceHistory.getDate());
        }
      }

      System.out.println();
    }

    System.out.println("====================================================");
    System.out.println("Sistema finalizado com sucesso!");
    System.out.println("====================================================");
  }

  private static void quietHibernateLogs() {
    Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
    Logger.getLogger("org.hibernate.orm").setLevel(Level.WARNING);
  }

  private static void ensureCatalogVersion() {
    File marker = catalogMarkerFile();
    File db = new File("products.db");

    if (db.exists() && !marker.exists()) {
      removeOldCatalogMarkers(marker.getName());
      if (db.delete()) {
        System.out.println("Catalogo v" + CATALOG_VERSION + ": banco antigo removido.\n");
      } else {
        System.err.println("ERRO: feche o programa e apague manualmente o arquivo products.db");
        System.exit(1);
      }
    }
  }

  private static void removeOldCatalogMarkers(String keepFileName) {
    File[] markers = new File(".").listFiles((dir, name) ->
        name.startsWith(".catalog-v") && !name.equals(keepFileName));
    if (markers != null) {
      for (File old : markers) {
        old.delete();
      }
    }
  }

  private static void markCatalogSeeded() {
    try {
      catalogMarkerFile().createNewFile();
    } catch (IOException e) {
      System.err.println("Aviso: nao foi possivel gravar marcador de catalogo: " + e.getMessage());
    }
  }

  private static File catalogMarkerFile() {
    return new File(".catalog-v" + CATALOG_VERSION);
  }
}
