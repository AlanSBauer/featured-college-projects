package paineldigital;

public class No {
    private Anuncio anuncio;
    private No proximo;

    public No(Anuncio anuncio, No no) {
        this.anuncio = anuncio;
        this.proximo = no;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No no) {
        this.proximo = no;
    }
}
