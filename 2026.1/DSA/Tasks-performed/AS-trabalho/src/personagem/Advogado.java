package personagem;

public class Advogado extends Personagem {
    public Advogado() {
        super("Advogado");
    }

    @Override
    public boolean podeSairSemFianca() {
        return true;
    }
}
