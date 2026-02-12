package exercicio2;

public class Peixe extends Animal {
    private String caracteristica;

    public Peixe(String nome, float comprimento, int numeroDePatas, String cor, String ambiente, float velocidade, String caracteristica) {
        super(nome, comprimento, numeroDePatas, cor, ambiente, velocidade);
        this.caracteristica = caracteristica;
    }

    public Peixe(String nome, float comprimento) {
        super(nome, comprimento, 0, "Cinzenta", "Mar", 0);
        this.caracteristica = "Barbatanas e cauda";
    }

    public Peixe() {
        super("", 0, 0, "Cinzenta", "Mar", 0);
        this.caracteristica = "Barbatanas e cauda";
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public void alterarCaracteristica(String caracteristica) {
        this.setCaracteristica(caracteristica);
    }

    public String caracteristica() {
        return "Caracteristicas: " + this.getCaracteristica();
    }

    @Override
    public void dados() {
        System.out.printf("Nome: %s, Comprimento: %.2fcm, Qtd Patas: %d, Cor: %s, Ambiente: %s, Velocidade: %.2fm/s, Caracteristicas: %s", this.getNome(), this.getComprimento(), this.getNumeroDePatas(), this.getCor(), this.getAmbiente(), this.getVelocidade(), this.getCaracteristica());
    }

}
