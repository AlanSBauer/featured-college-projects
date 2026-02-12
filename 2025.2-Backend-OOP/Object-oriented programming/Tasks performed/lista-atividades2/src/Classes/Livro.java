package Classes;

public class Livro {
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public String getTitulo() {
        return this.titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void emprestar() {
        if(this.disponivel) {
            this.disponivel = false;
            System.out.println("Livro emprestado!");
        } else {
            System.out.println("Livro indisponível para empréstimo!");
        }
    }

    public void devolver() {
        this.disponivel = true;
    }
}
