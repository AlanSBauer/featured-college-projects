package classes;

public class Celular {
    private String marca;
    private String modelo;
    private Bateria bateria;

    public Celular(String marca, String modelo, Bateria bateria) {
        this.marca = marca;
        this.modelo = modelo;
        this.bateria = bateria;
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

    public Bateria getBateria() {
        return bateria;
    }

    public void setBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    public void usar() {
        if(this.getBateria().getPorcentagemAtual() > 0) {
            System.out.println("-1%");
            this.getBateria().setPorcentagemAtual(getBateria().getPorcentagemAtual() -1);

        } else {
            System.out.println("Bateria esgotada!");
        }
    }

    public void carregar() {
        this.getBateria().setPorcentagemAtual(100);
    }
}
