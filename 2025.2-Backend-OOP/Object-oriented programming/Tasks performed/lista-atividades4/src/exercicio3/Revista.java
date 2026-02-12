package exercicio3;

public class Revista extends Publicacao {
    private int edicao;
    private String mesPublicacao;

    public Revista(String titulo, String autor, int anoPublicacao, int edicao, String mesPublicacao) {
        super(titulo, autor, anoPublicacao);
        this.edicao = edicao;
        this.mesPublicacao = mesPublicacao;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getMesPublicacao() {
        return mesPublicacao;
    }

    public void setMesPublicacao(String mesPublicacao) {
        this.mesPublicacao = mesPublicacao;
    }

    @Override
    public void exibirInformacoes() {
        System.out.printf("Titulo: %s, Autor: %s, Ano de Publicação: %d, Edição: %d, Mês de Publicação: %s", this.getTitulo(), this.getAutor(), this.getAnoPublicacao(), this.getEdicao(), this.getMesPublicacao());
    }
}
