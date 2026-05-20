# 📦 SISTEMA DE COMPARAÇÃO DE PREÇOS - INSTRUÇÕES DE ENTREGA

## ✅ Projeto Completo e Funcional

Este projeto implementa **completamente** os requisitos solicitados na atividade de Arquitetura de Software.

---

## 📂 Arquivos do Projeto

### **Código Fonte** (`src/`)
```
src/
├── Main.java                    ✅ CLASSE PRINCIPAL (com web scraping)
├── MainDemo.java                ✅ DEMO (sem scraping, dados simulados)
├── adapter/
│   ├── DatabaseStorage.java     ✅ Persistência com Hibernate
│   ├── PlaywrightScraper.java   ✅ Web scraping com Playwright
│   └── PersistInterface.java    ✅ Interface de persistência
├── domain/
│   ├── EntityInterface.java     ✅ Interface base para entidades
│   ├── Product.java             ✅ Entidade de produto
│   ├── ProductLink.java         ✅ Link de produto em loja
│   └── Price.java               ✅ Histórico de preços
├── service/
│   ├── CrawlerService.java      ✅ Executa crawler
│   ├── ProductService.java      ✅ Gerencia produtos
│   └── ServiceInterface.java    ✅ Interface de serviços
└── utils/
    └── GenerateValue.java       ✅ Gerador de UUIDs
```

### **Configuração** (`resources/`)
```
resources/
└── META-INF/
    └── persistence.xml         ✅ Configuração JPA/Hibernate
```

### **Documentação** (Raiz do Projeto)
```
├── README.md                    ✅ Documentação geral do sistema
├── DOCUMENTACAO_ARQUITETURA.md  ✅ Documentação técnica completa
├── SUMARIO_IMPLEMENTACAO.md     ✅ Sumário de implementação
├── EXEMPLOS_DADOS.md            ✅ Exemplos de estrutura de dados
└── pom.xml                      ✅ Configuração Maven
```

### **Banco de Dados**
```
└── products.db                  ✅ SQLite (criado automaticamente)
```

---

## 🎯 O que foi Implementado

### ✅ **Requisitos Técnicos**

1. **Cadastro de Produtos**
   - [x] Produtos com SKU, nome e preço
   - [x] Armazenamento em banco de dados

2. **Múltiplos Links por Produto**
   - [x] Cada produto pode ter vários links
   - [x] Cada link aponta para uma loja diferente
   - [x] Armazenamento em tabela separada (product_link)

3. **Múltiplas Lojas**
   - [x] Amazon
   - [x] Kabum
   - [x] Mercado Livre
   - [x] Casas Bahia
   - [x] Cada produto tem mínimo 2 lojas

4. **Execução do Crawler**
   - [x] Percorre todos os produtos cadastrados
   - [x] Para cada produto, percorre todos os links
   - [x] Acessa cada URL e busca o preço
   - [x] Usa web scraping com Playwright
   - [x] Trata erros de acesso

5. **Comparação de Preços**
   - [x] Compara todos os preços encontrados
   - [x] Identifica o menor preço
   - [x] Identifica qual loja tem o menor preço

6. **Histórico de Preços**
   - [x] Salva cada preço encontrado
   - [x] Registra a data de coleta
   - [x] Registra o nome da loja
   - [x] Permite rastreamento de evolução de preços

7. **Persistência**
   - [x] Banco SQLite automático
   - [x] Hibernate ORM para mapeamento
   - [x] Jakarta Persistence 3.0
   - [x] Transações ACID

### ✅ **Requisitos de Arquitetura**

1. **Separação em Camadas**
   - [x] Camada de Apresentação (Main)
   - [x] Camada de Serviço (ProductService, CrawlerService)
   - [x] Camada de Domínio (Product, ProductLink, Price)
   - [x] Camada de Adapter (DatabaseStorage, PlaywrightScraper)

2. **Interfaces de Contrato**
   - [x] EntityInterface - Para entidades
   - [x] ServiceInterface - Para serviços
   - [x] PersistInterface - Para persistência

3. **Padrões de Design**
   - [x] Layered Architecture
   - [x] Repository Pattern
   - [x] Adapter Pattern
   - [x] Strategy Pattern
   - [x] Factory Pattern
   - [x] Dependency Injection

---

## 🚀 Como Executar

### **Pré-requisitos**
```bash
Java Development Kit (JDK) 25 ou superior
Maven 3.x
```

### **Compilar o Projeto**
```bash
# Navegar para o diretório
cd "d:\Git e GitHub\arquitetura-software-quinta"

# Compilar
mvn clean compile
```

### **Executar com Web Scraping Real**
```bash
mvn exec:java
```
- **O quê faz**: Busca preços em tempo real nas lojas
- **Tempo**: 2-5 minutos (inclui download do Playwright)
- **Saída**: Preços reais encontrados e salvos no histórico

### **Executar Demo (Recomendado para Testes)**
```bash
mvn exec:java -Dexec.mainClass="MainDemo"
```
- **O quê faz**: Simula preços e demonstra funcionalidade
- **Tempo**: 30 segundos
- **Saída**: Dados simulados salvos no banco de dados

### **Compilar e Empacotar**
```bash
mvn clean package
```

---

## 📊 Exemplo de Execução (Demo)

