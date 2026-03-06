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
           for(int i = size - 1; i >= pos; i--) {
               elements[i + 1] = elements[i];
           }
           elements[pos] = element;
           size++;
       }
    }

    //Obter elemento de uma posição: Retorne o valor do elemento armazenado em uma determinada posição.
    public String getElementPosition(int pos) {
        if(pos < size) {
            return elements[pos];
        }
        return "Não existe elemento nesta posição.";
    }

    // Alterar elemento de uma posição: Altere o valor do elemento armazenado em uma determinada posição.
    public void changeElementPosition(String element, int pos) {
        if(pos < size) {
            elements[pos] = element;
        }
    }

    // Aumentar a capacidade: Aumente a capacidade do Vetor quando estiver cheio.
    public void increaseCapacity() {
        String[] newVetor = new String[this.elements.length * 2];

        for(int i = 0; i < size; i++) {
            newVetor[i] = elements[i];
        }
        this.elements = newVetor;
    }

    // Remover elemento pelo índice: Remova o elemento na posição especificada, reorganizando os elementos restantes para preencher a lacuna.
    public void removeElement(int pos) {
        if(pos >= 0 && pos < size) {
            elements[pos] = null;

            for(int i = pos; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[size - 1] = null;
            size--;
        }
    }

    // Remover elemento pelo valor: Encontre e remova a primeira ocorrência de um valor específico no vetor.
    public void removeElementValue(String element) {
        for(int i = 0; i < size; i++) {
            if(elements[i] != null && elements[i].contains(element)) {
                elements[i] = null;
                return;
            } 
        }
        System.out.println("O elemento não foi encontrado.");
    }

    // Verificar se um elemento existe: Verifique se um determinado valor está presente no vetor.
    public void verifyElementExists(String element) {
        for(int i = 0; i < size; i++) {
            if(elements[i] != null && elements[i].contains(element)) {
                System.out.println("O elemento existe no array.");
                return;
            }
        }
        System.out.println("O elemento não foi encontrado no array.");
    }

    // Obter tamanho atual do vetor: Retorne o número de elementos atualmente armazenados no vetor.
    public int getSize() {
        return this.size;
    }

    // Verificar se o vetor está vazio: Indique se o vetor está vazio (ou seja, sem nenhum elemento armazenado).
    public void isEmpty() {
        if(size == 0) {
            System.out.println("Está vazio!");
            return;
        }
        System.out.println("Não está vazio!");
    }

    // Limpar: Remova todos os elementos do vetor, tornando-o vazio.
    public void removeAllElements() {
        // vou limpar com for e null pra ficar mais completo.
        
        for(int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0; // igual precisa zerar, para caso for adicionar elementos adicionar desde o inicio.
    }

    // Listar Elementos: Reescreva o método toString() para listar todos os elementos contidos no vetor
    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}
