package classes;

public class Processador {
    private String marca;
    public String geracao;
    private float velocidadeGhz;

    public Processador(String marca, float velocidadeGhz, String geracao) {
        this.marca = marca;
        this.velocidadeGhz = velocidadeGhz;
        this.geracao = geracao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getVelocidadeGhz() {
        return velocidadeGhz;
    }

    public void setVelocidadeGhz(float velocidadeGhz) {
        this.velocidadeGhz = velocidadeGhz;
    }

    public String getGeracao() {
        return geracao;
    }

    public void setGeracao(String geracao) {
        this.geracao = geracao;
    }
}
