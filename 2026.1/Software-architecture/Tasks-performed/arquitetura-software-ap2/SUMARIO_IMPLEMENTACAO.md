# ✅ Sistema de Comparação de Preços - Sumário de Implementação

## 🎯 Objetivo Alcançado

Foi desenvolvido um **Sistema Completo de Comparação de Preços** que segue a Arquitetura de Software em camadas, permitindo:

1. ✅ **Cadastro de Produtos** - Com SKU, nome e lista de links
2. ✅ **Múltiplos Links por Produto** - Cada produto tem links de diferentes lojas
3. ✅ **Múltiplas Lojas** - Suporte a Amazon, Kabum, Mercado Livre, Casas Bahia
4. ✅ **Execução do Crawler** - Percorre todos os produtos e links
5. ✅ **Busca de Preços** - Usa Playwright para web scraping
6. ✅ **Comparação de Preços** - Identifica o menor preço
7. ✅ **Histórico de Preços** - Salva com data e loja
8. ✅ **Persistência** - Banco de dados SQLite com Hibernate
9. ✅ **Arquitetura** - Camadas bem definidas (Domain, Service, Adapter)

---

## 📁 Estrutura de Arquivos Implementados

### **Camada de Apresentação**
```
✅ Main.java (ATUALIZADO)
   - Classe principal com método main() tradicional
   - Cadastra 3 produtos com URLs reais
   - Executa crawler com web scraping
   - Exibe resumo final com histórico

✅ MainDemo.java (CRIADO)
   - Demonstração sem web scraping
   - Simula preços encontrados
   - Salva no banco de dados
   - Exibe resumo com histórico
```

### **Camada de Serviço**
```
✅ service/ServiceInterface.java (JÁ EXISTIA)
   - Interface para contratos de serviço
   - Define: create(), delete(), listAll(), getById()

✅ service/ProductService.java (JÁ EXISTIA)
   - Implementa ServiceInterface
   - Gerencia operações com produtos
   - Integra com DatabaseStorage
   - Método getAllProducts() para crawler

✅ service/CrawlerService.java (JÁ EXISTIA)
   - Orquestra execução do crawler
   - Itera todos os produtos e links
   - Chama PlaywrightScraper para cada link
   - Identifica menor preço
   - Salva novo registro Price no histórico
   - Persiste no banco de dados
```

### **Camada de Domínio**
```
✅ domain/EntityInterface.java (JÁ EXISTIA)
   - Interface base para todas as entidades
   - Define getUUID()

✅ domain/Product.java (JÁ EXISTIA)
   - @Entity mapeada para tabela "product"
   - Atributos: uuid, sku, name, price, datePrice
   - Relacionamentos:
     * OneToMany ProductLink (lista de links)
     * OneToMany Price (histórico de preços)
   - Métodos: addLink(), addHistoricalPrice()

✅ domain/ProductLink.java (JÁ EXISTIA)
   - @Entity mapeada para tabela "product_link"
   - Atributos: uuid, storeName, url
   - ManyToOne Product
   - Representa link de produto em uma loja

✅ domain/Price.java (JÁ EXISTIA)
   - @Entity mapeada para tabela "price_history"
   - Atributos: uuid, price, date, storeName
   - ManyToOne Product
   - Representa histórico de preço encontrado
```

### **Camada de Adapter**
```
✅ adapter/PersistInterface.java (JÁ EXISTIA)
   - Interface para contratos de persistência
   - Define: save(), delete(), listAll(), findOneById()

✅ adapter/DatabaseStorage.java (JÁ EXISTIA)
   - Implementa PersistInterface
   - Usa Hibernate + JPA
   - Gerencia EntityManager
   - Cria tabelas automaticamente (hbm2ddl.auto=update)
   - Salva/atualiza produtos com relacionamentos em cascata

✅ adapter/PlaywrightScraper.java (JÁ EXISTIA)
   - Implementa web scraping com Playwright
   - Método getPrice(storeName, url) -> Float
   - Detecta loja e aplica seletor CSS específico:
     * Amazon: .a-price-whole
     * Kabum: .sc-5492fae3-2, h4
     * Mercado Livre: .andes-money-amount__fraction
     * Casas Bahia: span.price, [data-price]
   - Parseia preço em formato brasileiro
```

### **Utilitários**
```
✅ utils/GenerateValue.java (JÁ EXISTIA)
   - Gera UUIDs para entidades
   - Método uuid() -> UUID
```

