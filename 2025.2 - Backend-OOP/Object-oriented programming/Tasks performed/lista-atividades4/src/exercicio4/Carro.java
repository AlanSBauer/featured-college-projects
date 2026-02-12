package exercicio4;

public class Carro extends Veiculo {
    private int portas;

    public Carro(String marca, String modelo, int ano, float velocidadeAtual, int portas) {
        super(marca, modelo, ano, velocidadeAtual);
        this.portas = portas;
    }

    public int getPortas() {
        return portas;
    }

    public void setPortas(int portas) {
        this.portas = portas;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.printf(", Numero de portas: %d", this.getPortas());
    }
}
