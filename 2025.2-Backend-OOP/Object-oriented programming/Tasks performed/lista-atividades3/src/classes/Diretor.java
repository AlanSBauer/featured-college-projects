package classes;

public class Diretor {
    private String nome;
    private String tempoDeServico;

    public Diretor(String nome, String tempoDeServico) {
        this.nome = nome;
        this.tempoDeServico = tempoDeServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTempoDeServico() {
        return tempoDeServico;
    }

    public void setTempoDeServico(String tempoDeServico) {
        this.tempoDeServico = tempoDeServico;
    }
}
