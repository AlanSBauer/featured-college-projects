import java.util.Arrays;
import java.util.Random;

public class Matriz {
    private int[][] matriz;

    public Matriz(int lines, int cols) {
        this.matriz = new int[lines][cols];
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int getLinhas() {
        return matriz.length;
    }

    public int getColunas() {
        return matriz[0].length;
    }

    public void preencherMatriz(int lin, int col, int value) {
        matriz[lin][col] = value;
    }

    public void preencherMatriz() {
        Random random = new Random();

        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                this.matriz[i][j] = random.nextInt(100);
            }
        }
    }

    public void exibirMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf("%4d", matriz[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Matriz{" +
                "matriz=" + Arrays.deepToString(matriz) +
                '}';
    }
}
