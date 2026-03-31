public class Ordenacao {
    public static void ordenar(Matriz matriz, String metodoOrdenacao) {
        switch (metodoOrdenacao.toLowerCase()) {
            case "linha":
                BubbleSort.BubbleSort(matriz, false);
                break;

            case "coluna":
                BubbleSort.BubbleSort(matriz, true);
                break;

            case "completa":
                MergeSort.ordenarMatrizCompleta(matriz);
                break;

            default:
                System.out.println("Método de ordenação inválido. É necessário que seja ou 'linha', ou 'coluna' ou 'completa'.");
                break;
        }
    }


}
