package ex2;

public class PagamentoBase implements Checkout {
    @Override
    public void processar(float valor) {
        System.out.println("Pagamento sendo processado no valor de R$" + valor);
    }

    @Override
    public void estornar(int idTransacao) {
        System.out.println("Id transação: " + idTransacao);
    }
}
