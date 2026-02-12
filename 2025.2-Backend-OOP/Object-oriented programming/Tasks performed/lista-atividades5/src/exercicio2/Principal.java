package exercicio2;

public class Principal {
    public static void main(String[] args) {
        ServicoPagamento pagamento = new ServicoPagamento();
        PagamentoCartao cartao = new PagamentoCartao();
        PagamentoBoleto boleto = new PagamentoBoleto();
        PagamentoPix pix = new PagamentoPix();

        pagamento.realizarPagamento(cartao, 150f);
        pagamento.realizarEstorno(cartao, "1");

        System.out.println();

        pagamento.realizarPagamento(pix, 250f);
        pagamento.realizarEstorno(pix,"2");

        System.out.println();

        pagamento.realizarPagamento(boleto, 350f);
        pagamento.realizarEstorno(boleto, "3");
    }
}
