package exercicio2;

public class PagamentoCartao extends PagamentoBase {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Cartão: cobrando taxa fixa + % sobre o valor.");
    }

    @Override
    public void estornar(int idTransacao) {
        super.estornar(idTransacao);
        System.out.println("Pagamento sendo estornado via Cartão.");
    }
}