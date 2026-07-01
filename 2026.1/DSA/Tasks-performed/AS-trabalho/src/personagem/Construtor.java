package personagem;

public class Construtor extends Personagem {
    public Construtor() {
        super("Construtor");
    }

    @Override
    public double modificarAluguel(double valor) {
        return valor * 1.15;
    }
}
