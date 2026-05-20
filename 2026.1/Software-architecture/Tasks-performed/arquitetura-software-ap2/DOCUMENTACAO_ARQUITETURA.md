# Documentação da Arquitetura - Sistema de Comparação de Preços

## 📋 Resumo Executivo

Este projeto implementa um **Sistema de Comparação de Preços** que permite rastrear o preço de produtos em múltiplas lojas online, mantendo um histórico de preços ao longo do tempo.

**Requisitos Implementados:**
- ✅ Cadastro de produtos com múltiplos links
- ✅ Suporte a pelo menos 2 lojas diferentes por produto  
- ✅ Execução do crawler que percorre todos os produtos e links
- ✅ Busca de preços em cada link
- ✅ Comparação de preços e identificação do menor preço
- ✅ Salvamento do histórico de preços com data e loja

---

## 🏗️ Arquitetura em Camadas

O projeto segue uma **Arquitetura em Camadas (Layered Architecture)** bem definida:

```
┌─────────────────────────────────────────────────────┐
│               Apresentação (Main)                    │
│  - Cadastro de produtos                             │
│  - Exibição de resumos e históricos                 │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│         Camada de Serviço (Service)                 │
│  - ProductService: gerencia produtos                │
│  - CrawlerService: executa o crawler                │
│  - ServiceInterface: contrato de serviços           │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│          Camada de Domínio (Domain)                 │
│  - Product: entidade de produto                     │
│  - ProductLink: link de produto em loja             │
│  - Price: histórico de preços                       │
│  - EntityInterface: contrato de entidades           │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│         Camada de Adapter (Adapter)                 │
│  - PlaywrightScraper: web scraping                  │
│  - DatabaseStorage: persistência                    │
│  - PersistInterface: contrato de persistência       │
└─────────────────────────────────────────────────────┘
```

---

## 📦 Estrutura de Pacotes

```
src/
├── Main.java                          # Classe principal com main()
├── MainDemo.java                      # Demo sem scraping real
│
├── domain/                            # Entidades de domínio
│   ├── EntityInterface.java           # Interface base para entidades
│   ├── Product.java                   # Produto com links e histórico
│   ├── ProductLink.java               # Link de produto em loja
│   └── Price.java                     # Histórico de preços
│
├── service/                           # Lógica de negócio
│   ├── ServiceInterface.java          # Interface para serviços
│   ├── ProductService.java            # Gerencia produtos
│   └── CrawlerService.java            # Executa crawler
│
├── adapter/                           # Adaptadores externos
│   ├── PersistInterface.java          # Interface de persistência
│   ├── DatabaseStorage.java           # Persistência com Hibernate
│   └── PlaywrightScraper.java         # Web scraper
│
└── utils/                             # Utilitários
    └── GenerateValue.java             # Gerador de valores
```

---

## 🔄 Fluxo de Execução do Crawler

```
Main.java
    ↓
ProductService.getAllProducts()
    ↓
CrawlerService.runCrawler()
    ↓
┌─────────────────────────────────────┐
│ Para cada Produto:                  │
├─────────────────────────────────────┤
│ ├─ Para cada ProductLink:           │
│ │  ├─ PlaywrightScraper.getPrice()  │
│ │  ├─ Extrai preço da URL           │
│ │  └─ Compara com lowestPrice       │
│ │                                    │
│ ├─ Identifica menor preço            │
│ ├─ Cria novo registro Price          │
│ ├─ Adiciona ao histórico             │
│ ├─ DatabaseStorage.save() persiste   │
│ └─ Exibe resumo                      │
└─────────────────────────────────────┘
    ↓
Exibe resumo final com histórico completo
```

---

## 💾 Modelo de Dados

### Entidade: Product
```java
@Entity
@Table(name = "product")
public class Product implements EntityInterface {
    @Id UUID uuid;                              // ID único
    @Column String sku;                         // SKU do produto
    @Column String name;                        // Nome
    @Column Float price;                        // Preço atual (menor)
    @Temporal Date datePrice;                   // Data do preço
    @OneToMany List<ProductLink> links;         // Links em lojas
    @OneToMany List<Price> historicalPrice;    // Histórico de preços
}
```

