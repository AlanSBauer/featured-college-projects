package ex4;

import java.util.Arrays;

public class FilaClientes {
    private Cliente[] clientes;
    private int tamanho;

    public FilaClientes(int capacidade) {
        this.clientes = new Cliente[capacidade];
        this.tamanho = 0;
    }

    public int tamanho(){
        return tamanho;
    }
    public boolean estaVazia(){
        return tamanho == 0;
    }
    public boolean estaCheia(){
        return tamanho == this.clientes.length;
    }

    public boolean enfileirar(Cliente e){
        if(!estaCheia()){
            this.clientes[tamanho] = e;
            tamanho ++;
            return true;
        }
        return false;
    }

    public Cliente desenfileirar(){
        if(!estaVazia()){
            Cliente elementoRemovido = this.clientes[0];
            for(int i = 1; i < tamanho; i++){
                clientes[i - 1] = clientes[i];
            }
            tamanho--;
            return elementoRemovido;
        }
        return null;
    };

    public Cliente espiar(){
        if(!estaVazia()){
            return this.clientes[0];
        }
        return null;
    };

    @Override
    public String toString() {
        String texto = "[";
        for (int i = 0; i < tamanho; i++){
            if (i != tamanho -1){
                texto += clientes[i].getNome() + ", ";
            }else{
                texto += clientes[i].getNome();
            }
        }
        texto+= "]";
        return texto;
    }
}
