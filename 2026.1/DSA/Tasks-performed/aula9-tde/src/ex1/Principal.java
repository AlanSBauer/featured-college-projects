package ex1;

import ex2.PilhaInteiros;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PilhaInteiros pares = new PilhaInteiros(10);
        PilhaInteiros impares = new PilhaInteiros(10);

        for (int i = 0; i < 10; i++) {
            System.out.println("Digite o número: ");
            int numero = input.nextInt();
            input.nextLine();

            if(numero % 2 == 0 && numero != 0) {
                pares.empilhar(numero);
            } else if (numero == 0) {
                if(pares.estaVazia()) {
                    System.out.println("Pilha de pares vazia.");
                } else {
                    pares.desempilhar();
                }

                if(impares.estaVazia()) {
                    System.out.println("Pilha de impares vazia.");
                } else {
                    impares.desempilhar();
                }
            } else {
                impares.empilhar(numero);
            }
        }

        System.out.println(pares);
        System.out.println(impares);

        input.close();
    }
}
