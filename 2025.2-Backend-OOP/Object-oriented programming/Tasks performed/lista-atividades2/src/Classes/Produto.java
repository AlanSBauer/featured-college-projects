package Classes;

public class Produto {
    private String nome;
    private float preco;
    private int quantidade;

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return this.preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return this.quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float calcularTotal() {
        return getPreco() * getQuantidade();
    }
}
