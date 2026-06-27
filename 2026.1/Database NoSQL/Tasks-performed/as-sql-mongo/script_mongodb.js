// =====================================================================
// PROJETO: Sistema de Controle de Estoque - Central das Ferragens
// SGBD: MongoDB (Banco de Dados NAO Relacional / Orientado a Documentos)
// Autor: Leonardo Wust
// =====================================================================
//
// DECISAO DE MODELAGEM (ponto central do trabalho)
// ----------------------------------------------------------------------
// No MySQL o sistema tem 5 tabelas separadas (categoria, fornecedor,
// funcionario, produto, movimentacao) ligadas por chaves estrangeiras.
//
// No MongoDB NAO copiamos essas 5 tabelas como 5 colecoes com referencias
// imitando FK. Isso desperdicaria a vantagem do banco de documentos.
//
// Aqui a modelagem segue a regra "dados lidos juntos ficam juntos":
//   - A colecao "produtos" EMBARCA (embedding) os dados da categoria e do
//     fornecedor dentro do proprio documento do produto, porque sempre que
//     consultamos um produto queremos exibir essas infos juntas.
//   - As movimentacoes recentes do produto tambem ficam EMBARCADAS num
//     array dentro do documento, com o nome do funcionario "congelado"
//     (snapshot) no momento do registro.
//
// Resultado: uma unica leitura devolve o produto completo, sem JOIN.
//
// TRADE-OFF (assumido de proposito): o array de movimentacoes cresce com
// o tempo. Para um historico muito grande, o ideal seria uma colecao
// separada "movimentacoes" referenciando o produto. Para o escopo deste
// projeto, embarcar as movimentacoes recentes demonstra melhor o conceito
// de modelagem por documento.
// ----------------------------------------------------------------------

// ---------------------------------------------------------------------
// 1. SELECIONA / CRIA O BANCO
// ---------------------------------------------------------------------
use central_ferragens;

// ---------------------------------------------------------------------
// 2. CRIACAO DAS COLECOES
// ---------------------------------------------------------------------
// Colecao principal: produtos (com categoria, fornecedor e movimentacoes embarcados)
db.createCollection("produtos");

// Colecao de apoio: funcionarios.
// Aqui usamos REFERENCIA (e nao embedding), porque o funcionario e uma
// entidade independente, consultada por conta propria, e nao "pertence"
// a um unico produto. Mostra que a escolha embed x referencia e consciente.
db.createCollection("funcionarios");

// ---------------------------------------------------------------------
// 3. ESTRUTURA DOS DOCUMENTOS + INSERCAO DE DADOS
// ---------------------------------------------------------------------

// --- Funcionarios (colecao referenciada) ---
db.funcionarios.insertMany([
  { _id: 1, nome: "Leonardo Wust",  cargo: "Estoquista", data_admissao: "2023-02-01" },
  { _id: 2, nome: "Carlos Pereira", cargo: "Vendedor",   data_admissao: "2021-08-15" },
  { _id: 3, nome: "Marina Souza",   cargo: "Gerente",    data_admissao: "2019-05-10" }
]);

// --- Produtos (documento rico, com tudo embarcado) ---
db.produtos.insertMany([
  {
    nome: "Parafuso Phillips 4x40mm (cento)",
    descricao: "Caixa com 100 unidades",
    preco_custo: 12.00,
    preco_venda: 22.90,
    qtd_estoque: 8,
    estoque_minimo: 10,
    categoria: { nome: "Parafusos e Fixadores", descricao: "Parafusos, buchas, porcas e arruelas" },
    fornecedor: { nome: "Distribuidora Sul Ferragens", cidade: "Porto Alegre", telefone: "51 3333-1000" },
    movimentacoes: [
      { tipo: "ENTRADA", quantidade: 50, data: "2025-05-02", funcionario: "Leonardo Wust" },
      { tipo: "SAIDA",   quantidade: 42, data: "2025-05-20", funcionario: "Carlos Pereira" }
    ]
  },
  {
    nome: "Martelo Unha 25mm cabo madeira",
    descricao: "Martelo carpinteiro",
    preco_custo: 28.00,
    preco_venda: 49.90,
    qtd_estoque: 5,
    estoque_minimo: 6,
    categoria: { nome: "Ferramentas Manuais", descricao: "Martelos, chaves, alicates" },
    fornecedor: { nome: "Distribuidora Sul Ferragens", cidade: "Porto Alegre", telefone: "51 3333-1000" },
    movimentacoes: [
      { tipo: "ENTRADA", quantidade: 10, data: "2025-05-05", funcionario: "Leonardo Wust" },
      { tipo: "SAIDA",   quantidade: 5,  data: "2025-05-22", funcionario: "Carlos Pereira" }
    ]
  },
  {
    nome: "Tinta Acrilica Branca 18L",
    descricao: "Lata 18 litros",
    preco_custo: 140.00,
    preco_venda: 219.90,
    qtd_estoque: 3,
    estoque_minimo: 4,
    categoria: { nome: "Tintas e Acessorios", descricao: "Tintas, pinceis, rolos e solventes" },
    fornecedor: { nome: "Tintas Litoral LTDA", cidade: "Osorio", telefone: "51 3222-2000" },
    movimentacoes: [
      { tipo: "ENTRADA", quantidade: 6, data: "2025-05-08", funcionario: "Leonardo Wust" },
      { tipo: "SAIDA",   quantidade: 3, data: "2025-05-25", funcionario: "Carlos Pereira" }
    ]
  },
  {
    nome: "Fio Flexivel 2,5mm (rolo 100m)",
    descricao: "Rolo 100 metros",
    preco_custo: 95.00,
    preco_venda: 149.90,
    qtd_estoque: 2,
    estoque_minimo: 5,
    categoria: { nome: "Material Eletrico", descricao: "Fios, tomadas, disjuntores" },
    fornecedor: { nome: "Eletro Componentes SA", cidade: "Caxias do Sul", telefone: "51 3111-3000" },
    movimentacoes: [
      { tipo: "ENTRADA", quantidade: 5, data: "2025-05-10", funcionario: "Marina Souza" },
      { tipo: "SAIDA",   quantidade: 3, data: "2025-05-28", funcionario: "Carlos Pereira" }
    ]
  },
  {
    nome: "Disjuntor Bipolar 25A",
    descricao: "Disjuntor DIN",
    preco_custo: 22.00,
    preco_venda: 39.90,
    qtd_estoque: 18,
    estoque_minimo: 8,
    categoria: { nome: "Material Eletrico", descricao: "Fios, tomadas, disjuntores" },
    fornecedor: { nome: "Eletro Componentes SA", cidade: "Caxias do Sul", telefone: "51 3111-3000" },
    movimentacoes: [
      { tipo: "ENTRADA", quantidade: 20, data: "2025-05-12", funcionario: "Marina Souza" }
    ]
  }
]);


db.produtos.find(
  { qtd_estoque: { $lt: 6 } },              
  { _id: 0, nome: 1, qtd_estoque: 1 }       
).sort({ qtd_estoque: 1 });                 



db.produtos.find(
  { "fornecedor.nome": "Eletro Componentes SA" },
  { _id: 0, nome: 1, preco_venda: 1, "fornecedor.nome": 1 }
).sort({ preco_venda: -1 });
