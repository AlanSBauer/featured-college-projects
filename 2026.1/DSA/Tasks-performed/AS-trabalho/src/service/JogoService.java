package service;

import estrutura.FilaPrisao;
import estrutura.PilhaBaralho;
import model.Carta;
import model.Casa;
import model.Dado;
import model.Jogador;

public class JogoService {

    private JogadorService jogadorService;
    private TabuleiroService tabuleiroService;
    private ImovelService imovelService;
    private PilhaBaralho baralho;
    private FilaPrisao filaPrisao;
    private Dado dado;
    private int rodadaAtual;

    public JogoService(JogadorService jogadorService, TabuleiroService tabuleiroService, ImovelService imovelService) {
        this.jogadorService = jogadorService;
        this.tabuleiroService = tabuleiroService;
        this.imovelService = imovelService;
        this.baralho = new PilhaBaralho();
        this.filaPrisao = new FilaPrisao();
        this.dado = new Dado();
        this.rodadaAtual = 1;
    }

    public void jogarTurno(Jogador jogador) {
        int resultado = dado.rolarDados();

        System.out.println(jogador.getNome() + " tirou " + resultado);
        tabuleiroService.moverJogador(jogador, resultado);
        System.out.println("Parou em: " + tabuleiroService.obterCasaAtual(jogador));
        processarCasa(jogador);
    }

    public void processarCasa(Jogador jogador) {
        Casa casaAtual = tabuleiroService.obterCasaAtual(jogador);

        switch (casaAtual.getTipo()) {
            case INICIO:
                break;

            case IMOVEL:
                break;

            case IMPOSTO:
                jogador.setSaldo(jogador.getSaldo() - 100);
                break;

            case RESTITUICAO:
                jogador.setSaldo(jogador.getSaldo() + 100);
                break;

            case PRISAO:
                break;

            case LEILAO:
                break;

            case SORTE_REVES:
                Carta carta = baralho.pegarCarta();
                aplicarCarta(jogador, carta);
                break;
        }
    }

    public void aplicarCarta(Jogador jogador, Carta carta) {
        switch (carta.getEfeitoCarta()) {

            case GANHAR_DINHEIRO:
                jogador.setSaldo(jogador.getSaldo() + carta.getValor());
                break;

            case PERDER_DINHEIRO:
                jogador.setSaldo(jogador.getSaldo() - carta.getValor());
                break;

            case AVANCAR:
                tabuleiroService.moverJogador(jogador, carta.getQuantidadeCasas());
                break;

            case RETROCEDER:
                tabuleiroService.retrocederJogador(jogador, carta.getQuantidadeCasas());
                break;

            case IR_PARA_PRISAO:
                filaPrisao.enfileirar(jogador);
                break;
        }


    }
}