### Entidade: ProductLink
```java
@Entity
@Table(name = "product_link")
public class ProductLink implements EntityInterface {
    @Id UUID uuid;                              // ID único
    @Column String storeName;                   // Nome da loja
    @Column String url;                         // URL do produto
    @ManyToOne Product product;                 // Referência ao produto
}
```

### Entidade: Price (Histórico)
```java
@Entity
@Table(name = "price_history")
public class Price implements EntityInterface {
    @Id UUID uuid;                              // ID único
    @Column Float price;                        // Preço encontrado
    @Temporal Date date;                        // Data da coleta
    @Column String storeName;                   // Loja onde foi encontrado
    @ManyToOne Product product;                 // Referência ao produto
}
```

### Relacionamentos
```
Product (1) ──────────────────── (N) ProductLink
   │                                      
   │                                      
   └──────────────────── (N) Price
```

---

## 🎯 Padrões de Arquitetura Implementados

### 1. **Layered Architecture**
- Separação clara entre Domain, Service, Adapter e Presentation
- Cada camada tem responsabilidades bem definidas
- Facilita manutenção e testes

### 2. **Repository Pattern**
- `DatabaseStorage` implementa `PersistInterface`
- Abstrai detalhes de persistência
- Permite trocar implementação (Hibernate ↔ JDBC ↔ MongoDB)

### 3. **Adapter Pattern**
- `PlaywrightScraper` adapta web scraping para a aplicação
- Encapsula detalhes de acesso a diferentes lojas
- Pode ser substituído por outro adapter

### 4. **Strategy Pattern**
- `CrawlerService` usa estratégia diferente para cada loja
- `PlaywrightScraper.getPrice()` detecta loja e aplica seletor CSS específico
- Facilita adição de novas lojas

### 5. **Factory Pattern**
- `ProductService` cria instâncias de `Product`
- `DatabaseStorage` cria `EntityManager`

### 6. **Dependency Injection**
- `CrawlerService` recebe `ProductService` via construtor
- `ProductService` injeta `DatabaseStorage`
- Facilita testes e manutenção

---

## 🏪 Lojas Suportadas

| Loja | Seletor CSS | URL |
|------|------------|-----|
| **Amazon** | `.a-price-whole` | amazon.com.br |
| **Kabum** | `.sc-5492fae3-2, h4` | kabum.com.br |
| **Mercado Livre** | `.andes-money-amount__fraction` | mercadolivre.com.br |
| **Casas Bahia** | `span.price, [data-price]` | casasbahia.com.br |

---

## 🗄️ Banco de Dados

**Tipo**: SQLite  
**Arquivo**: `products.db` (gerado automaticamente)  
**ORM**: Hibernate 6.6.4  
**JPA**: Jakarta Persistence 3.0

### Tabelas
```sql
CREATE TABLE product (
    uuid VARCHAR(36) PRIMARY KEY,
    sku VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price FLOAT,
    date_price TIMESTAMP
);

CREATE TABLE product_link (
    uuid VARCHAR(36) PRIMARY KEY,
    store_name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    product_uuid VARCHAR(36) NOT NULL,
    FOREIGN KEY (product_uuid) REFERENCES product(uuid)
);

CREATE TABLE price_history (
    uuid VARCHAR(36) PRIMARY KEY,
    price FLOAT NOT NULL,
    date_price TIMESTAMP NOT NULL,
    store_name VARCHAR(255),
    product_uuid VARCHAR(36) NOT NULL,
    FOREIGN KEY (product_uuid) REFERENCES product(uuid)
);
```

---

## 🚀 Como Usar

### Compilar
```bash
mvn clean compile
```

### Executar (com web scraping)
```bash
mvn exec:java
```

### Executar (demo sem scraping)
```bash
mvn exec:java -Dexec.mainClass="MainDemo"
```

### Build completo
```bash
mvn clean package
```

---

## 📊 Exemplo de Saída

