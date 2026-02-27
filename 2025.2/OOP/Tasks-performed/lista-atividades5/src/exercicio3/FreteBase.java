package exercicio3;

public class FreteBase implements Frete {
    public boolean cepValido(String cep) {
        return cep != null && cep.matches("\\d{5}-\\d{3}");
    }

    @Override
    public float valor(float peso, String cep) {
        return 0;
    }

    @Override
    public int prazo(String cep) {
        return 0;
    }
}
