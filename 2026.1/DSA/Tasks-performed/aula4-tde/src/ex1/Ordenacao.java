package ex1;

public class Ordenacao {
    public void bubbleSort(int[] array, boolean isDesc) {
        for(int i = 0; i < array.length -1; i++) {
            for(int j = 0; j < array.length -1 -i; j++) {
                if(array[j] > array[j + 1] && !isDesc) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1 ] = temp;
                }
                if(array[j] < array[j + 1] && isDesc) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1 ] = temp;
                }
            }
        }
    }

    public void selectionSort(int[] array, boolean isDesc) {
        for(int i = 0; i < array.length - 1; i++) {
            int menor = i;

            for(int j = i + 1; j < array.length; j++) {
                if(array[j] < array[menor] && !isDesc) {
                    menor = j;
                }
                if(array[j] > array[menor] && isDesc) {
                    menor = j;
                }
            }

            int temp = array[i];
            array[i] = array[menor];
            array[menor] = temp;
        }
    }

    public void insertionSort(int[] array, boolean isDesc) {
        for(int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key && !isDesc) {
                array[j + 1] = array[j];
                j--;
            }

            while (j >= 0 && array[j] < key && isDesc) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }
}
