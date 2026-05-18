package pedidoscafeteria;

public class No {
    private Pedido pedido;
    private No proximo;

    public No(Pedido pedido, No proximo) {
        this.pedido = pedido;
        this.proximo = proximo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
