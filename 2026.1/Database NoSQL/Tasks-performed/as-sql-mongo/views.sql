-- =====================================================================
-- VIEWS - Sistema de Controle de Estoque (Central das Ferragens)
-- Requer: banco central_ferragens ja criado (script_mysql.sql)
-- =====================================================================

USE central_ferragens;

-- ---------------------------------------------------------------------
-- VIEW 1: vw_produtos_estoque_baixo
-- Relatorio de reposicao: produtos cuja quantidade em estoque esta
-- abaixo (ou igual) do estoque minimo, ja com os dados de contato do
-- fornecedor para facilitar o pedido de compra.
-- ---------------------------------------------------------------------
CREATE OR REPLACE VIEW vw_produtos_estoque_baixo AS
SELECT
    p.id_produto,
    p.nome              AS produto,
    c.nome              AS categoria,
    p.qtd_estoque,
    p.estoque_minimo,
    (p.estoque_minimo - p.qtd_estoque) AS faltam,
    f.nome              AS fornecedor,
    f.telefone          AS contato_fornecedor
FROM produto p
JOIN categoria  c ON c.id_categoria  = p.id_categoria
JOIN fornecedor f ON f.id_fornecedor = p.id_fornecedor
WHERE p.qtd_estoque <= p.estoque_minimo
ORDER BY faltam DESC;

-- ---------------------------------------------------------------------
-- VIEW 2: vw_relatorio_movimentacao
-- Historico de movimentacoes legivel: troca os IDs pelos nomes do
-- produto e do funcionario, e calcula o valor financeiro da saida
-- (quantidade x preco de venda) para acompanhamento.
-- ---------------------------------------------------------------------
CREATE OR REPLACE VIEW vw_relatorio_movimentacao AS
SELECT
    m.id_movimentacao,
    m.data_hora,
    p.nome              AS produto,
    func.nome           AS funcionario,
    m.tipo,
    m.quantidade,
    p.preco_venda,
    (m.quantidade * p.preco_venda) AS valor_total
FROM movimentacao_estoque m
JOIN produto     p    ON p.id_produto     = m.id_produto
JOIN funcionario func ON func.id_funcionario = m.id_funcionario
ORDER BY m.data_hora DESC;

-- ---------------------------------------------------------------------
-- Como usar as views:
-- SELECT * FROM vw_produtos_estoque_baixo;
-- SELECT * FROM vw_relatorio_movimentacao;
-- =====================================================================
