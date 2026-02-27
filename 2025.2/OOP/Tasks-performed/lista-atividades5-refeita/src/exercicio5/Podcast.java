package exercicio5;

public class Podcast extends MidiaBase {
    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo Podcast...");
    }

    @Override
    public int duracao() {
        return this.getDuracao();
    }
}
