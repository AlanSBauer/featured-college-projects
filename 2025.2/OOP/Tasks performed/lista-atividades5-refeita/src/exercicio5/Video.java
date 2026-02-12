package exercicio5;

public class Video extends MidiaBase {
    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo Video...");
    }

    @Override
    public int duracao() {
        return this.getDuracao();
    }
}
