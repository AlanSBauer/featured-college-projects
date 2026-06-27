package estruturas;

import model.Casa;

public class ListaCircular {
    private NoCasa inicio;
    private NoCasa fim;
    private int tamanho;

    public ListaCircular() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public NoCasa getInicio() {
        return inicio;
    }

    public void setInicio(NoCasa inicio) {
        this.inicio = inicio;
    }

    public NoCasa getFim() {
        return fim;
    }

    public void setFim(NoCasa fim) {
        this.fim = fim;
    }

    public void listar() {
        if(inicio == null) {
            System.out.println("Lista vazia!");
            return;
        }

        NoCasa auxInicio = inicio;

        do {
            System.out.println(auxInicio.getCasa());
            auxInicio = auxInicio.getProximo();
        } while (auxInicio != inicio);
    }

    public void adicionarCasa(Casa casa) {
        NoCasa novaCasa = new NoCasa(casa);

        if(tamanho == 0) {
            inicio = novaCasa;
            fim = novaCasa;

            inicio.setProximo(inicio);
            inicio.setAnterior(inicio);
        } else {
            novaCasa.setAnterior(fim);
            novaCasa.setProximo(inicio);

            fim.setProximo(novaCasa);
            inicio.setAnterior(novaCasa);

            fim = novaCasa;
        }
        tamanho++;
    }

    public NoCasa avancar(NoCasa atual, int passos) {
        NoCasa atualAux = atual;

        for(int i = 0; i < passos; i++) {
            atualAux = atualAux.getProximo();
        }

        return atualAux;
    }

    public NoCasa retroceder(NoCasa atual, int passos) {
        NoCasa atualAux = atual;

        for(int i = 0; i < passos; i++) {
            atualAux = atualAux.getAnterior();
        }

        return atualAux;
    }
}
