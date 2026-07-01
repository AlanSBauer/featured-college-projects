package service;

import estrutura.ListaCircular;
import estrutura.NoCasa;
import model.Casa;
import model.Jogador;

public class TabuleiroService {

    private ListaCircular tabuleiro;

    public TabuleiroService() {
        this.tabuleiro = new ListaCircular();
    }

    public ListaCircular getTabuleiro() {
        return tabuleiro;
    }

    public void adicionarCasa(Casa casa) {
        tabuleiro.adicionarCasa(casa);
    }

    public void listarTabuleiro() {
        tabuleiro.listar();
    }

    public void posicionarJogadorNoInicio(Jogador jogador) {
        jogador.setPosicaoAtual(tabuleiro.getInicio());
    }

    public NoCasa moverJogador(Jogador jogador, double passos) {
        NoCasa destino = tabuleiro.avancar(jogador.getPosicaoAtual(), passos);
        jogador.setPosicaoAtual(destino);

        return destino;
    }

    public NoCasa retrocederJogador(Jogador jogador, double passos) {
        NoCasa destino = tabuleiro.retroceder(jogador.getPosicaoAtual(), passos);
        jogador.setPosicaoAtual(destino);

        return destino;
    }

    public Casa obterCasaAtual(Jogador jogador) {
        return jogador.getPosicaoAtual().getCasa();
    }
}