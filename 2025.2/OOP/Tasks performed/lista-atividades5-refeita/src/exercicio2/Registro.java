package exercicio2;

public class Registro {
    private int id;
    private String tipo;
    private Checkout metodo;
    private float valor;

    public Registro(int id, String tipo, Checkout metodo, float valor) {
        this.id = id;
        this.tipo = tipo;
        this.metodo = metodo;
        this.valor = valor;
    }

    public Registro() {
    }

    public Registro(String tipo, Checkout metodo, float valor) {
        this.tipo = tipo;
        this.metodo = metodo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + tipo + " - " + this.getMetodo().getClass().getSimpleName() + " - R$" + valor;
    }
}
