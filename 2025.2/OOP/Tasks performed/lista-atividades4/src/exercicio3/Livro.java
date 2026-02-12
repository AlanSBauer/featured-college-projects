package exercicio3;

public class Livro extends Publicacao {
    private int numPaginas;
    private String genero;

    public Livro(String titulo, String autor, int anoPublicacao, int numPaginas, String genero) {
        super(titulo, autor, anoPublicacao);
        this.numPaginas = numPaginas;
        this.genero = genero;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public void exibirInformacoes() {
        System.out.printf("Titulo: %s, Autor: %s, Ano de Publicação: %d, Numero de Paginas: %d, Genero: %s", this.getTitulo(), this.getAutor(), this.getAnoPublicacao(), this.getNumPaginas(), this.getGenero());
    }
}
