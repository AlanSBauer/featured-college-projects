-- =====================================================================
-- PROJETO: Sistema de Controle de Estoque - Central das Ferragens
-- SGBD: MySQL (Banco de Dados Relacional)
-- Autor: Leonardo Wust
-- Disciplina: Modelagem e Implementacao de Banco de Dados
-- =====================================================================

-- ---------------------------------------------------------------------
-- 1. CRIACAO DO BANCO DE DADOS
-- ---------------------------------------------------------------------
DROP DATABASE IF EXISTS central_ferragens;
CREATE DATABASE central_ferragens
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE central_ferragens;

-- ---------------------------------------------------------------------
-- 2. CRIACAO DAS TABELAS
-- ---------------------------------------------------------------------

-- Tabela CATEGORIA
-- Agrupa os produtos por tipo (ex.: Parafusos, Ferramentas, Tintas).
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT,
    nome         VARCHAR(60)  NOT NULL,
    descricao    VARCHAR(200),
    CONSTRAINT pk_categoria PRIMARY KEY (id_categoria),
    CONSTRAINT uk_categoria_nome UNIQUE (nome)
);

-- Tabela FORNECEDOR
-- Empresas que fornecem os produtos para a loja.
CREATE TABLE fornecedor (
    id_fornecedor INT AUTO_INCREMENT,
    nome          VARCHAR(100) NOT NULL,
    cnpj          CHAR(14)     NOT NULL,
    telefone      VARCHAR(20),
    email         VARCHAR(100),
    cidade        VARCHAR(60),
    CONSTRAINT pk_fornecedor PRIMARY KEY (id_fornecedor),
    CONSTRAINT uk_fornecedor_cnpj UNIQUE (cnpj)
);

-- Tabela FUNCIONARIO
-- Quem opera o sistema e registra as movimentacoes de estoque.
CREATE TABLE funcionario (
    id_funcionario INT AUTO_INCREMENT,
    nome           VARCHAR(100) NOT NULL,
    cargo          VARCHAR(50),
    data_admissao  DATE,
    CONSTRAINT pk_funcionario PRIMARY KEY (id_funcionario)
);

-- Tabela PRODUTO
-- Itens em estoque. Cada produto pertence a UMA categoria e tem UM
-- fornecedor principal (relacionamentos 1:N a partir das duas tabelas acima).
CREATE TABLE produto (
    id_produto       INT AUTO_INCREMENT,
    nome             VARCHAR(120)   NOT NULL,
    descricao        VARCHAR(250),
    preco_custo      DECIMAL(10,2)  NOT NULL DEFAULT 0.00,
    preco_venda      DECIMAL(10,2)  NOT NULL DEFAULT 0.00,
    qtd_estoque      INT            NOT NULL DEFAULT 0,
    estoque_minimo   INT            NOT NULL DEFAULT 0,
    id_categoria     INT            NOT NULL,
    id_fornecedor    INT            NOT NULL,
    CONSTRAINT pk_produto PRIMARY KEY (id_produto),
    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (id_categoria)  REFERENCES categoria (id_categoria),
    CONSTRAINT fk_produto_fornecedor
        FOREIGN KEY (id_fornecedor) REFERENCES fornecedor (id_fornecedor)
);

-- Tabela MOVIMENTACAO_ESTOQUE
-- Registra cada entrada ou saida de estoque. E a tabela "fato" do sistema:
-- liga PRODUTO (o que mexeu) e FUNCIONARIO (quem registrou).
CREATE TABLE movimentacao_estoque (
    id_movimentacao INT AUTO_INCREMENT,
    id_produto      INT          NOT NULL,
    id_funcionario  INT          NOT NULL,
    tipo            ENUM('ENTRADA','SAIDA') NOT NULL,
    quantidade      INT          NOT NULL,
    data_hora       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    observacao      VARCHAR(200),
    CONSTRAINT pk_movimentacao PRIMARY KEY (id_movimentacao),
    CONSTRAINT fk_mov_produto
        FOREIGN KEY (id_produto)     REFERENCES produto (id_produto),
    CONSTRAINT fk_mov_funcionario
        FOREIGN KEY (id_funcionario) REFERENCES funcionario (id_funcionario),
    CONSTRAINT ck_mov_quantidade CHECK (quantidade > 0)
);

