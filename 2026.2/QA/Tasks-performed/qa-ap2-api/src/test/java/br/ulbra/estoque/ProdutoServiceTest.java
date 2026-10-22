package br.ulbra.estoque;

import br.ulbra.estoque.modelo.Produto;
import br.ulbra.estoque.repositorio.ProdutoRepository;
import br.ulbra.estoque.servico.ProdutoNaoEncontradoException;
import br.ulbra.estoque.servico.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    private ProdutoService service;

    @BeforeEach
    void prepararCenario() {
        service = new ProdutoService(repository);
    }

    @Test
    @DisplayName("Não deve cadastrar produto sem nome")
    void naoDeveCadastrarProdutoSemNome() {
        // Aqui eu crio o produto para o teste
        Produto produto = new Produto("", 10.0, 5);

        // Aqui o assert throws verifica se com o produto sem nome ta disparando a excessao, se tiver o teste passou, se nao tem algum problema
        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar(produto));

        // aqui é verificado se a repository nem chega a chamar o metodo de salvar, porque ela nao deve salvar algo errado.
        verify(repository, never()).salvar(any());
    }

    @Test
    @DisplayName("Não deve cadastrar produto com preço zero")
    void naoDeveCadastrarProdutoComPrecoZero() {
        Produto produto = new Produto("Produto", 0, 5);

        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar(produto));

        verify(repository, never()).salvar(any());
    }

    @Test
    @DisplayName("Não permite baixa maior que o estoque")
    void naoDeveDarBaixaComEstoqueInsuficiente() {
        Produto produto = new Produto("Produto", 99, 5);

        when(repository.buscarPorId(1L))
                .thenReturn(Optional.of(produto));

        assertThrows(IllegalArgumentException.class,
                () -> service.darBaixa(1L, 10));

        verify(repository, never()).salvar(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar produto inexistente")
    void deveLancarExcecaoAoBuscarProdutoInexistente() {
        when(repository.buscarPorId(55L))
                .thenReturn(Optional.empty());

        assertThrows(ProdutoNaoEncontradoException.class,
                () -> service.buscarPorId(55L));

        verify(repository).buscarPorId(55L);
    }
}