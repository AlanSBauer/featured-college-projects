package ex1;

import java.util.Arrays;

public class Vetor {
    private String[] elements;
    private int size;

    public Vetor(int capacity) {
        this.elements = new String[capacity];
        this.size = 0;
    }

    // Adicionar elemento ao final: Adicione um elemento na primeira posição livre ao final do vetor.
    public void insertElementFinal(String element) {
        if(size < elements.length) {
            elements[size] = element;
            size++;
        } else {
            System.out.println("Vetor cheio!");
        }

    }

    //Adicionar elemento em uma posição específica: Insira um elemento em uma posição específica, deslocando os elementos subsequentes para a direita.
    public void addElementPosition(String element, int pos) {
       if(pos >= 0 && pos <= size) {
           for(int i = size -1; i < pos; i--) {
               elements[i + 1] = elements[i];
           }
           elements[pos] = element;
           size++;
       }
    }

    //Obter elemento de uma posição: Retorne o valor do elemento armazenado em uma determinada posição.

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
