package ex1;

import java.util.*;

public class Calculadora {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Double> resultados = new ArrayList<>();

        boolean menuRodando = true;

        while (menuRodando) {
            System.out.println("Digite o primeiro número: ");
            double num1 = input.nextDouble();
            input.nextLine();

            System.out.println("Digite o segundo número: ");
            double num2 = input.nextDouble();
            input.nextLine();

            System.out.println("Digite qual operação: \n1 - Somar \n2 - Subtrair \n3 - Multiplicar \n4 - Dividir");
            int operacao = input.nextInt();
            input.nextLine();

            double resultado = 0;

            switch (operacao) {
                case 1:
                    resultado = num1 + num2;
                    break;
                case 2:
                    resultado = num1 - num2;
                    break;
                case 3:
                    resultado = num1 * num2;
                    break;
                case 4:
                    if(num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        System.out.println("Divisão por 0 não permitida!");
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
                    continue;
            }

            resultados.add(resultado);

            System.out.println("Deseja continuar calculando? \n1 - SIM \n2 - NÃO");
            int resposta = input.nextInt();
            input.nextLine();

            if(resposta == 2) {
                menuRodando = false;
                System.out.println("Finalizado!");
            }
        }

        System.out.println("Resultados: ");
        for(double res : resultados) {
            System.out.println(res);
        }

        input.close();
    }
}
