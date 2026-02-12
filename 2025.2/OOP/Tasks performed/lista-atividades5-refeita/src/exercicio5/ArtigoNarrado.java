package exercicio5;

public class ArtigoNarrado extends MidiaBase {
    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo Artigo narrado...");
    }

    @Override
    public int duracao() {
        return this.getDuracao();
    }
}
