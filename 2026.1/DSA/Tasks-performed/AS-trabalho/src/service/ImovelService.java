package service;

import model.Imovel;
import model.Jogador;

import java.util.ArrayList;

public class ImovelService {
    private ArrayList<Imovel> imoveis;
    private int contadorId = 1;

    public ImovelService() {
        this.imoveis =  new ArrayList<>();
    }

    public ArrayList<Imovel> getImoveis() {
        return imoveis;
    }

    public void cadastrarImovel(String nome, double valorCompra, double aluguelBase) {
        if(imoveis.size() < 40) {
            Imovel imovel = new Imovel(nome, valorCompra, aluguelBase);
            imovel.setId(contadorId++);
            imoveis.add(imovel);
        } else {
            System.out.println("Número máximo de imóveis atingido.");
        }
    }

    public void listarImoveis() {
        if(imoveis.size() == 0) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }

        for (Imovel imovel : imoveis) {
            System.out.println(imovel);
        }
    }

    public Imovel buscarImovel(int id) {
        for (Imovel imovel : imoveis) {
            if (imovel.getId() == id) {
                return imovel;
            }
        }
        return null;
    }

    public void editarImovel(int id, String nome, double valorCompra, double aluguelBase) {
        if(imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }

        Imovel imovel = buscarImovel(id);

        if (imovel != null) {
            imovel.setNome(nome);
            imovel.setValorCompra(valorCompra);
            imovel.setAluguelBase(aluguelBase);
        } else {
            System.out.println("Imóvel não encontrado.");
        }
    }

    public void removerImovel(int id) {
        if(imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }

        Imovel imovel = buscarImovel(id);

        if (imovel != null) {
            imoveis.remove(imovel);
            System.out.println("Imóvel removido: " + imovel);
        } else {
            System.out.println("Imóvel não encontrado.");
        }
    }

    public boolean comprarImovel(Jogador jogador, Imovel imovel) {
        if(imovel == null) {
            return false;
        }

        if(imovel.getDono() != null) {
            return false;
        }

        if(jogador.getSaldo() >= imovel.getValorCompra()) {
            jogador.setSaldo(jogador.getSaldo() - imovel.getValorCompra());
            imovel.setDono(jogador);
            jogador.getPropriedades().add(imovel);
            return true;
        }

        return false;
    }

    public double calcularAluguel(Imovel imovel) {
        return imovel.calcularAluguel();
    }

    public void registrarVisita(Imovel imovel) {
        imovel.aumentarDemanda();
    }

    public Imovel primeiroSemDono() {
        for (Imovel imovel : imoveis) {
            if (imovel.getDono() == null) {
                return imovel;
            }
        }

        return null;
    }
}
