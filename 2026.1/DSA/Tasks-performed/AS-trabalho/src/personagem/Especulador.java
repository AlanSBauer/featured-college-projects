package personagem;

public class Especulador extends Personagem {
    public Especulador() {
        super("Especulador");
    }

    @Override
    public double bonusSalario(double valor) {
        return valor * 1.20;
    }

    @Override
    public double modificarImposto(double valor) {
        return valor * 1.10;
    }
}
