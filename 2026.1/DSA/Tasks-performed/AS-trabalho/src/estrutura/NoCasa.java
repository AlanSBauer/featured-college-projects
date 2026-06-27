package estruturas;

import model.Casa;

public class NoCasa {
    private Casa casa;
    private NoCasa anterior;
    private NoCasa proximo;

    public NoCasa(Casa casa, NoCasa anterior, NoCasa proximo) {
        this.casa = casa;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    public NoCasa(Casa casa) {
        this.casa = casa;
    }

    public NoCasa() {
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public NoCasa getAnterior() {
        return anterior;
    }

    public void setAnterior(NoCasa anterior) {
        this.anterior = anterior;
    }

    public NoCasa getProximo() {
        return proximo;
    }

    public void setProximo(NoCasa proximo) {
        this.proximo = proximo;
    }
}
