import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o primeiro número: ");
        double num1 = input.nextDouble();
        input.nextLine();

        System.out.println("Digite o segundo número: ");
        double num2 = input.nextDouble();
        input.nextLine();

        System.out.println("Digite qual operação: \n1 - Somar \n2 - Subtrair \n3 - Multiplicar \n4 - Dividir");
        int operacao = input.nextInt();
        input.nextLine();

        switch (operacao) {
            case 1:
                System.out.println("A soma é: " + (num1 + num2));
                break;
            case 2:
                System.out.println("A subtração é: " + (num1 - num2));
                break;
            case 3:
                System.out.println("A multiplicação é: " + (num1 * num2));
                break;
            case 4:
                if(num2 != 0) {
                    System.out.println("A divisão é: " + (num1 / num2));
                } else {
                    System.out.println("Divisão por 0 não permitida!");
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}