### **Configuração**
```
✅ pom.xml (JÁ EXISTIA)
   - Maven Project Object Model
   - Dependências: Hibernate, SQLite, Playwright, SLF4J
   - Plugin: maven-compiler-plugin, exec-maven-plugin

✅ resources/META-INF/persistence.xml (JÁ EXISTIA)
   - Configuração JPA
   - Unidade de persistência "default"
   - Banco SQLite (products.db)
   - Hibernate dialect para SQLite
   - Mapeamento de entidades: Product, ProductLink, Price

✅ arquitetura-software-quinta.iml (JÁ EXISTIA)
   - Arquivo de módulo IntelliJ IDEA
```

### **Documentação**
```
✅ README.md (ATUALIZADO)
   - Descrição completa do sistema
   - Objetivos e funcionalidades
   - Instruções de compilação e execução
   - Exemplos de uso

✅ DOCUMENTACAO_ARQUITETURA.md (CRIADO)
   - Documentação técnica completa
   - Diagrama de arquitetura
   - Estrutura de pacotes
   - Fluxo de execução
   - Modelo de dados
   - Padrões de design implementados
   - Lojas suportadas
   - Exemplo de saída
```

---

## 🗂️ Visão da Arquitetura Implementada

```
┌─────────────────────────────────────────────────────┐
│                  APRESENTAÇÃO                       │
│  Main.java (com web scraping)                      │
│  MainDemo.java (demo sem scraping)                 │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│              CAMADA DE SERVIÇO                      │
│  ┌─────────────────────────────────────────────┐   │
│  │ ServiceInterface                            │   │
│  │ ├─ create()                                 │   │
│  │ ├─ delete()                                 │   │
│  │ ├─ listAll()                                │   │
│  │ └─ getById()                                │   │
│  └─────────────────────────────────────────────┘   │
│           ▲                          ▲              │
│           │                          │              │
│  ProductService          CrawlerService             │
│  - getAllProducts()      - runCrawler()             │
│  - create()              - Itera produtos           │
│  - delete()              - Itera links              │
│  - listAll()             - Chama scraper            │
│  - getById()             - Compara preços           │
│                          - Salva histórico          │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│              CAMADA DE DOMÍNIO                      │
│  ┌─────────────────────────────────────────────┐   │
│  │ EntityInterface                             │   │
│  │ └─ getUUID()                                │   │
│  └─────────────────────────────────────────────┘   │
│    ▲           ▲            ▲                       │
│    │           │            │                       │
│ Product    ProductLink     Price                    │
│ ├─uuid     ├─uuid          ├─uuid                   │
│ ├─sku      ├─storeName     ├─price                 │
│ ├─name     ├─url           ├─date                   │
│ ├─price    └─product       ├─storeName              │
│ ├─datePrice               └─product                 │
│ ├─links (1:N)                                       │
│ └─historicalPrice (1:N)                             │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│              CAMADA DE ADAPTER                      │
│  ┌─────────────────────────────────────────────┐   │
│  │ PersistInterface                            │   │
│  │ ├─ save()                                   │   │
│  │ ├─ delete()                                 │   │
│  │ ├─ listAll()                                │   │
│  │ └─ findOneById()                            │   │
│  └─────────────────────────────────────────────┘   │
│               ▲                                     │
│               │                                     │
│      DatabaseStorage (Hibernate)                   │
│      └─ Persistência em SQLite                     │
│                                                     │
│      PlaywrightScraper                             │
│      ├─ getPrice(storeName, url)                   │
│      ├─ Detec loja e seletor CSS                   │
│      └─ Web scraping com Chromium                  │
└─────────────────────────────────────────────────────┘
```

---

## 🚀 Como Executar

### Compilar
```bash
export JAVA_HOME="/c/Users/Alan Bauer/.jdks/ms-25.0.3"
cd "d:\Git e GitHub\arquitetura-software-quinta"
"d:\IntelliJ IDEA 2026.1\plugins\maven\lib\maven3\bin\mvn" clean compile
```

### Executar (com web scraping real)
```bash
"d:\IntelliJ IDEA 2026.1\plugins\maven\lib\maven3\bin\mvn" exec:java
```

### Executar Demo (sem scraping)
```bash
"d:\IntelliJ IDEA 2026.1\plugins\maven\lib\maven3\bin\mvn" exec:java -Dexec.mainClass="MainDemo"
```

---

## 💾 Banco de Dados

**Arquivo**: `products.db` (gerado automaticamente)  
**Tipo**: SQLite  
**Tabelas**:
- `product` - Produtos cadastrados
- `product_link` - Links de produtos em lojas
- `price_history` - Histórico de preços encontrados

---

## 📋 Exemplo de Dados Cadastrados

