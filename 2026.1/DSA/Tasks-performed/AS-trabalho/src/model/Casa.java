package model;

import model.enums.TipoCasa;

public class Casa {
    private String nome;
    private TipoCasa tipo;
    private Imovel imovel;

    public Casa(TipoCasa tipo, Imovel imovel) {
        if(tipo != TipoCasa.IMOVEL) {
            throw new IllegalArgumentException("Este construtor deve ser usado apenas para casos do tipo IMÓVEL");
        }

        this.nome = imovel.getNome();
        this.tipo = tipo;
        this.imovel = imovel;
    }

    public Casa(String nome, TipoCasa tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Casa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCasa getTipo() {
        return tipo;
    }

    public void setTipo(TipoCasa tipo) {
        this.tipo = tipo;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    @Override
    public String toString() {
        return "Nome da casa: " + nome +
                " Tipo: " + tipo;
    }
}
