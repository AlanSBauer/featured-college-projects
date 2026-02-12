package exercicio3;

public class FreteBase implements Frete {
    @Override
    public void valor(float peso, String cep) {
        System.out.println("Peso: " + peso);
        System.out.println("CEP da entrega: " + cep);
    }
}
