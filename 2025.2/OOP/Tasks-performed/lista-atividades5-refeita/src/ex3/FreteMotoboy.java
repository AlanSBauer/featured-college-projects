package ex3;

public class FreteMotoboy extends FreteBase {
    @Override
    public void valor(float peso, String cep) {
        super.valor(peso, cep);
        float valor = 15f;
        System.out.println("Valor: R$" + valor);
        System.out.println("Um motoboy levará sua encomenda.");
        System.out.println("--------------------------");
    }

    @Override
    public void prazo(String cep) {
        int prazoDias = 0;
        System.out.println("Prazo: " + prazoDias);
    }
}
