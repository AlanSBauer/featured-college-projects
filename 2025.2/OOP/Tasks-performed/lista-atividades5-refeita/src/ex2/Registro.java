package ex2;

public class Registro {
    private float valor;
    private int idTransacao;
    private String tipo;
    private Checkout metodo;

    public Registro(float valor, int idTransacao, String tipo) {
        this.valor = valor;
        this.idTransacao = idTransacao;
        this.tipo = tipo;
    }

    public Registro(float valor, int idTransacao, String tipo, Checkout metodo) {
        this.valor = valor;
        this.idTransacao = idTransacao;
        this.tipo = tipo;
        this.metodo = metodo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Checkout getMetodo() {
        return metodo;
    }

    public void setMetodo(Checkout metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        return  "ID: " + idTransacao + " Valor: " + valor + " Tipo: " + tipo + " Metodo:" + this.getMetodo().getClass().getSimpleName() ;
    }
}
