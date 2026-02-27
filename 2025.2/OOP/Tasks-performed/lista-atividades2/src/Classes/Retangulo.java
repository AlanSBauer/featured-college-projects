package Classes;

public class Retangulo {
    private float largura;
    private float altura;

    public Retangulo(float largura, float altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public float getLargura() {
        return this.largura;
    }
    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getAltura() {
        return this.altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float calcularPerimetro() {
        return  2 * (getLargura() + getAltura());
    }
    public float calcularArea() {
        return getAltura() * getLargura();
    }
}