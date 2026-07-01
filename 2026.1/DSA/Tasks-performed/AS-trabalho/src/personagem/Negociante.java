package personagem;

public class Negociante extends Personagem {
    public Negociante() {
        super("Negociante");
    }

    @Override
    public double modificarAluguel(double valor) {
        return valor * 0.90;
    }
}
