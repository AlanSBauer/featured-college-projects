package ex1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Ordenacao ordenar = new Ordenacao();
        int[] array = {5, 3, 8, 4, 2};

        System.out.println(Arrays.toString(array));
        ordenar.insertionSort(array, false);
        System.out.println(Arrays.toString(array));
    }
}
