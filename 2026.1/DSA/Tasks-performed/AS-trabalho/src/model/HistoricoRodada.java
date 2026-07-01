package model;

public class HistoricoRodada {
    private int rodada;
    private String jogador;
    private int dados;
    private String casaDestino;
    private String efeito;

    public HistoricoRodada(int rodada, String jogador, int dados, String casaDestino, String efeito) {
        this.rodada = rodada;
        this.jogador = jogador;
        this.dados = dados;
        this.casaDestino = casaDestino;
        this.efeito = efeito;
    }

    public int getRodada() {
        return rodada;
    }

    public String getJogador() {
        return jogador;
    }

    public int getDados() {
        return dados;
    }

    public String getCasaDestino() {
        return casaDestino;
    }

    public String getEfeito() {
        return efeito;
    }

    @Override
    public String toString() {
        return "Rodada " + rodada +
                " | Jogador: " + jogador +
                " | Dados: " + dados +
                " | Casa: " + casaDestino +
                " | Efeito: " + efeito;
    }
}