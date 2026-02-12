package exercicio2;

public class PagamentoBase implements Checkout {

    @Override
    public void processar(float valor) {
        System.out.println("Processando pagamento no valor de R$" + valor);
    }

    @Override
    public String estornar(String idTransacao) {
        return "Estorno realizado para transação: " + idTransacao;
    }

}
