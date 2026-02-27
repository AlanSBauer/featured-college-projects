package exercicio2;

public class PagamentoPix extends PagamentoBase  {
    @Override
    public void processar(float valor) {
        super.processar(valor);
        System.out.println("PIX: aprovado instantaneamente!");
    }

    @Override
    public void estornar(int idTransacao) {
        super.estornar(idTransacao);
        System.out.println("Pagamento sendo estornado via Pix.");
    }
}