-- ---------------------------------------------------------------------
-- 3. DADOS DE EXEMPLO (para as consultas e views funcionarem)
-- ---------------------------------------------------------------------

INSERT INTO categoria (nome, descricao) VALUES
('Parafusos e Fixadores', 'Parafusos, buchas, porcas e arruelas'),
('Ferramentas Manuais',   'Martelos, chaves, alicates'),
('Tintas e Acessorios',   'Tintas, pinceis, rolos e solventes'),
('Material Eletrico',     'Fios, tomadas, disjuntores');

INSERT INTO fornecedor (nome, cnpj, telefone, email, cidade) VALUES
('Distribuidora Sul Ferragens', '12345678000190', '51 3333-1000', 'vendas@sulferragens.com.br', 'Porto Alegre'),
('Tintas Litoral LTDA',         '98765432000155', '51 3222-2000', 'contato@tintaslitoral.com.br', 'Osorio'),
('Eletro Componentes SA',       '45678912000133', '51 3111-3000', 'comercial@eletrocomp.com.br', 'Caxias do Sul');

INSERT INTO funcionario (nome, cargo, data_admissao) VALUES
('Leonardo Wust',  'Estoquista',   '2023-02-01'),
('Carlos Pereira', 'Vendedor',     '2021-08-15'),
('Marina Souza',   'Gerente',      '2019-05-10');

INSERT INTO produto (nome, descricao, preco_custo, preco_venda, qtd_estoque, estoque_minimo, id_categoria, id_fornecedor) VALUES
('Parafuso Phillips 4x40mm (cento)', 'Caixa com 100 unidades', 12.00, 22.90,  8,  10, 1, 1),
('Bucha de Nylon 8mm (pacote)',      'Pacote com 50 unidades',  9.50, 18.00, 40,  15, 1, 1),
('Martelo Unha 25mm cabo madeira',   'Martelo carpinteiro',     28.00, 49.90,  5,   6, 2, 1),
('Alicate Universal 8 pol',          'Alicate isolado',         34.00, 59.90, 12,   5, 2, 1),
('Tinta Acrilica Branca 18L',        'Lata 18 litros',         140.00, 219.90, 3,   4, 3, 2),
('Rolo de Pintura 23cm',             'Rolo la sintetica',        7.00, 14.90, 25,  10, 3, 2),
('Fio Flexivel 2,5mm (rolo 100m)',   'Rolo 100 metros',         95.00, 149.90, 2,   5, 4, 3),
('Disjuntor Bipolar 25A',            'Disjuntor DIN',           22.00, 39.90, 18,   8, 4, 3);

INSERT INTO movimentacao_estoque (id_produto, id_funcionario, tipo, quantidade, data_hora, observacao) VALUES
(1, 1, 'ENTRADA', 50, '2025-05-02 09:10:00', 'Reposicao de estoque'),
(1, 2, 'SAIDA',   42, '2025-05-20 14:30:00', 'Venda balcao'),
(3, 1, 'ENTRADA', 10, '2025-05-05 10:00:00', 'Compra fornecedor'),
(3, 2, 'SAIDA',    5, '2025-05-22 16:45:00', 'Venda balcao'),
(5, 1, 'ENTRADA',  6, '2025-05-08 08:30:00', 'Compra fornecedor'),
(5, 2, 'SAIDA',    3, '2025-05-25 11:20:00', 'Venda obra'),
(7, 3, 'ENTRADA',  5, '2025-05-10 09:00:00', 'Compra fornecedor'),
(7, 2, 'SAIDA',    3, '2025-05-28 15:10:00', 'Venda eletricista');
