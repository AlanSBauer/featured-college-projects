package exercicio2;

public class PagamentoBoleto extends PagamentoBase  {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Boleto gerado. Pagamento será aprovado em até 24 horas.");
    }

    @Override
    public void estornar(int idTransacao) {
        super.estornar(idTransacao);
        System.out.println("Pagamento sendo estornado via Boleto.");
    }
}