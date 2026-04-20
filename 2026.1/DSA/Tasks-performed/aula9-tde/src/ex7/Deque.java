package ex7;

import java.util.Arrays;

public class Deque {
    private int[] elementos;
    private int inicio;
    private int fim;
    private int tamanho;

    public Deque(int capacidade) {
        this.elementos = new int[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean estaVazio() {
        return tamanho == 0;
    }

    public boolean estaCheio() {
        return tamanho == elementos.length;
    }

    public int tamanho() {
        return tamanho;
    }

    public void limpar() {
        inicio = 0;
        fim = 0;
        tamanho = 0;
    }

    public boolean inserirNoInicio(int valor) {
        if (estaCheio()) {
            System.out.println("Deque cheio!");
            return false;
        }

        inicio = (inicio - 1 + elementos.length) % elementos.length;
        elementos[inicio] = valor;
        tamanho++;
        return true;
    }

    public boolean inserirNoFim(int valor) {
        if (estaCheio()) {
            System.out.println("Deque cheio!");
            return false;
        }

        elementos[fim] = valor;
        fim = (fim + 1) % elementos.length;
        tamanho++;
        return true;
    }

    public int removerDoInicio() {
        if (estaVazio()) {
            System.out.println("Deque vazio!");
            return -1;
        }

        int valor = elementos[inicio];
        inicio = (inicio + 1) % elementos.length;
        tamanho--;
        return valor;
    }

    public int removerDoFim() {
        if (estaVazio()) {
            System.out.println("Deque vazio!");
            return -1;
        }

        fim = (fim - 1 + elementos.length) % elementos.length;
        int valor = elementos[fim];
        tamanho--;
        return valor;
    }

    public int consultarInicio() {
        if (estaVazio()) {
            return -1;
        }
        return elementos[inicio];
    }

    public int consultarFim() {
        if (estaVazio()) {
            return -1;
        }

        int ultimo = (fim - 1 + elementos.length) % elementos.length;
        return elementos[ultimo];
    }

    @Override
    public String toString() {
        return Arrays.toString(elementos);
    }
}