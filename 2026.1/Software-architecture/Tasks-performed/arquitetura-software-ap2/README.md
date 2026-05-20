# Sistema de Comparação de Preços - Arquitetura de Software

## Objetivo
Sistema que percorre links de produtos em lojas online e salva o histórico de preços encontrados, permitindo acompanhar o preço de um mesmo produto em mais de uma loja.

## Arquitetura

O projeto segue uma arquitetura em camadas bem definida:

```
src/
├── domain/           # Entidades de domínio
│   ├── EntityInterface.java
│   ├── Product.java           # Produto com lista de links e histórico
│   ├── ProductLink.java       # Link de produto em loja específica
│   └── Price.java             # Histórico de preços
├── service/          # Lógica de negócio
│   ├── ServiceInterface.java
│   ├── ProductService.java    # Serviço de produtos
│   └── CrawlerService.java    # Serviço de crawler
├── adapter/          # Adaptadores externos
│   ├── PersistInterface.java
│   ├── DatabaseStorage.java   # Persistência com Hibernate
│   └── PlaywrightScraper.java # Web scraper com Playwright
└── utils/            # Utilitários
    └── GenerateValue.java
```

## Funcionalidades Principais

### 1. Cadastro de Produtos
Cada produto possui:
- **SKU**: Identificador único do produto
- **Nome**: Nome do produto
- **Lista de Links**: Múltiplos links apontando para diferentes lojas
- **Histórico de Preços**: Rastreamento de preços ao longo do tempo

### 2. Múltiplos Links por Produto
Cada produto pode ter links de diferentes lojas:
```json
{
  "nome": "PlayStation 5",
  "links": [
    {
      "loja": "Amazon",
      "url": "https://www.amazon.com.br/..."
    },
    {
      "loja": "Kabum",
      "url": "https://www.kabum.com.br/..."
    }
  ]
}
```

### 3. Lojas Suportadas
- Amazon
- Kabum
- Mercado Livre
- Casas Bahia

### 4. Execução do Crawler
O crawler executa as seguintes operações:
1. Percorre todos os produtos cadastrados
2. Para cada produto, percorre todos os links cadastrados
3. Acessa cada link e busca o preço atual
4. Compara os preços encontrados
5. Identifica o menor preço
6. Salva no histórico de preços com a data e nome da loja

### 5. Histórico de Preços
Cada registro contém:
- **Preço**: Valor encontrado
- **Data**: Data e hora da coleta
- **Loja**: Nome da loja onde foi encontrado
- **Produto**: Referência ao produto

Exemplo:
```json
{
  "produto": "PlayStation 5",
  "preco": 3699.00,
  "loja": "Kabum",
  "data": "2026-05-13"
}
```

## Tecnologias Utilizadas

- **Java 25**: Linguagem de programação
- **Maven**: Gerenciador de dependências e build
- **Hibernate 6.6.4**: ORM para persistência
- **Jakarta Persistence 3.0**: JPA
- **SQLite**: Banco de dados
- **CURL**: Web scraping via requisições HTTP
- **SLF4J**: Logging

## Como Executar

### Compilar o projeto
```bash
mvn clean compile
```

### Executar a aplicação
```bash
mvn exec:java
```

### Build completo
```bash
mvn clean package
```

## Exemplo de Uso

Ao executar a aplicação pela primeira vez, o sistema:
1. Cria o banco de dados SQLite (`products.db`)
2. Cadastra produtos de exemplo:
   - PlayStation 5 (Amazon, Kabum)
   - Xbox Series X (Mercado Livre, Casas Bahia)
   - Monitor LG 27" (Amazon, Kabum)
3. Executa o crawler para buscar preços
4. Exibe o resumo dos produtos com menor preço encontrado

## Estrutura de Dados

### Product
- UUID (gerado automaticamente)
- SKU (identificador único)
- Nome
- Preço atual
- Data do preço
- Lista de ProductLink
- Histórico de Price

### ProductLink
- UUID
- Nome da loja
- URL
- Referência ao produto

### Price (Histórico)
- UUID
- Preço
- Data
- Nome da loja
- Referência ao produto

## Diagrama de Classes

```
EntityInterface (interface)
    ├── Product
    ├── ProductLink
    └── Price

Product
    ├── 1..* ProductLink
    └── 1..* Price

ServiceInterface (interface)
    └── ProductService
        └── uses DatabaseStorage

CrawlerService
    ├── uses ProductService
    └── uses PlaywrightScraper

PersistInterface (interface)
    └── DatabaseStorage
        ├── saves Product
        ├── saves ProductLink
        └── saves Price
```

## Fluxo de Execução

1. **Main.java** inicia a aplicação
2. **ProductService** gerencia produtos
3. **CrawlerService** executa:
   - Busca todos os produtos via ProductService
   - Para cada produto, itera sobre ProductLink
   - **PlaywrightScraper** busca preço em cada URL
   - Identifica o menor preço
   - Cria registro de **Price** com preço e loja
   - **DatabaseStorage** persiste dados via Hibernate
4. Exibe resumo final com produtos, preços e histórico

## Persistência

O projeto usa **SQLite** como banco de dados local. O Hibernate gerencia automaticamente:
- Criação de tabelas
- Mapeamento objeto-relacional
- Transações
- Cascade de operações

Banco de dados: `products.db` (gerado automaticamente na raiz do projeto)

## Padrões de Arquitetura

- **Factory Pattern**: DatabaseStorage e ProductService
- **Adapter Pattern**: PlaywrightScraper para web scraping
- **Strategy Pattern**: Diferentes estratégias de scraping por loja
- **Repository Pattern**: DatabaseStorage implementa interface PersistInterface
- **Layered Architecture**: Separação clara entre domain, service e adapter

## Próximas Melhorias

- Dashboard web para visualização de preços
- Notificações quando preço cai
- Mais estratégias de scraping por loja
- API REST para integração
- Agendamento automático do crawler
