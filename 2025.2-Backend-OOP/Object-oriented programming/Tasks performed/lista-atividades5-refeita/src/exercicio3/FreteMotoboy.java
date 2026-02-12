package exercicio3;

public class FreteMotoboy extends FreteBase {
    @Override
    public void valor(float peso, String cep) {
        super.valor(peso, cep);
        int prazoDias = 0;
        float valor = 15f;
        System.out.println("Prazo: " + prazoDias + " dias");
        System.out.println("Valor: R$" + valor);
        System.out.println("------------------------------");
        System.out.println("Entrega feita por motoboy");
    }
}
