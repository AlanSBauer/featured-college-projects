package exercicio4;

public class Veiculo {
    private String marca;
    private String modelo;
    private int ano;
    private float velocidadeAtual;

    public Veiculo(String marca, String modelo, int ano, float velocidadeAtual) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velocidadeAtual = velocidadeAtual;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public void setVelocidadeAtual(float velocidadeAtual) {
        this.velocidadeAtual = velocidadeAtual;
    }

    public void acelerar(float incremento) {
        if(incremento > 0 && incremento < 400) {
            this.setVelocidadeAtual(this.getVelocidadeAtual() + incremento);
        } else {
            System.out.println("Acelerando mais do que pode!");
        }
    }

    public void frear(float decremento) {
        if(decremento <= this.getVelocidadeAtual() && decremento > 0) {
            this.setVelocidadeAtual(this.getVelocidadeAtual() - decremento);
        } else {
            System.out.println("Freando mais do que consegue!");
        }
    }

    public void exibirInformacoes() {
        System.out.printf("Marca: %s, Modelo: %s, Ano: %d, Velocidade Atual: %.1f", this.getMarca(), this.getModelo(), this.getAno(), this.getVelocidadeAtual());
    }
}
