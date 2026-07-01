package estrutura;

import model.Jogador;

public class NoJogador {

    private Jogador jogador;
    private NoJogador proximo;

    public NoJogador(Jogador jogador) {
        this.jogador = jogador;
        this.proximo = null;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public NoJogador getProximo() {
        return proximo;
    }

    public void setProximo(NoJogador proximo) {
        this.proximo = proximo;
    }
}
