package ex3;

public class FreteCorreios extends FreteBase {
    @Override
    public void valor(float peso, String cep) {
        super.valor(peso, cep);
        float valor = 5 * peso;
        System.out.println("Valor R$" + valor);
        System.out.println("O produto será entregue pelos Correios.");
        System.out.println("--------------------------");
    }

    @Override
    public void prazo(String cep) {
        int prazoDias = 10;
        System.out.println("Prazo: " + prazoDias + " dias");
    }
}
