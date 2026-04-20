package ex3;

import java.util.Arrays;

public class Pilha {
    private char[] elementos;
    private int tamanho;

    public Pilha(int capacidade){
        this.elementos = new char[capacidade];
        this.tamanho = 0;
    }

    public int tamanho() {
        return this.tamanho;
    }

    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    public boolean estaCheia() {
        return this.tamanho == this.elementos.length;
    }

    public boolean empilhar(char e) {
        if(!estaCheia()){
            this.elementos[this.tamanho] = e;
            this.tamanho++;
            return true;
        }
        return false;
    }

    public Character desempilhar() {
        if(!estaVazia()) {
            this.tamanho--;
            return this.elementos[this.tamanho];
        }
        return null;
    }

    public Character espiar() {
        if(!estaVazia()) {
            return this.elementos[this.tamanho - 1];
        }
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementos);
    }
}