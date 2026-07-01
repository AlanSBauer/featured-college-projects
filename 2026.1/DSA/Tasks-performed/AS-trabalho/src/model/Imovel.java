package model;

public class Imovel {
    private int id;
    private String nome;
    private double valorCompra;
    private double aluguelBase;
    private double aluguelBaseOriginal;
    private Jogador dono;
    private double multiplicadorDemanda;

    public Imovel(String nome, double valorCompra, double aluguelBase) {
        this.nome = nome;
        this.valorCompra = valorCompra;
        this.aluguelBase = aluguelBase;
        this.aluguelBaseOriginal = aluguelBase;
        this.dono = null;
        this.multiplicadorDemanda = 1.0;
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

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getAluguelBase() {
        return aluguelBase;
    }

    public void setAluguelBase(double aluguelBase) {
        this.aluguelBase = aluguelBase;
    }

    public double getAluguelBaseOriginal() {
        return aluguelBaseOriginal;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    public double getMultiplicadorDemanda() {
        return multiplicadorDemanda;
    }

    public void setMultiplicadorDemanda(double multiplicadorDemanda) {
        this.multiplicadorDemanda = multiplicadorDemanda;
    }

    public double calcularAluguel() {
        return aluguelBase * multiplicadorDemanda;
    }

    public void aumentarDemanda() {
        if(multiplicadorDemanda < 2.0) {
            multiplicadorDemanda = Math.min(2.0, multiplicadorDemanda + 0.1);
        }
    }

    public void resetarParaNovaPartida() {
        this.dono = null;
        this.multiplicadorDemanda = 1.0;
        this.aluguelBase = aluguelBaseOriginal;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " Nome: " + nome +
                " Valor de compra: " + valorCompra +
                " Aluguel: " + aluguelBase +
                " Dono: " + (dono != null ? dono.getNome() : "Nenhum") +
                " Multiplicador atual: " + multiplicadorDemanda;
    }
}
