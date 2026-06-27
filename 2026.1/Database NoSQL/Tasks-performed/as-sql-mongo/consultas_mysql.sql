
USE central_ferragens;


SELECT
    p.nome          AS produto,
    c.nome          AS categoria,
    f.nome          AS fornecedor,
    p.qtd_estoque,
    p.estoque_minimo
FROM produto p
JOIN categoria  c ON c.id_categoria  = p.id_categoria
JOIN fornecedor f ON f.id_fornecedor = p.id_fornecedor
WHERE p.qtd_estoque <= p.estoque_minimo
ORDER BY p.qtd_estoque ASC;



SELECT
    p.nome          AS produto,
    p.preco_venda,
    p.qtd_estoque
FROM produto p
JOIN categoria c ON c.id_categoria = p.id_categoria
WHERE c.nome = 'Ferramentas Manuais'
ORDER BY p.preco_venda DESC;
