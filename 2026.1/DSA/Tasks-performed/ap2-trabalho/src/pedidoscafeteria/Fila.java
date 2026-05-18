package pedidoscafeteria;

public class Fila {
    private No inicio;
    private No fim;
    private int tamanho;

    public Fila() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
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

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void enqueue(Pedido pedido) {
        No novoNo = new No(pedido, null);

        if(tamanho == 0) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.setProximo(novoNo);
            fim = novoNo;
        }

        tamanho++;
    }

    public Pedido dequeue() {
        if(tamanho == 0) {
            return null;
        } else {
            Pedido removido = inicio.getPedido();
            inicio = inicio.getProximo();
            if(inicio == null) {
                fim = null;
            }
            tamanho--;
            return removido;
        }
    }

    public void printQueue() {
        No atual = inicio;

        if(atual == null) {
            System.out.println("A fila está vazia!");
        }

        while (atual != null) {
            System.out.println("ID: " + atual.getPedido().getId() + " Descrição: " + atual.getPedido().getDescricao());
            atual = atual.getProximo();
        }
    }


}
