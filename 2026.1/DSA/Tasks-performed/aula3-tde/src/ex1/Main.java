package ex1;

public class Main {
    public static void main(String[] args) {
        Vetor vetor = new Vetor(5);
        vetor.insertElementFinal("Alan");
        vetor.insertElementFinal("Leonardo");
//        vetor.addElement("João");

        System.out.println(vetor); //Retorna vetor com os 2 nomes

        vetor.addElementPosition("Pedro", 1);

        System.out.println(vetor);
    }
}
