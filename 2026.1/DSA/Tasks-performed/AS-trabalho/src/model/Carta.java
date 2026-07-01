package model;

import model.enums.EfeitoCarta;
import model.enums.TipoCarta;

import java.util.UUID;

public class Carta {
    private static long contadorCartas = 1;

    private UUID uuid;
    private TipoCarta tipo;
    private EfeitoCarta efeitoCarta;
    private String descricao;
    private double valor;
    private double quantidadeCasas;

    public Carta(TipoCarta tipo, EfeitoCarta efeitoCarta, String descricao, int valor, int quantidadeCasas) {
        this.uuid = new UUID(0L, contadorCartas++);
        this.tipo = tipo;
        this.efeitoCarta = efeitoCarta;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidadeCasas = quantidadeCasas;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public TipoCarta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarta tipo) {
        this.tipo = tipo;
    }

    public EfeitoCarta getEfeitoCarta() {
        return efeitoCarta;
    }

    public void setEfeitoCarta(EfeitoCarta efeitoCarta) {
        this.efeitoCarta = efeitoCarta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public double getQuantidadeCasas() {
        return quantidadeCasas;
    }

    public void setQuantidadeCasas(int quantidadeCasas) {
        this.quantidadeCasas = quantidadeCasas;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo +
                " Descricao: " + descricao;
    }
}
