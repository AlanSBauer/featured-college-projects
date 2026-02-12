package exercicio2;

public class PagamentoCartao extends PagamentoBase {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("Via Cartão.");
        System.out.println("Taxa: X + 5%");
    }

    @Override
    public String estornar(String idTransacao) {
        return super.estornar(idTransacao) + " - Via Cartão.";
    }
}
