package personagem;

public abstract class Personagem {
    private String nome;

    public Personagem(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double bonusSalario(double valor) {
        return valor;
    }

    public double modificarAluguel(double valor) {
        return valor;
    }

    public double modificarImposto(double valor) {
        return valor;
    }

    public boolean podeSairSemFianca() {
        return false;
    }
}
