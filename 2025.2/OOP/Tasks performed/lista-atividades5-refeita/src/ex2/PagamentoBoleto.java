package ex2;

public class PagamentoBoleto extends PagamentoBase {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Via boleto: O pagamento pode ser aprovado em até 24 horas!");
    }

    @Override
    public void estornar(int idTransacao) {
        super.estornar(idTransacao);
        System.out.println("Estorno sendo realizado via Boleto!");
    }
}
