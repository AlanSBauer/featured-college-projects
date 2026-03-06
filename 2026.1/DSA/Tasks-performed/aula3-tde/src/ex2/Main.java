package ex2;

public class Main {
    public static void main(String[] args) {
        Matriz matriz = new Matriz(3, 3);
        int value = 1;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                matriz.setElement(i, j, value);
                value ++;
            }
        }

        System.out.println(matriz);

        System.out.println("Maior: " + matriz.getBiggestElement());
        System.out.println("Menor: " + matriz.getSmallestElement());
        System.out.println("Soma total: " + matriz.getSumTotal());
        System.out.println("Média: " + matriz.getAverage());

        System.out.println();

        matriz.setElement(0, 0, 9); // tirando o 1 e substituindo pelo 9 para o 9 aparecer 2 vezes.
        System.out.println(matriz);
        System.out.println("Menor: " + matriz.getBiggestElement()); // agora será o 2. pois n tem o 1 mais.
        System.out.println("Ocorrencias: " + matriz.getOccurrencesValue(9)); // retorna 2
        System.out.println("Ocorrencias: " + matriz.getOccurrencesValue(15)); // nao tem nenhuma.
        System.out.println("Ocorrencias: " + matriz.getOccurrencesValue(2)); // retorna 1.
        System.out.println("Ocorrencias: " + matriz.getOccurrencesValue(1)); // como foi tirado o 1 pelo 9, nenhuma ocorrencia.
        
    }
}
