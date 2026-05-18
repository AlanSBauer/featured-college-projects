package paineldigital;

public class PainelDigital {
    private No inicio;
    private No fim;
    private No atual;
    private int tamanho;

    public PainelDigital(No inicio, No fim, No atual, int tamanho) {
        this.inicio = inicio;
        this.fim = fim;
        this.atual = atual;
        this.tamanho = tamanho;
    }

    public PainelDigital() {
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }

    public No getAtual() {
        return atual;
    }

    public void setAtual(No atual) {
        this.atual = atual;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void exibirEAvancar() {
        if (atual == null) {
            System.out.println("Lista vazia!");
            return;
        }

        System.out.println("ID: " + atual.getAnuncio().getId());
        System.out.println("Empresa: " + atual.getAnuncio().getEmpresa());
        System.out.println("Descrição: " + atual.getAnuncio().getDescricao());

        atual = atual.getProximo();
    }

    public void adicionar(Anuncio anuncio) {
        No novo = new No(anuncio, null);

        if (inicio == null) {
            inicio = novo;
            fim = novo;
            atual = novo;

            novo.setProximo(novo);
            tamanho++;
            return;
        }

        novo.setProximo(inicio);
        fim.setProximo(novo);
        fim = novo;

        tamanho++;
    }

    public void listar() {
        if (atual == null) {
            System.out.println("Lista vazia!");
            return;
        }

        No temp = atual;
        do {
            Anuncio anuncioAtual = temp.getAnuncio();
            System.out.println("ID: " + anuncioAtual.getId());
            System.out.println("Empresa: " + anuncioAtual.getEmpresa());
            System.out.println("Descrição: " + anuncioAtual.getDescricao());
            System.out.println("------------------");

            temp = temp.getProximo();
        } while (temp != atual);
    }

    public void remover(int id) {

        if (inicio == null) {
            System.out.println("Lista vazia!");
            return;
        }

        No atual = inicio;
        No anterior = fim;

        do {

            if (atual.getAnuncio().getId() == id) {

                if (tamanho == 1) {
                    inicio = null;
                    fim = null;
                    atual = null;
                    tamanho--;
                    return;
                }

                if (atual == inicio) {
                    inicio = inicio.getProximo();
                    fim.setProximo(inicio);
                } else if (atual == fim) {
                    fim = anterior;
                    fim.setProximo(inicio);
                } else {
                    anterior.setProximo(atual.getProximo());
                }

                if (this.atual == atual) {
                    this.atual = atual.getProximo();
                }

                tamanho--;
                System.out.println("Removido!");
                return;
            }

            anterior = atual;
            atual = atual.getProximo();

        } while (atual != inicio);

        System.out.println("ID não encontrado!");
    }

}
