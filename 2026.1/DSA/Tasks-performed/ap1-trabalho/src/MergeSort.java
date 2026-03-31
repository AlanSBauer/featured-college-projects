public class MergeSort {

    public static void mergeSort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;

            mergeSort(arr, inicio, meio);

            mergeSort(arr, meio + 1, fim);

            merge(arr, inicio, meio, fim);
        }
    }

    public static void merge(int[] arr, int inicio, int meio, int fim) {
        int[] temp = new int[fim - inicio + 1];

        int i = inicio;
        int j = meio + 1;
        int k = 0;

        while (i <= meio && j <= fim) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= meio) {
            temp[k++] = arr[i++];
        }

        while (j <= fim) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[inicio + x] = temp[x];
        }
    }

    public static void ordenarMatrizCompleta(Matriz matriz) {
        int linhas = matriz.getLinhas();
        int colunas = matriz.getColunas();

        int[][] m = matriz.getMatriz();

        int[] vetor = new int[linhas * colunas];
        int index = 0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                vetor[index++] = m[i][j];
            }
        }

        mergeSort(vetor, 0, vetor.length - 1);

        index = 0;
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                m[i][j] = vetor[index++];
            }
        }
    }
}