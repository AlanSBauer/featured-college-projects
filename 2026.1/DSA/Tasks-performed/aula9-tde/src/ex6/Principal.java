package ex6;

public class Principal {
    public static void main(String[] args) {

        FilaCircular fila = new FilaCircular(5);

        fila.enfileirar(10);
        fila.enfileirar(20);
        fila.enfileirar(30);
        fila.enfileirar(40);
        fila.enfileirar(50);

        System.out.println("Fila: " + fila);

        fila.enfileirar(60);

        System.out.println("Removido: " + fila.desenfileirar());
        System.out.println("Removido: " + fila.desenfileirar());

        System.out.println("Fila após remoções: " + fila);

        fila.enfileirar(60);
        fila.enfileirar(70);

        System.out.println("Fila após reaproveitar espaço: " + fila);

        System.out.println("Primeiro da fila: " + fila.espiar());
    }
}