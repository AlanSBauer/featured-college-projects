package exercicio2;

public class PagamentoPix extends PagamentoBase {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Via Pix.");
    }

    @Override
    public String estornar(String idTransacao) {
        return super.estornar(idTransacao) + " - Via Pix.";
    }
}
