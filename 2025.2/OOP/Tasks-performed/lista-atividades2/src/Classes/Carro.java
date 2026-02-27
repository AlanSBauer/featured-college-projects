package Classes;

public class Carro {
    private String modelo;
    private int ano;
    private int velocidade;

    public Carro(String modelo, int ano) {
        this.modelo = modelo;
        this.ano = ano;
        this.velocidade = 0;
    }

    public String getModelo() {
        return this.modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return this.ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getVelocidade() {
        return this.velocidade;
    }
    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public void acelerar() {
        this.velocidade += 10;
    }

    public void frear() {
        if(this.velocidade >= 10) {
            this.velocidade -= 10;
        } else if (this.velocidade >= 5) {
            this.velocidade -= 5;
        } else {
            System.out.println("Carro ja está parado!");
        }
    }

    public void status() {
        System.out.println("Velocidade atual: " + getVelocidade());
    }
}
