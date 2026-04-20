package ex2;

import java.util.Arrays;

public class Pilha {
    private String[] elementos;
    private int tamanho;

    public Pilha(int capacidade){
        this.elementos = new String[capacidade];
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

    public boolean empilhar(String e) {
        if(!estaCheia()){
            this.elementos[this.tamanho] = e;
            this.tamanho++;
            return true;
        }

        return false;
    }

    public String desempilhar() {
        if(!estaVazia()) {
            this.tamanho--;
            return this.elementos[tamanho];
        }
        return null;
    }

    public String espiar() {
        if(!estaVazia()) {
            return  this.elementos[this.tamanho - 1];
        }
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementos);
    }
}
