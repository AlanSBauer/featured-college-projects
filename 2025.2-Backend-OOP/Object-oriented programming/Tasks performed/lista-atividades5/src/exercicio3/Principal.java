package exercicio3;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--- Calculo de Frete ---");

        System.out.print("Digite qual o CEP (Ex: 00000-000): ");
        String cep = input.nextLine();

        System.out.print("Digite o peso do objeto em kg: ");
        float peso = input.nextFloat();
        input.nextLine();

        FreteBase base = new FreteBase();
        if (!base.cepValido(cep)) {
            System.out.println("Erro - CEP inválido!");
            return;
        }

        FreteRetirada retirada = new FreteRetirada();
        FreteCorreios correios = new FreteCorreios();
        FreteMotoboy motoboy = new FreteMotoboy();

        FreteBase[] fretes = { retirada, correios, motoboy };

        float menorValor = Float.MAX_VALUE;
        int menorPrazo = Integer.MAX_VALUE;
        FreteBase maisBarato = null;
        FreteBase maisRapido = null;

        for (FreteBase f : fretes) {
            float valor = f.valor(peso, cep);
            int prazo = f.prazo(cep);

            if (valor < menorValor) {
                menorValor = valor;
                maisBarato = f;
            }

            if (prazo < menorPrazo) {
                menorPrazo = prazo;
                maisRapido = f;
            }
        }


        System.out.println("\nResultado do cálculo:");
        System.out.println("Mais barato: " + maisBarato.getClass().getSimpleName() + " - R$ " + menorValor);
        System.out.println("Mais rápido: " + maisRapido.getClass().getSimpleName() + " - " + menorPrazo + " dia(s)");

        System.out.println();

        if(cep.equals("95560-000") && peso < 5) {
            System.out.println("Você também pode pedir para um motoboy levar seu objeto.");
            System.out.println("Valor do motoboy: R$" + motoboy.valor(peso, cep));
        }
    }
}
