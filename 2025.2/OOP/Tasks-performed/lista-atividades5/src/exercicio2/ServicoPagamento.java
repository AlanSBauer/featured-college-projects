package exercicio2;

public class ServicoPagamento {
    public void realizarPagamento(Checkout formaDePagamento, float valor) {
        formaDePagamento.processar(valor);
    }

    public void realizarEstorno(Checkout formaDePagamento, String idTransacao) {
        String resultado = formaDePagamento.estornar(idTransacao);
        System.out.println(resultado);
    }
}
