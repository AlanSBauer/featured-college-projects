package exercicio5;

public abstract class MidiaBase implements Midia {
    private String titulo;
    private String autor;
    private String url;
    private int duracao;

    public MidiaBase(String titulo, String autor, String url) {
        this.titulo = titulo;
        this.autor = autor;
        this.url = url;
    }

    public MidiaBase() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + ", Autor: " + autor + ", Url: " + url + ", Duracao: " + duracao;
    }
}
