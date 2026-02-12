package ex3;

public class FreteRetirada extends FreteBase {
    @Override
    public void valor(float peso, String cep) {
        super.valor(peso, cep);
        float valor = 0;
        System.out.println("Valor: R$" + valor);
        System.out.println("Produto disponivel para retirada.");
        System.out.println("--------------------------");
    }

    @Override
    public void prazo(String cep) {
        int prazoDias = 0;
        System.out.println("Prazo: " + prazoDias);
    }
}
