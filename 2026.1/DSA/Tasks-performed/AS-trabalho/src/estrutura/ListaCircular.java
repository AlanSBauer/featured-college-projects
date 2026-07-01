package estrutura;

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

    public int getTamanho() {
        return tamanho;
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

    public void listarComLigacoes() {
        if(inicio == null) {
            System.out.println("Lista vazia!");
            return;
        }

        NoCasa atual = inicio;
        int indice = 1;
        int totalInicio = 0;
        int totalImovel = 0;
        int totalImposto = 0;
        int totalRestituicao = 0;
        int totalPrisao = 0;
        int totalLeilao = 0;
        int totalSorteReves = 0;

        do {
            System.out.println(indice + ". " + atual.getCasa().getNome()
                    + " [" + atual.getCasa().getTipo() + "]"
                    + " | anterior: " + atual.getAnterior().getCasa().getNome()
                    + " | proximo: " + atual.getProximo().getCasa().getNome());

            switch (atual.getCasa().getTipo()) {
                case INICIO:
                    totalInicio++;
                    break;
                case IMOVEL:
                    totalImovel++;
                    break;
                case IMPOSTO:
                    totalImposto++;
                    break;
                case RESTITUICAO:
                    totalRestituicao++;
                    break;
                case PRISAO:
                    totalPrisao++;
                    break;
                case LEILAO:
                    totalLeilao++;
                    break;
                case SORTE_REVES:
                    totalSorteReves++;
                    break;
            }

            atual = atual.getProximo();
            indice++;
        } while (atual != inicio);

        System.out.println("Circularidade: " + fim.getCasa().getNome()
                + " -> " + inicio.getCasa().getNome());
        System.out.println("Resumo: " + tamanho + " casas | Inicio: " + totalInicio
                + " | Imoveis: " + totalImovel
                + " | Sorte/Reves: " + totalSorteReves
                + " | Imposto: " + totalImposto
                + " | Restituicao: " + totalRestituicao
                + " | Prisao: " + totalPrisao
                + " | Leilao: " + totalLeilao);
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

    public NoCasa avancar(NoCasa atual, double passos) {
        NoCasa atualAux = atual;

        for(int i = 0; i < passos; i++) {
            atualAux = atualAux.getProximo();
        }

        return atualAux;
    }

    public NoCasa retroceder(NoCasa atual, double passos) {
        NoCasa atualAux = atual;

        for(int i = 0; i < passos; i++) {
            atualAux = atualAux.getAnterior();
        }

        return atualAux;
    }

    @Override
    public String toString() {
        return "ListaCircular{" +
                "inicio=" + inicio +
                ", fim=" + fim +
                ", tamanho=" + tamanho +
                '}';
    }
}