```
====================================================
     SISTEMA DE COMPARACAO DE PRECOS
====================================================

Cadastrando: PlayStation 5
✓ PlayStation 5 cadastrado com 2 lojas (Amazon, Kabum)
  - Preços: R$ 3.799,00 (Amazon) | R$ 3.699,00 (Kabum)

Cadastrando: Xbox Series X
✓ Xbox Series X cadastrado com 2 lojas (Mercado Livre, Casas Bahia)
  - Preços: R$ 4.500,00 (Mercado Livre) | R$ 4.299,00 (Casas Bahia)

Cadastrando: Monitor LG 27 polegadas
✓ Monitor LG 27" cadastrado com 2 lojas (Amazon, Kabum)
  - Preços: R$ 1.299,00 (Amazon) | R$ 1.199,00 (Kabum)

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
  - R$ 3.799,00 na Amazon (19/05/2026 14:27:48)
  - R$ 3.699,00 na Kabum (19/05/2026 14:27:49)

Produto: Xbox Series X
SKU: XBOX-001
Menor preço atual: R$ 4.299,00
Links cadastrados:
  - Mercado Livre: https://produto.mercadolivre.com.br/...
  - Casas Bahia: https://www.casasbahia.com.br/...
Histórico de preços:
  - R$ 4.500,00 na Mercado Livre (19/05/2026 14:27:52)
  - R$ 4.299,00 na Casas Bahia (19/05/2026 14:27:53)

Produto: Monitor LG 27 polegadas
SKU: MON-001
Menor preço atual: R$ 1.199,00
Links cadastrados:
  - Amazon: https://www.amazon.com.br/...
  - Kabum: https://www.kabum.com.br/...
Histórico de preços:
  - R$ 1.299,00 na Amazon (19/05/2026 14:27:55)
  - R$ 1.199,00 na Kabum (19/05/2026 14:27:56)

====================================================
Sistema finalizado com sucesso!
====================================================
```

---

## 💾 Banco de Dados

**Arquivo**: `products.db` (SQLite)

**Tabelas**:
1. **product** - Produtos cadastrados
2. **product_link** - Links de produtos em lojas
3. **price_history** - Histórico de preços

Criadas automaticamente pelo Hibernate na primeira execução.

---

## 📋 Estrutura de Dados Implementada

### **Produto**
```json
{
  "uuid": "550e8400-e29b-41d4-a716-446655440000",
  "sku": "PS5-001",
  "nome": "PlayStation 5",
  "precoAtual": 3699.00,
  "dataPreco": "2026-05-19T14:27:49",
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

### **Histórico de Preços**
```json
{
  "produto": "PlayStation 5",
  "preco": 3699.00,
  "loja": "Kabum",
  "data": "2026-05-19T14:27:49"
}
```

---

## 🏗️ Arquitetura Visual

```
┌─────────────────────────────────────────────────────┐
│                    APRESENTAÇÃO                     │
│  Main.java / MainDemo.java                          │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│                 SERVIÇO                             │
│  ProductService / CrawlerService                    │
│  (implementam ServiceInterface)                     │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│                 DOMÍNIO                             │
│  Product / ProductLink / Price                      │
│  (implementam EntityInterface)                      │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│                ADAPTER                              │
│  DatabaseStorage / PlaywrightScraper                │
│  (implementam PersistInterface)                     │
└─────────────────────────────────────────────────────┘
```

---

## 📚 Documentação Disponível

1. **README.md** - Guia geral do projeto
2. **DOCUMENTACAO_ARQUITETURA.md** - Detalhes técnicos e padrões
3. **SUMARIO_IMPLEMENTACAO.md** - Checklist de requisitos
4. **EXEMPLOS_DADOS.md** - Exemplos de estrutura de dados
5. **Este arquivo** - Instruções de entrega

---

## ✨ Destaques da Implementação

### **Código Limpo**
- Nomes significativos
- Métodos pequenos e focados
- Sem duplicação
- Bem documentado

### **Arquitetura Sólida**
- Separação de responsabilidades
- SOLID Principles
- Design Patterns
- Fácil manutenção e extensão

### **Funcionalidades Completas**
- Cadastro de produtos ✅
- Múltiplos links por produto ✅
- Múltiplas lojas ✅
- Crawler automático ✅
- Comparação de preços ✅
- Histórico persistente ✅

### **Tecnologias Modernas**
- Java 25 ✅
- Hibernate 6.6.4 ✅
- Playwright 1.43.0 ✅
- SQLite ✅

---

## 🎓 Conceitos de Arquitetura Demonstrados

- Layered Architecture
- Repository Pattern
- Adapter Pattern
- Strategy Pattern
- Factory Pattern
- Dependency Injection
- SOLID Principles
- Clean Code
- Entity Relationships (JPA)
- ORM (Hibernate)

---

## ❓ Dúvidas Frequentes

**P: O projeto funciona sem internet?**  
R: A versão com scraping precisa de internet. Use `MainDemo` para demo offline.

**P: Posso adicionar mais lojas?**  
R: Sim, adicione casos em `PlaywrightScraper.getPrice()` com seletores específicos.

**P: Como adicionar novos produtos?**  
R: Via `Main.java`, criando instâncias de `Product` e chamando `productService.create()`.

**P: O banco persiste os dados?**  
R: Sim, no arquivo `products.db`. Delete-o para resetar.

---

## 📝 Conclusão

O projeto foi implementado **completamente** conforme os requisitos da atividade, com:

✅ Arquitetura em camadas bem estruturada  
✅ Todas as funcionalidades solicitadas  
✅ Padrões de design apropriados  
✅ Código limpo e documentado  
✅ Persistência em banco de dados  
✅ Web scraping automático  

**Status: PRONTO PARA ENTREGA** 🎉

---

## 📞 Informações do Projeto

- **Linguagem**: Java 25
- **Framework**: Hibernate 6.6.4 + JPA
- **Banco de Dados**: SQLite
- **Web Scraping**: Playwright 1.43.0
- **Build Tool**: Maven
- **Arquitetura**: Layered Architecture
- **Data de Conclusão**: 19 de Maio de 2026
