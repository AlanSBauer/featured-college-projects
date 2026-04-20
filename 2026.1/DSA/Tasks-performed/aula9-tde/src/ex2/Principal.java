package ex2;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite uma palavra: ");
        String texto = input.nextLine();

        texto = texto.toLowerCase().replace(" ", "");

        Pilha pilha = new Pilha(texto.length());

        int meio = texto.length() / 2;

        for (int i = 0; i < meio; i++) {
            pilha.empilhar(String.valueOf(texto.charAt(i)));
        }

        int inicioSegundaParte = meio;
        if (texto.length() % 2 != 0) {
            inicioSegundaParte++;
        }

        boolean palindromo = true;

        for (int i = inicioSegundaParte; i < texto.length(); i++) {
            String letra = pilha.desempilhar();

            if (letra == null || !letra.equals(String.valueOf(texto.charAt(i)))) {
                palindromo = false;
                break;
            }
        }

        if (palindromo) {
            System.out.println("É palíndromo");
        } else {
            System.out.println("Não é palíndromo");
        }

        input.close();
    }
}
