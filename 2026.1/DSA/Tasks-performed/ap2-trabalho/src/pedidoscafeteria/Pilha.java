package pedidoscafeteria;

public class Pilha {
    private No topo;
    private int tamanho;

    public Pilha () {
        this.topo = null;
        this.tamanho = 0;
    }

    public No getTopo() {
        return topo;
    }

    public void setTopo(No topo) {
        this.topo = topo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void push(Pedido pedido) {
        No novoNo = new No(pedido, topo);
        topo = novoNo;
        tamanho++;
    }

    public Pedido pop() {
        if(tamanho == 0) return null;

        Pedido removido = topo.getPedido();
        tamanho --;
        topo = topo.getProximo();
        return removido;
    }

    public void printStack() {
        No atual = topo;

        if(atual == null) {
            System.out.println("A pilha está vazia!");
        }

        while(atual != null) {
            System.out.println("ID: " + atual.getPedido().getId() + " Descrição: " + atual.getPedido().getDescricao());
            atual = atual.getProximo();
        }
    }
}
