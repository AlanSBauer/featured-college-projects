package exercicio2;

public class PagamentoBoleto extends PagamentoBase {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Via Boleto.");
    }

    @Override
    public String estornar(String idTransacao) {
        return super.estornar(idTransacao) + " - Via Boleto.";
    }
}
