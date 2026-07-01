package estrutura;

import model.HistoricoRodada;

public class FilaHistorico {

    private NoHistorico inicio;
    private NoHistorico fim;
    private int tamanho;
    private int capacidade;

    public FilaHistorico(int capacidade) {
        this.capacidade = capacidade;
    }

    public void registrar(HistoricoRodada historico) {
        if (tamanho == capacidade) {
            desenfileirar();
        }

        NoHistorico novo = new NoHistorico(historico);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.setProximo(novo);
            fim = novo;
        }
        tamanho++;
    }

    private void desenfileirar() {
        if (inicio == null) {
            return;
        }

        inicio = inicio.getProximo();
        tamanho--;

        if (inicio == null) {
            fim = null;
        }
    }

    public void listar() {
        NoHistorico atual = inicio;

        while (atual != null) {
            System.out.println(atual.getHistorico());
            atual = atual.getProximo();
        }
    }
}