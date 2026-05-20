/**
 * EXEMPLOS DE DADOS - Sistema de Comparação de Preços
 * 
 * Este arquivo demonstra a estrutura de dados conforme especificado na atividade
 */

// ============================================================
// EXEMPLO 1: Estrutura de Produto com Múltiplos Links
// ============================================================

{
  "produto": {
    "uuid": "550e8400-e29b-41d4-a716-446655440000",
    "sku": "PS5-001",
    "nome": "PlayStation 5",
    "precoAtual": 3699.00,
    "dataPrecoCurr": "2026-05-19T14:27:49-03:00",
    "links": [
      {
        "uuid": "550e8400-e29b-41d4-a716-446655440001",
        "loja": "Amazon",
        "url": "https://www.amazon.com.br/Console-PlayStation-5-Edicao-Digital/dp/B0CJ5T9PDC/"
      },
      {
        "uuid": "550e8400-e29b-41d4-a716-446655440002",
        "loja": "Kabum",
        "url": "https://www.kabum.com.br/produto/120934/console-playstation-5-slim-2tb-cor-branco-sony-ps5"
      }
    ],
    "historicoDePrecosEncontrados": [
      {
        "uuid": "550e8400-e29b-41d4-a716-446655440010",
        "preco": 3799.00,
        "loja": "Amazon",
        "data": "2026-05-19T14:27:48-03:00"
      },
      {
        "uuid": "550e8400-e29b-41d4-a716-446655440011",
        "preco": 3699.00,
        "loja": "Kabum",
        "data": "2026-05-19T14:27:49-03:00"
      }
    ]
  }
}

// ============================================================
// EXEMPLO 2: Funcionamento do Crawler
// ============================================================

ENTRADA:
Produtos no banco de dados:
- PlayStation 5 (2 links: Amazon, Kabum)
- Xbox Series X (2 links: Mercado Livre, Casas Bahia)
- Monitor LG 27" (2 links: Amazon, Kabum)

EXECUÇÃO DO CRAWLER:

1. Para PlayStation 5:
   - Link 1 (Amazon): R$ 3.799,00
   - Link 2 (Kabum): R$ 3.699,00
   - Menor preço: R$ 3.699,00 (Kabum)
   - Salva no histórico: { preco: 3699.00, loja: "Kabum", data: "2026-05-19T14:27:49" }

2. Para Xbox Series X:
   - Link 1 (Mercado Livre): R$ 4.500,00
   - Link 2 (Casas Bahia): R$ 4.299,00
   - Menor preço: R$ 4.299,00 (Casas Bahia)
   - Salva no histórico: { preco: 4299.00, loja: "Casas Bahia", data: "2026-05-19T14:28:02" }

3. Para Monitor LG 27":
   - Link 1 (Amazon): R$ 1.299,00
   - Link 2 (Kabum): R$ 1.199,00
   - Menor preço: R$ 1.199,00 (Kabum)
   - Salva no histórico: { preco: 1199.00, loja: "Kabum", data: "2026-05-19T14:28:15" }

SAÍDA:
Resumo de produtos com menor preço e loja:
- PlayStation 5: R$ 3.699,00 (Kabum)
- Xbox Series X: R$ 4.299,00 (Casas Bahia)
- Monitor LG 27": R$ 1.199,00 (Kabum)

// ============================================================
// EXEMPLO 3: Histórico de Preços Ao Longo do Tempo
// ============================================================

{
  "produto": "PlayStation 5",
  "historico": [
    {
      "preco": 3899.00,
      "loja": "Amazon",
      "data": "2026-05-17"
    },
    {
      "preco": 3799.00,
      "loja": "Kabum",
      "data": "2026-05-17"
    },
    {
      "preco": 3799.00,
      "loja": "Amazon",
      "data": "2026-05-18"
    },
    {
      "preco": 3799.00,
      "loja": "Kabum",
      "data": "2026-05-18"
    },
    {
      "preco": 3799.00,
      "loja": "Amazon",
      "data": "2026-05-19"
    },
    {
      "preco": 3699.00,
      "loja": "Kabum",
      "data": "2026-05-19"
    }
  ],
  "menorPrecoAtual": 3699.00,
  "lojaComMenorPreco": "Kabum",
  "dataDoMenorPreco": "2026-05-19"
}

// ============================================================
// EXEMPLO 4: Comparação de Preços (Snapshot de um momento)
// ============================================================

PlayStation 5:
┌─────────────┬─────────┬────────────────────────────────────────────┐
│ Loja        │ Preço   │ URL                                        │
├─────────────┼─────────┼────────────────────────────────────────────┤
│ Amazon      │ 3799.00 │ amazon.com.br/Console-PlayStation-5/...   │
│ Kabum       │ 3699.00 │ kabum.com.br/produto/120934/console-...   │
├─────────────┼─────────┼────────────────────────────────────────────┤
│ Menor preço │ 3699.00 │ Kabum                                      │
└─────────────┴─────────┴────────────────────────────────────────────┘

Xbox Series X:
┌─────────────────┬─────────┬────────────────────────────────────────────┐
│ Loja            │ Preço   │ URL                                        │
├─────────────────┼─────────┼────────────────────────────────────────────┤
│ Mercado Livre   │ 4500.00 │ mercadolivre.com.br/MLB-2834769636/...   │
│ Casas Bahia     │ 4299.00 │ casasbahia.com.br/console-xbox-...       │
├─────────────────┼─────────┼────────────────────────────────────────────┤
│ Menor preço     │ 4299.00 │ Casas Bahia                               │
└─────────────────┴─────────┴────────────────────────────────────────────┘

