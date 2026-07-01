package estrutura;

import model.Jogador;

public class FilaPrisao {
    private NoJogador inicio;
    private NoJogador fim;
    private int tamanho;

    public FilaPrisao() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public NoJogador getInicio() {
        return inicio;
    }

    public NoJogador getFim() {
        return fim;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void enfileirar(Jogador jogador) {
        NoJogador novoNo = new NoJogador(jogador);

        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.setProximo(novoNo);
            fim = novoNo;
        }

        tamanho++;
    }

    public Jogador desenfileirar() {
        if (estaVazia()) {
            return null;
        }

        Jogador jogador = inicio.getJogador();

        inicio = inicio.getProximo();
        tamanho--;

        if (inicio == null) {
            fim = null;
        }

        return jogador;
    }

    public Jogador espiar() {
        if (inicio == null) {
            return null;
        }

        return inicio.getJogador();
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public void listar() {
        if (inicio == null) {
            System.out.println("A fila de prisão está vazia.");
            return;
        }

        NoJogador atual = inicio;
        System.out.println("Jogadores na fila de prisão:");
        while (atual != null) {
            System.out.println("- " + atual.getJogador().getNome());
            atual = atual.getProximo();
        }
    }
}