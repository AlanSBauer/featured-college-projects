package ex7;

public class Principal {
    public static void main(String[] args) {

        Deque deque = new Deque(5);

        deque.inserirNoFim(10);
        deque.inserirNoFim(20);
        deque.inserirNoInicio(5);
        deque.inserirNoInicio(1);

        System.out.println("Deque: " + deque);

        System.out.println("Início: " + deque.consultarInicio());
        System.out.println("Fim: " + deque.consultarFim());

        System.out.println("Removido início: " + deque.removerDoInicio());
        System.out.println("Removido fim: " + deque.removerDoFim());

        System.out.println("Deque após remoções: " + deque);

        deque.limpar();
        System.out.println("Deque após limpar: " + deque);
    }
}