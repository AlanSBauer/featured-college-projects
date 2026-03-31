public class Main {
    public static void main(String[] args) {
        Matriz matriz1 = new Matriz(3, 3);

        // Usuario preenche o valor na matriz como preferir...
        matriz1.preencherMatriz(0,0, 1);
        matriz1.preencherMatriz(1,1, 2);
        matriz1.preencherMatriz(2,2, 3);
        System.out.println("Matriz 1: ");
        matriz1.exibirMatriz();

        System.out.println();

        // Preenchimento automatico dos valores na matriz!!
        Matriz matriz2 = new Matriz(3, 3);
        matriz2.preencherMatriz();
        System.out.println("Matriz 2: ");
        matriz2.exibirMatriz();

        System.out.println();

        System.out.println("ORDENAÇÃO COM BUBBLESORT EM MATRIZ");

        System.out.println("Matriz 2 ordenada por LINHA");
        Ordenacao.ordenar(matriz2, "linha");
        matriz2.exibirMatriz();

        System.out.println("Matriz 2 ordenada por COLUNA");
        Ordenacao.ordenar(matriz2, "coluna");
        matriz2.exibirMatriz();

        System.out.println();
        System.out.println("ORDENAÇÃO COM MERGESORT EM MATRIZ");

        System.out.println("Matriz 2 ordenada por COMPLETA");
        Ordenacao.ordenar(matriz2, "completa");
        matriz2.exibirMatriz();
    }
}