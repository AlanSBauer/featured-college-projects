package ex2;

public class PagamentoPix extends PagamentoBase {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Via pix: APROVADO!");
    }

    @Override
    public void estornar(int idTransacao) {
        super.estornar(idTransacao);
        System.out.println("Estorno sendo realizado via Pix!");
    }
}
