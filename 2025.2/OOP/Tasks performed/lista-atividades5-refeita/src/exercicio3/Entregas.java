package exercicio3;

public class Entregas {
    private float peso;
    private String cep;

    public Entregas(float peso, String cep) {
        this.peso = peso;
        this.cep = cep;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Entregas{" +
                "peso=" + peso +
                ", cep='" + cep + '\'' +
                '}';
    }
}
