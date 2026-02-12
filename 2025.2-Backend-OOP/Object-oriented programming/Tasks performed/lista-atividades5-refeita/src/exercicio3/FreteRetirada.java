package exercicio3;

public class FreteRetirada extends FreteBase {
    @Override
    public void valor(float peso, String cep) {
        super.valor(peso, cep);
        int prazoDias = 0;
        float valor = 0;
        System.out.println("Prazo: " + prazoDias + " dias");
        System.out.println("Valor: R$" + valor);
        System.out.println("------------------------------");
        System.out.println("A entrega foi retirada");
    }
}
