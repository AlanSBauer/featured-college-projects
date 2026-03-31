public class BubbleSort {
    public static void BubbleSort(Matriz matriz, boolean isCol) {
        int[][] m = matriz.getMatriz();

        int linhas = m.length;
        int colunas = m[0].length;

        if (!isCol) {
            for (int i = 0; i < linhas; i++) {
                for (int k = 0; k < colunas - 1; k++) {
                    for (int j = 0; j < colunas - 1 - k; j++) {
                        if (m[i][j] > m[i][j + 1]) {
                            int temp = m[i][j];
                            m[i][j] = m[i][j + 1];
                            m[i][j + 1] = temp;
                        }
                    }
                }
            }
            return;
        }
        for (int j = 0; j < colunas; j++) {
            for (int k = 0; k < linhas - 1; k++) {
                for (int i = 0; i < linhas - 1 - k; i++) {
                    if (m[i][j] > m[i + 1][j]) {
                        int temp = m[i][j];
                        m[i][j] = m[i + 1][j];
                        m[i + 1][j] = temp;
                    }
                }
            }
        }
    }
}