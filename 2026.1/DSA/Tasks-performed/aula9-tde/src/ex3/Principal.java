package ex3;

import java.util.Scanner;

public class Principal {

    public static boolean estaBalanceado(String expressao) {
        Pilha pilha = new Pilha(expressao.length());

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                pilha.empilhar(c);
            }

            else if (c == ')' || c == ']' || c == '}') {

                if (pilha.estaVazia()) {
                    return false;
                }

                char topo = pilha.desempilhar();

                if (!combina(topo, c)) {
                    return false;
                }
            }
        }

        return pilha.estaVazia();
    }

    public static boolean combina(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')') ||
                (abertura == '[' && fechamento == ']') ||
                (abertura == '{' && fechamento == '}');
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite uma expressão: ");
        String expressao = input.nextLine();

        if (estaBalanceado(expressao)) {
            System.out.println("Balanceado");
        } else {
            System.out.println("Não balanceado");
        }

        input.close();
    }
}