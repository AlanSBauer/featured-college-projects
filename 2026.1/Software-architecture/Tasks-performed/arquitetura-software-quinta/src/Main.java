import domain.Product;

void main() {
    Product product = new Product();

    product.setName("Celular");
    product.setSku("TSH-BLK-002-SM");
    product.setPrice(new BigDecimal("1000"));
    product.setPrice(new BigDecimal("999"));

    Product product2 = new Product("Notebook", "SAH-HAW-030-SW", new BigDecimal("3200"));
    Product product3 = new Product("Tablet", "PQH-WSF-345-IO", new BigDecimal("4200"));

    product2.setPrice(new BigDecimal("4000"));
    product2.setPrice(new BigDecimal("33000"));
    product3.setPrice(new BigDecimal("3100"));
    product3.setPrice(new BigDecimal("3400"));

    System.out.println(product.toString());
    System.out.println(product2.toString());
    System.out.println(product3.toString());
}
