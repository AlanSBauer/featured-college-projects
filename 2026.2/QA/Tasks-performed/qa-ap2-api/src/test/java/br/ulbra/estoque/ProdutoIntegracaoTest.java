package br.ulbra.estoque;

import br.ulbra.estoque.modelo.Produto;
import br.ulbra.estoque.repositorio.ProdutoRepositoryEmMemoria;
import br.ulbra.estoque.servico.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoIntegracaoTest {

    private ProdutoService service;

    @BeforeEach
    void prepararCenario() {
        service = new ProdutoService(new ProdutoRepositoryEmMemoria());
    }

    @Test
    @DisplayName("Deve cadastrar e buscar um produto corretamente")
    void deveCadastrarEBuscarProduto() {
        Produto produto = new Produto("Mouse", 50, 10);

        Produto cadastrado = service.cadastrar(produto);
        Produto encontrado = service.buscarPorId(cadastrado.getId());

        assertEquals("Mouse", encontrado.getNome());
        assertEquals(50.0, encontrado.getPreco());
        assertEquals(10, encontrado.getEstoque());
    }

    @Test
    @DisplayName("Deve atualizar o estoque após dar baixa")
    void deveAtualizarEstoqueAoDarBaixa() {
        Produto produto = new Produto("Produto", 100, 20);

        Produto produtoCadastrado = service.cadastrar(produto);
        Produto produtoEncontrado = service.darBaixa(produtoCadastrado.getId(), 5);

        service.buscarPorId(produtoCadastrado.getId());

        assertEquals(15, produtoEncontrado.getEstoque());
    }
}