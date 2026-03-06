package ex1;

public class Main {
    public static void main(String[] args) {
        Vetor vetor = new Vetor(5);
        Vetor vetorEmpty = new Vetor(3);

        //Fui fazendo comentários pra entender o que está acontecendo em cada parte certinho. Assim consigo raciocinar melhor.

        vetor.insertElementFinal("Alan");
        vetor.insertElementFinal("Leonardo");
//        vetor.addElement("João");

        System.out.println(vetor); // Retorna vetor com os 2 nomes

        vetor.addElementPosition("Pedro", 0);

        System.out.println(vetor);

        System.out.println(vetor.getElementPosition(0)); // Mostra Pedro
        System.out.println(vetor.getElementPosition(5)); // Mostra Não existe elemento nesta posição.

        System.out.println();

        vetor.increaseCapacity(); // Aumentando tamanho

        System.out.println(vetor); // Novo vetor com tamanho 10 e com os itens do primeiro vetor.

        System.out.println();

        vetor.removeElement(1); // Removendo Alan
        System.out.println(vetor);

        System.out.println("O numero de elementos dentro do vetor é: " + vetor.getSize());

        System.out.println();

        vetor.insertElementFinal("Felipe");
        System.out.println(vetor);

        vetor.removeElementValue("Leonardo"); // Remove Leonardo e a posiçao dele fica com null
        System.out.println(vetor);

        System.out.println();

        vetor.removeElementValue("Teste"); // Retorna nao foi encontrado
        System.out.println(vetor); // Retorna o vetor normalmente

        System.out.println();
        
        vetor.verifyElementExists("Felipe"); // Existe
        vetor.verifyElementExists("Pedro"); // Existe
        vetor.verifyElementExists("Alan"); // Nao encontrado

        System.out.println();

        System.out.println("O numero de elementos dentro do vetor é: " + vetor.getSize()); // A principio ta contando o null que existe no meio, q ficou quando foi retirado Leonardo com o metodo removeElementValue(), esse metodo n ta reorganizando o array, já o removeElement organiza.

        System.out.println();

        vetor.isEmpty(); // Retorna não está vazio.
        vetorEmpty.isEmpty(); // Retorna está vazio.

        System.out.println();

        vetor.removeAllElements(); // Fiz esse método limpar tudo deixando como null, e zerei o size dele;
        System.out.println(vetor);
    }
}