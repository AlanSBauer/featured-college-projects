package ex2;

import java.util.Arrays;

public class PilhaInteiros {
    private Integer[] elementos;
    private int tamanho;

    public PilhaInteiros(int capacidade){
        this.elementos = new Integer[capacidade];
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

    public boolean empilhar(Integer e) {
        if(!estaCheia()){
            this.elementos[this.tamanho] = e;
            this.tamanho++;
            return true;
        }

        return false;
    }

    public Integer desempilhar() {
        if(!estaVazia()) {
            this.tamanho--;
            return this.elementos[tamanho];
        }
        return null;
    }

    public Integer espiar() {
        if(!estaVazia()) {
            return  this.elementos[this.tamanho - 1];
        }
        return null;
    }

    @Override
    public String toString() {
        String texto = "[";

        for (int i = 0; i < this.tamanho; i++) {
            if(i != this.tamanho - 1) {
                texto += this.elementos[i] + ", ";
            } else {
                texto += this.elementos[i];
            }
        }
        texto += "]";

        return texto;
    }
}
