package exercicio4;

public class Arquivo {
    private String dados;
    private String destino;

    public Arquivo(String dados, String destino) {
        this.dados = dados;
        this.destino = destino;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Arquivo{" +
                "dados='" + dados + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}
