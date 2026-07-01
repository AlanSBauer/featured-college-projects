package estrutura;

import model.Carta;

public class PilhaBaralho {
    private NoCarta topo;
    private int tamanho;

    public PilhaBaralho() {
        this.topo = null;
        this.tamanho = 0;
    }

    public NoCarta getTopo() {
        return topo;
    }

    public void setTopo(NoCarta topo) {
        this.topo = topo;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void empilhar(Carta carta) {
        NoCarta novaCarta = new NoCarta(carta);

        novaCarta.setProximo(topo);
        topo = novaCarta;
        tamanho++;
    }

    public Carta pegarCarta() {
        if (estaVazia()) {
             return null;
        }

        Carta cartaDesempilhada = topo.getCarta();
        topo = topo.getProximo();
        tamanho--;

        return cartaDesempilhada;
    }

    public Carta verTopo() {

        if (estaVazia()) {
            return null;
        }

        return topo.getCarta();
    }

        public void listarCartas() {
            NoCarta atual = topo;
            while (atual != null) {
                System.out.println(atual.getCarta());
                atual = atual.getProximo();
            }
        }
}