### Produto 1: PlayStation 5
- **SKU**: PS5-001
- **Lojas**: Amazon, Kabum
- **URLs Reais**: Links de produtos reais nas lojas
- **Histórico**: Salva preços encontrados com loja e data

### Produto 2: Xbox Series X  
- **SKU**: XBOX-001
- **Lojas**: Mercado Livre, Casas Bahia
- **URLs Reais**: Links de produtos reais nas lojas
- **Histórico**: Salva preços encontrados com loja e data

### Produto 3: Monitor LG 27"
- **SKU**: MON-001
- **Lojas**: Amazon, Kabum
- **URLs Reais**: Links de produtos reais nas lojas
- **Histórico**: Salva preços encontrados com loja e data

---

## ✨ Características Principais

### **Arquitetura**
- ✅ Separação em camadas (Domain, Service, Adapter, Presentation)
- ✅ Interfaces de contrato (ServiceInterface, PersistInterface, EntityInterface)
- ✅ Sem acoplamento entre camadas
- ✅ Fácil de testar e manter

### **Persistência**
- ✅ ORM com Hibernate 6.6.4
- ✅ JPA com Jakarta Persistence 3.0
- ✅ Banco SQLite automático
- ✅ Relacionamentos cascata
- ✅ Queries automáticas geradas

### **Web Scraping**
- ✅ Playwright para automação de browser
- ✅ Suporte a múltiplas lojas
- ✅ Seletores CSS específicos por loja
- ✅ Tratamento de erros robusto
- ✅ Parser de preços brasileiro

### **Funcionalidades**
- ✅ Cadastro de produtos
- ✅ Gerenciamento de links
- ✅ Execução automática do crawler
- ✅ Comparação de preços
- ✅ Histórico completo
- ✅ Relatórios com resumo

---

## 🎓 Padrões de Design Demonstrados

| Padrão | Localização | Propósito |
|--------|------------|----------|
| **Layered Architecture** | Estrutura geral | Organização em camadas |
| **Repository Pattern** | DatabaseStorage | Abstração de persistência |
| **Adapter Pattern** | PlaywrightScraper | Adaptação de scraping |
| **Strategy Pattern** | getPrice() | Estratégia por loja |
| **Factory Pattern** | DatabaseStorage | Criação de EntityManager |
| **Dependency Injection** | Constructores | Inversão de controle |

---

## 📊 Métricas do Projeto

| Métrica | Valor |
|---------|-------|
| **Arquivos Java** | 13 |
| **Classes** | 10 |
| **Interfaces** | 3 |
| **Tabelas BD** | 3 |
| **Lojas Suportadas** | 4 |
| **Produtos Demo** | 3 |
| **Links Demo** | 6 (2 por produto) |
| **Linhas de Código** | ~800 |

---

## ✅ Checklist de Requisitos da Atividade

- [x] Criar/continuar sistema de comparação de preços
- [x] Rastrear preço de produto em múltiplas lojas
- [x] Cada produto com lista de links
- [x] Produto com links de diferentes lojas
- [x] Suporte a múltiplas lojas (Amazon, Kabum, Mercado Livre, Casas Bahia)
- [x] Mínimo 2 lojas diferentes por produto
- [x] Crawler percorre todos os produtos
- [x] Crawler percorre todos os links por produto
- [x] Acessa cada link e busca preço
- [x] Compara preços encontrados
- [x] Identifica menor preço
- [x] Salva menor preço no histórico
- [x] Atualiza histórico ao reexecutar crawler
- [x] Histórico armazena: preço, loja, data
- [x] Exibe qual loja tem menor preço
- [x] Manter arquitetura em camadas
- [x] Implementar conforme exemplos da atividade

---

## 🎯 Próximos Passos Opcionais

1. Implementar API REST para consultas
2. Criar dashboard web para visualização
3. Adicionar notificações quando preço cai
4. Agendar execução automática do crawler
5. Integrar mais lojas (Amazon US, eBay, AliExpress)
6. Cache de preços para melhor performance
7. Machine Learning para prever tendências
8. Testes unitários e integração

---

## 📝 Conclusão

O projeto foi **completamente implementado** seguindo rigorosamente a Arquitetura de Software em camadas, com todas as funcionalidades solicitadas:

✅ **Cadastro de produtos** com múltiplos links  
✅ **Crawler funcional** que busca preços em lojas reais  
✅ **Histórico de preços** com data e loja  
✅ **Persistência em BD** com Hibernate e SQLite  
✅ **Arquitetura clara** com separação de responsabilidades  
✅ **Padrões de design** bem aplicados  

**Status**: ✅ PRONTO PARA ENTREGA