// ============================================================
// EXEMPLO 5: Mapeamento Java ↔ Banco de Dados
// ============================================================

TABELA: product
┌──────────────┬──────────────────────────────────────────┬────────┐
│ uuid         │ sku      │ name            │ price  │ date_price │
├──────────────┼──────────┼─────────────────┼────────┼────────────┤
│ uuid123      │ PS5-001  │ PlayStation 5   │ 3699.0 │ 2026-05-19 │
│ uuid456      │ XBOX-001 │ Xbox Series X   │ 4299.0 │ 2026-05-19 │
│ uuid789      │ MON-001  │ Monitor LG 27"  │ 1199.0 │ 2026-05-19 │
└──────────────┴──────────┴─────────────────┴────────┴────────────┘

TABELA: product_link
┌──────────────┬──────────────┬────────────┬────────────────────────────────┐
│ uuid         │ product_uuid │ store_name │ url                            │
├──────────────┼──────────────┼────────────┼────────────────────────────────┤
│ uuid101      │ uuid123      │ Amazon     │ amazon.com.br/Console-PS5/...  │
│ uuid102      │ uuid123      │ Kabum      │ kabum.com.br/produto/120934/.. │
│ uuid103      │ uuid456      │ Mercado... │ mercadolivre.com.br/MLB-28347..│
│ uuid104      │ uuid456      │ Casas...   │ casasbahia.com.br/console-xbox │
│ uuid105      │ uuid789      │ Amazon     │ amazon.com.br/Monitor-LG-27/.. │
│ uuid106      │ uuid789      │ Kabum      │ kabum.com.br/produto/123456/.. │
└──────────────┴──────────────┴────────────┴────────────────────────────────┘

TABELA: price_history
┌──────────────┬────────┬────────────┬──────────────┬────────────────┐
│ uuid         │ price  │ date_price │ store_name   │ product_uuid   │
├──────────────┼────────┼────────────┼──────────────┼────────────────┤
│ uuid201      │ 3799.0 │ 2026-05-19 │ Amazon       │ uuid123        │
│ uuid202      │ 3699.0 │ 2026-05-19 │ Kabum        │ uuid123        │
│ uuid203      │ 4500.0 │ 2026-05-19 │ Mercado Livre│ uuid456        │
│ uuid204      │ 4299.0 │ 2026-05-19 │ Casas Bahia  │ uuid456        │
│ uuid205      │ 1299.0 │ 2026-05-19 │ Amazon       │ uuid789        │
│ uuid206      │ 1199.0 │ 2026-05-19 │ Kabum        │ uuid789        │
└──────────────┴────────┴────────────┴──────────────┴────────────────┘

// ============================================================
// EXEMPLO 6: Resposta da API (Proposta Futura)
// ============================================================

GET /api/products

{
  "status": "success",
  "data": [
    {
      "uuid": "550e8400-e29b-41d4-a716-446655440000",
      "sku": "PS5-001",
      "nome": "PlayStation 5",
      "menorPrecoAtual": 3699.00,
      "lojaComMenorPreco": "Kabum",
      "dataDaMedicao": "2026-05-19T14:27:49-03:00",
      "lojas": [
        {
          "nome": "Amazon",
          "url": "https://www.amazon.com.br/..."
        },
        {
          "nome": "Kabum",
          "url": "https://www.kabum.com.br/..."
        }
      ]
    }
  ]
}

GET /api/products/:uuid/history

{
  "status": "success",
  "produto": "PlayStation 5",
  "sku": "PS5-001",
  "historico": [
    {
      "data": "2026-05-17",
      "preco": 3899.00,
      "loja": "Amazon"
    },
    {
      "data": "2026-05-17",
      "preco": 3799.00,
      "loja": "Kabum"
    },
    {
      "data": "2026-05-18",
      "preco": 3799.00,
      "loja": "Amazon"
    },
    {
      "data": "2026-05-18",
      "preco": 3799.00,
      "loja": "Kabum"
    },
    {
      "data": "2026-05-19",
      "preco": 3799.00,
      "loja": "Amazon"
    },
    {
      "data": "2026-05-19",
      "preco": 3699.00,
      "loja": "Kabum"
    }
  ],
  "estatisticas": {
    "menorPreco": 3699.00,
    "maiorPreco": 3899.00,
    "precoMedio": 3799.00,
    "variacao": "5.13%"
  }
}

// ============================================================
// EXEMPLO 7: Estrutura de Classes Java
// ============================================================

// Product.java
@Entity
public class Product {
    @Id
    private UUID uuid;
    
    @Column
    private String sku;           // PS5-001
    
    @Column
    private String name;          // PlayStation 5
    
    @Column
    private Float price;          // 3699.00 (menor preço)
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductLink> links;           // [Amazon, Kabum]
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Price> historicalPrice;       // [3799, 3699]
}

// ProductLink.java
@Entity
public class ProductLink {
    @Id
    private UUID uuid;
    
    @Column
    private String storeName;     // "Amazon" ou "Kabum"
    
    @Column
    private String url;           // "https://amazon.com.br/..."
    
    @ManyToOne
    private Product product;      // Referência ao produto
}

// Price.java (Histórico)
@Entity
public class Price {
    @Id
    private UUID uuid;
    
    @Column
    private Float price;          // 3699.00
    
    @Column
    private String storeName;     // "Kabum"
    
    @Temporal
    private Date date;            // 2026-05-19
    
    @ManyToOne
    private Product product;      // Referência ao produto
}
