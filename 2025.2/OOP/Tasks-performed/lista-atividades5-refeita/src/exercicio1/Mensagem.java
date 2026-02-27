package exercicio1;

public class Mensagem {
    public String mensagem;
    public String destino;

    public Mensagem(String mensagem, String destino) {
        this.mensagem = mensagem;
        this.destino = destino;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "mensagem='" + mensagem + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}
