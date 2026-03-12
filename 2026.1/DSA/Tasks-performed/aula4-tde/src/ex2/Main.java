package ex2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Ordenacao ordenar = new Ordenacao();
        Funcionario f1 = new Funcionario("Alan", 2500);
        Funcionario f2 = new Funcionario("Leonardo", 2200);
        Funcionario f3 = new Funcionario("Felipe", 2600);
        Funcionario f4 = new Funcionario("João", 1800);
        Funcionario f5 = new Funcionario("José", 1700);
        Funcionario[] funcionarios = {f1, f2, f3, f4, f5};

        System.out.println("SALARIOS");
        System.out.println("Array ORIGINAL: " + Arrays.toString(funcionarios));
        ordenar.blubbleSortSalario(funcionarios, true);
        System.out.println("Array ORDEM DECRESCENTE: " + Arrays.toString(funcionarios));
        ordenar.blubbleSortSalario(funcionarios, false);
        System.out.println("Array CRESCENTE: " + Arrays.toString(funcionarios));

        System.out.println("\nORDEM ALFABETICA");
        System.out.println("Array ORIGINAL: " + Arrays.toString(funcionarios));
        ordenar.blubbleSortOrdemAlfabetica(funcionarios);
        System.out.println("Array ORDEM ALFABETICA: " + Arrays.toString(funcionarios));

    }
}
