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
   * v8: 2 lojas por produto (Kabum + Amazon), precos via CURL nas duas.
   * Mercado Livre bloqueia acesso automatico; Amazon responde de forma estavel.
   */
  private static final int CATALOG_VERSION = 8;

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
      System.out.println("Nota: precos automaticos via CURL (Kabum + Amazon).\n");

      System.out.println("Cadastrando: PlayStation 5");
      Product ps5 = new Product("PS5-001", "PlayStation 5", 0f);
      ps5.addLink(new ProductLink("Kabum",
          "https://www.kabum.com.br/produto/939944/console-playstation-5-slim-digital-edition-825gb-usb-hdmi-branco"));
      ps5.addLink(new ProductLink("Amazon",
          "https://www.amazon.com.br/dp/B0CL61F39H"));
      productService.create(ps5);
      System.out.println("PlayStation 5 -> Kabum + Amazon\n");

      System.out.println("Cadastrando: Xbox Series X");
      Product xbox = new Product("XBOX-001", "Xbox Series X", 0f);
      xbox.addLink(new ProductLink("Kabum",
          "https://www.kabum.com.br/produto/678178/console-xbox-series-x-1tb-microsoft-bivolt-branco"));
      xbox.addLink(new ProductLink("Amazon",
          "https://www.amazon.com.br/dp/B08H75RTZ8"));
      productService.create(xbox);
      System.out.println("Xbox Series X -> Kabum + Amazon\n");

      System.out.println("Cadastrando: Monitor LG 27 polegadas");
      Product monitor = new Product("MON-001", "Monitor LG 27 polegadas", 0f);
      monitor.addLink(new ProductLink("Kabum",
          "https://www.kabum.com.br/produto/323895/monitor-gamer-lg-27-full-hd-ips-hdmi-e-vesa-freesync-ajuste-de-angulo-bordas-finas-preto-27mp400-b"));
      monitor.addLink(new ProductLink("Amazon",
          "https://www.amazon.com.br/dp/B09JT2FRXF"));
      productService.create(monitor);
      System.out.println("Monitor LG -> Kabum + Amazon\n");

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
        System.out.println("Menor preço atual (Kabum + Amazon): R$ "
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
