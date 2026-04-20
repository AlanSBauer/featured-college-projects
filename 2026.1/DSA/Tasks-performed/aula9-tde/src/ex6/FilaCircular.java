package ex6;

import java.util.Arrays;

public class FilaCircular {
    private int[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;

    public FilaCircular(int capacidade) {
        this.elementos = new int[capacidade];
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

    // ENFILEIRAR
    public boolean enfileirar(int valor) {
        if (estaCheia()) {
            System.out.println("Fila cheia!");
            return false;
        }

        elementos[fim] = valor;
        fim = (fim + 1) % elementos.length;
        tamanho++;
        return true;
    }

    // DESENFILEIRAR
    public Integer desenfileirar() {
        if (estaVazia()) {
            System.out.println("Fila vazia!");
            return null;
        }

        int valor = elementos[inicio];
        inicio = (inicio + 1) % elementos.length;
        tamanho--;
        return valor;
    }
    public Integer espiar() {
        if (estaVazia()) {
            return null;
        }
        return elementos[inicio];
    }

    public int tamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementos);
    }
}