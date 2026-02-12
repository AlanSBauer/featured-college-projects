package exercicio3;

public class FreteCorreios extends FreteBase {
    @Override
    public float valor(float peso, String cep) {
        if(!cepValido(cep)) {
            return Float.MAX_VALUE;
        }

        if(peso < 10)
            return 40f;
        if(peso <= 30)
            return 60f;
        if(peso <= 80)
            return 150f;
        else
            return 200f;
    }

    @Override
    public int prazo(String cep) {
        if(!cepValido(cep)) {
            System.out.println("Erro - CEP inválido!");
            return Integer.MAX_VALUE;
        }
        return 5;
    }
}
