package ex2;

import java.util.Arrays;

public class Matriz {
    private int [][] matriz;

    public Matriz(int lines, int columns) {
        this.matriz = new int[lines][columns];
    }

    // Adicionar valores
    public void setElement(int line, int column, int value) {
        matriz[line][column] = value;
    }

    // Maior elemento
    public int getBiggestElement() {
        int maior = matriz[0][0];
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j] > maior) {
                    maior = matriz[i][j];
                }
            }
        }
        return maior;
    }
    
    // Menor elemento
    public int getSmallestElement() {
        int small = matriz[0][0];
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j] < small) {
                    small = matriz[i][j];
                }
            }
        }
        return small;
    }

    // Média
    public int getAverage() {
        int result = 0;

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                result += matriz[i][j];
            }
        }

        // matriz.length retorna quantas linhas tem a matriz
        // matriz[0].length retorna quantas colunas
        // para saber quantos valores temos, devemos multiplicar a qtd de linhas por colunas.
        int totalElements = matriz.length * matriz[0].length;

        return result / totalElements;
    }

    public int getSumTotal() {
        int total = 0;

        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                total += matriz[i][j];
            }
        }

        return total;
    }

    public int getOccurrencesValue(int value) {
        int ocurrences = 0;
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                if(matriz[i][j] == value) {
                    ocurrences++;
                }
            }
        }
        return ocurrences;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                result += matriz[i][j] + " ";
            }
            result += "\n";
        }

        return result;
}

}
