package classes;

public class Livro {
    private String nome;
    private int anoPublicacao;
    private Autor autor;

    public Livro(String nome, int anoPublicacao, Autor autor) {
        this.nome = nome;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String descricao() {
        return "Titulo do livro: " + this.getNome() + " | Autor: " + this.getAutor().getNome();
    }
}
