package ex1;

public class Mensagem {
    private String descricao;
    private String destino;

    public Mensagem(String descricao, String destino) {
        this.descricao = descricao;
        this.destino = destino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Mensagem: " + descricao + " Destino: " + destino;
    }
}