```
====================================================
     SISTEMA DE COMPARACAO DE PRECOS
====================================================

Cadastrando: PlayStation 5
✓ PlayStation 5 cadastrado com 2 lojas (Amazon, Kabum)

====================================================
--- Iniciando Crawler ---
Verificando produto: PlayStation 5 (SKU: PS5-001)
Buscando preco em Amazon -> https://www.amazon.com.br/...
Preço encontrado: 3799.00
Buscando preco em Kabum -> https://www.kabum.com.br/...
Preço encontrado: 3699.00
Menor preco encontrado para PlayStation 5: 3699.00 na loja Kabum
--- Crawler Finalizado ---

====================================================
              RESUMO FINAL DOS PRODUTOS
====================================================

Produto: PlayStation 5
SKU: PS5-001
Menor preço atual: R$ 3.699,00
Links cadastrados:
  - Amazon: https://www.amazon.com.br/...
  - Kabum: https://www.kabum.com.br/...
Histórico de preços:
  - R$ 3.799,00 na Amazon em 19/05/2026 14:27:48
  - R$ 3.699,00 na Kabum em 19/05/2026 14:27:49

====================================================
Sistema finalizado com sucesso!
====================================================
```

---

## 🔧 Tecnologias Utilizadas

| Tecnologia | Versão | Função |
|-----------|--------|--------|
| Java | 25 | Linguagem |
| Maven | 3.x | Build |
| Hibernate ORM | 6.6.4 | ORM |
| Jakarta Persistence | 3.0 | JPA |
| SQLite | 3.46.1 | BD |
| Playwright | 1.43.0 | Web Scraping |
| SLF4J | 2.0.16 | Logging |

---

## ✅ Checklist de Requisitos

- [x] Cadastro de produtos
- [x] Lista de links por produto
- [x] Múltiplas lojas por produto (mínimo 2)
- [x] Execução do crawler para todos os produtos
- [x] Busca de preço em cada link
- [x] Comparação de preços
- [x] Identificação do menor preço
- [x] Salvamento do histórico com data
- [x] Salvamento da loja no histórico
- [x] Persistência em banco de dados
- [x] Manutenção da arquitetura em camadas
- [x] Uso de interfaces para contracts
- [x] Separação de responsabilidades

---

## 🎓 Conceitos de Arquitetura Demonstrados

### **SOLID Principles**
- **S**ingle Responsibility: Cada classe tem uma responsabilidade
- **O**pen/Closed: Fácil adicionar novas lojas sem modificar código existente
- **L**iskov Substitution: Interfaces bem definidas
- **I**nterface Segregation: Interfaces específicas (ServiceInterface, PersistInterface)
- **D**ependency Inversion: Dependência de interfaces, não implementações

### **Clean Code**
- Nomes significativos para classes, métodos e variáveis
- Métodos pequenos com responsabilidade clara
- Documentação com JavaDoc
- Sem duplicação de código

### **Design Patterns**
- Repository Pattern para persistência
- Adapter Pattern para web scraping
- Strategy Pattern para lojas diferentes
- Factory Pattern para criação de objetos
- Dependency Injection para inversão de controle

---

## 📈 Possíveis Extensões

1. **API REST** - Expor endpoints para cadastro e consulta
2. **Dashboard Web** - Interface gráfica para visualização
3. **Notificações** - Alertar quando preço cai
4. **Agendamento** - Executar crawler automaticamente
5. **Cache** - Armazenar preços em cache para melhor performance
6. **Integração com mais lojas** - Amazon US, eBay, AliExpress, etc.
7. **Machine Learning** - Prever tendências de preço
8. **API de produtos** - Integrar com catalogos externos

---

## 🐛 Troubleshooting

### Erro: "No compiler is provided"
**Solução**: Configure JAVA_HOME para uma JDK (não JRE)
```bash
export JAVA_HOME="/c/Users/Alan Bauer/.jdks/ms-25.0.3"
```

### Erro: Playwright não baixa navegadores
**Solução**: Configure proxy ou tente manualmente
```bash
npx playwright install --with-deps
```

### Erro: Elemento não encontrado no site
**Solução**: O seletor CSS mudou. Atualize `PlaywrightScraper.getPrice()`

---

## 📝 Conclusão

Este projeto demonstra como construir uma aplicação real mantendo uma arquitetura clara, reutilizável e escalável, com separação bem definida de responsabilidades entre as camadas.

