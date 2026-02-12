package exercicio2;

public class Mamifero extends Animal {
    private String alimento;

    public Mamifero(String nome, float comprimento, int numeroDePatas, String cor, String ambiente, float velocidade, String alimento) {
        super(nome, comprimento, numeroDePatas, cor, ambiente, velocidade);
        this.alimento = alimento;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public void alterarAlimento(String alimento) {
        this.setAlimento(alimento);
    }

    public String alimento() {
        return "Alimento: " + this.getAlimento();
    }

    @Override
    public void dados() {
        System.out.printf("Nome: %s, Comprimento: %.2fcm, Qtd Patas: %d, Cor: %s, Ambiente: %s, Velocidade: %.2fm/s, Alimento: %s", this.getNome(), this.getComprimento(), this.getNumeroDePatas(), this.getCor(), this.getAmbiente(), this.getVelocidade(), this.getAlimento());
    }
}
