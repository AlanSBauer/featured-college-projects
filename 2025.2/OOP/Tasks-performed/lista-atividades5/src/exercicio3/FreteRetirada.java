package exercicio3;

public class FreteRetirada extends FreteBase {
    @Override
    public float valor(float peso, String cep) {
        if(!cepValido(cep)) {
            return Float.MAX_VALUE;
        }

        if(cep.equals("95560-000"))
            return 0f;

        return Float.MAX_VALUE;
    }

    @Override
    public int prazo(String cep) {
        if(!cepValido(cep)) {
            System.out.println("Erro - CEP inválido!");
            return Integer.MAX_VALUE;
        }

        if(cep.equals("95560-000"))
            return 0;

        return Integer.MAX_VALUE;
    }
}
