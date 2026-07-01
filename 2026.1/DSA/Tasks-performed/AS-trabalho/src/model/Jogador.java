package model;

import estrutura.NoCasa;
import personagem.Personagem;

import java.util.ArrayList;

public class Jogador {
    private int id;
    private String nome;
    private double saldo;
    private NoCasa posicaoAtual;
    private Personagem personagem;
    private ArrayList<Imovel> propriedades;
    private boolean isencaoAdvogadoUsada;
    private boolean preso;
    private boolean falido;
    private int tentativasPrisao;
    private int voltasCompletas;

    public Jogador(String nome, double saldo, NoCasa posicaoAtual, Personagem personagem) {
        this.nome = nome;
        this.saldo = saldo;
        this.posicaoAtual = posicaoAtual;
        this.personagem = personagem;
        this.propriedades = new ArrayList<>();
    }

    public Jogador() {
        this.propriedades = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public NoCasa getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(NoCasa posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public ArrayList<Imovel> getPropriedades() {
        return propriedades;
    }

    public void setPropriedades(ArrayList<Imovel> propriedades) {
        this.propriedades = propriedades;
    }

    public boolean isIsencaoAdvogadoUsada() {
        return isencaoAdvogadoUsada;
    }

    public void setIsencaoAdvogadoUsada(boolean isencaoAdvogadoUsada) {
        this.isencaoAdvogadoUsada = isencaoAdvogadoUsada;
    }

    public boolean isPreso() {
        return preso;
    }

    public void setPreso(boolean preso) {
        this.preso = preso;
    }

    public boolean isFalido() {
        return falido;
    }

    public void setFalido(boolean falido) {
        this.falido = falido;
    }

    public int getTentativasPrisao() {
        return tentativasPrisao;
    }

    public void setTentativasPrisao(int tentativasPrisao) {
        this.tentativasPrisao = tentativasPrisao;
    }

    public int getVoltasCompletas() {
        return voltasCompletas;
    }

    public void setVoltasCompletas(int voltasCompletas) {
        this.voltasCompletas = voltasCompletas;
    }

    public double calcularPatrimonio() {
        double total = saldo;

        for (Imovel imovel : propriedades) {
            total += imovel.getValorCompra();
        }

        return total;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " Nome: " + nome +
                " Saldo: " + saldo +
                " Personagem: " + (personagem != null ? personagem.getNome() : "Nao definido") +
                " Propriedades: " + propriedades.size() +
                " Voltas: " + voltasCompletas +
                " Status: " + (falido ? "Falido" : (preso ? "Preso" : "Ativo"));
    }
}
