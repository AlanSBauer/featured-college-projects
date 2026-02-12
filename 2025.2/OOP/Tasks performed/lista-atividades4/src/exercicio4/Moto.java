package exercicio4;

public class Moto extends Veiculo {
    private boolean partidaEletrica;

    public Moto(String marca, String modelo, int ano, float velocidadeAtual, boolean partidaEletrica) {
        super(marca, modelo, ano, velocidadeAtual);
        this.partidaEletrica = partidaEletrica;
    }

    public boolean getPartidaEletrica() {
        return partidaEletrica;
    }

    public void setPartidaEletrica(boolean partidaEletrica) {
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.printf(", Partida elética: %b", this.getPartidaEletrica());
    }
}
