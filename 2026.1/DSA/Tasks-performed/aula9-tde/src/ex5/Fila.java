package ex5;

import java.util.Arrays;

public class Fila {
    private Documento[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;

    public Fila(int capacidade) {
        this.elementos = new Documento[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public boolean estaCheia() {
        return tamanho == elementos.length;
    }

    public boolean enfileirar(Documento doc) {
        if (!estaCheia()) {
            elementos[fim] = doc;
            fim = (fim + 1) % elementos.length;
            tamanho++;
            return true;
        }
        return false;
    }

    public Documento desenfileirar() {
        if (!estaVazia()) {
            Documento doc = elementos[inicio];
            inicio = (inicio + 1) % elementos.length;
            tamanho--;
            return doc;
        }
        return null;
    }

    public Documento espiar() {
        if (!estaVazia()) {
            return elementos[inicio];
        }
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementos);
    }
}