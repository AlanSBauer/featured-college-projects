package exercicio2;

public class Animal {
    protected String nome;
    protected float comprimento;
    protected int numeroDePatas = 4;
    protected String cor;
    protected String Ambiente;
    protected float velocidade;

    public Animal(String nome, float comprimento, int numeroDePatas, String cor, String ambiente, float velocidade) {
        this.nome = nome;
        this.comprimento = comprimento;
        this.numeroDePatas = numeroDePatas;
        this.cor = cor;
        Ambiente = ambiente;
        this.velocidade = velocidade;
    }

    public Animal() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }

    public int getNumeroDePatas() {
        return numeroDePatas;
    }

    public void setNumeroDePatas(int numeroDePatas) {
        this.numeroDePatas = numeroDePatas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAmbiente() {
        return Ambiente;
    }

    public void setAmbiente(String ambiente) {
        Ambiente = ambiente;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public void alterarNome(String nome) {
        this.setNome(nome);
    }

    public void alterarComprimento(int comprimento) {
        this.setComprimento(comprimento);
    }

    public void alterarPatas(int patas) {
        this.setNumeroDePatas(patas);
    }

    public void alterarCor(String cor) {
        this.setCor(cor);
    }

    public void alterarAmbiente(String ambiente) {
        this.setAmbiente(ambiente);
    }

    public void alterarVelocidade(float velocidade) {
        this.setVelocidade(velocidade);
    }

    public void dados() {
        System.out.printf("Nome: %s, Comprimento: %.2fcm, Qtd Patas: %d, Cor: %s, Ambiente: %s, Velocidade: %.2fm/s", this.getNome(), this.getComprimento(), this.getNumeroDePatas(), this.getCor(), this.getAmbiente(), this.getVelocidade());
    }
}
