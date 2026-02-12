package exercicio3;

public class FreteCorreios extends FreteBase {
    @Override
    public void valor(float peso, String cep) {
        super.valor(peso, cep);
        int prazoDias = 10;
        float valor = 5 * peso;
        System.out.println("Prazo: " + prazoDias + " dias");
        System.out.println("Valor: R$" + valor);
        System.out.println("------------------------------");
        System.out.println("Entrega feita pelos correios");
    }
}
