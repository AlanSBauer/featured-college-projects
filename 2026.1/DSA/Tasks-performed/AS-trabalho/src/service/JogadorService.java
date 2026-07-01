package service;

import model.Jogador;

import java.util.ArrayList;

public class JogadorService {
    private ArrayList<Jogador> jogadores;
    private int contadorId = 1;

    public JogadorService() {
        this.jogadores = new ArrayList<>();
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void cadastrarJogador(Jogador jogador) {
        if(jogadores.size() < 6) {
            jogador.setId(contadorId++);
            jogadores.add(jogador);
        } else {
            System.out.println("Número máximo de jogadores atingido.");
        }
    }

    public void listarJogadores() {
        if(jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado.");
            return;
        }

        for (Jogador jogador : jogadores) {
            System.out.println(jogador);
        }
    }

    public Jogador buscarJogador(int id) {
        for (Jogador jogador : jogadores) {
            if(jogador.getId() == id) {
                return jogador;
            }
        }
        return null;
    }

    public void removerJogador(int id) {
        if(jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado.");
            return;
        }

        Jogador jogador = buscarJogador(id);

        if (jogador != null) {
            jogadores.remove(jogador);
            System.out.println("Jogador removido: " + jogador);
        } else {
            System.out.println("Jogador não encontrado.");
        }
    }

    public void editarJogador(int id, String novoNome, double novoSaldo) {
        if(jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado.");
        } else {
            Jogador jogador = buscarJogador(id);

            if (jogador != null) {
                jogador.setNome(novoNome);
                jogador.setSaldo(novoSaldo);
                System.out.println("Jogador editado: " + jogador);
            } else {
                System.out.println("Jogador não encontrado.");
            }
        }
    }
}
