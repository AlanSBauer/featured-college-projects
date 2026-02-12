package ex2;

public class PagamentoCartao extends PagamentoBase {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Via Cartao: cobra taxa fixa + %");
    }

    @Override
    public void estornar(int idTransacao) {
        super.estornar(idTransacao);
        System.out.println("Estorno sendo realizado via Cartao!");
    }
}
