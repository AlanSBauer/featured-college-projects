package estrutura;

import model.Carta;

public class NoCarta {
    private Carta carta;
    private NoCarta proximo;

    public NoCarta(Carta carta) {
        this.carta = carta;
        this.proximo = null;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public NoCarta getProximo() {
        return proximo;
    }

    public void setProximo(NoCarta proximo) {
        this.proximo = proximo;
    }
}
