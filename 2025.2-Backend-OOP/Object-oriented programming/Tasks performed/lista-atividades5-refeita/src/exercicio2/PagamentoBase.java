package exercicio2;

public class PagamentoBase implements Checkout {
    @Override
    public void processar(float valor) {
        System.out.println("Pagamento Processado! Valor: R$" + valor);
    }

    @Override
    public void estornar(int idTransacao) {
        System.out.println("Estornando pagamento. Id transação: " + idTransacao);
    }
}