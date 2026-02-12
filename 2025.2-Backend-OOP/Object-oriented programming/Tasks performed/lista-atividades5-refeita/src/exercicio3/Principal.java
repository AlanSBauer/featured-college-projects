package exercicio3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<Entregas, Frete> fretes = new LinkedHashMap<>();

        boolean estaCadastrando = true;

        while (estaCadastrando) {
            System.out.println("Digite seu CEP: ");
            String cep = input.nextLine();

            System.out.println("Digite o peso do produto: ");
            float pesoProduto = input.nextFloat();
            input.nextLine();

            if(cep.equals("123456789")) {
                System.out.println("Você pode fazer a retirada ou pedir para um motoboy: 1 = Retirada, 2 = Motoboy");
                int resposta = input.nextInt();
                input.nextLine();

                switch (resposta) {
                    case 1:
                        fretes.put(new Entregas(pesoProduto, cep), new FreteRetirada());
                        break;
                    case 2:
                        fretes.put(new Entregas(pesoProduto, cep), new FreteMotoboy());
                        break;
                }
            } else {
                System.out.println("Com esse CEP, os correios podem levar a entrega até você!");
                fretes.put(new Entregas(pesoProduto, cep), new FreteCorreios());
            }

            System.out.println("Deseja ver o valor de outra entrega? 1 = SIM, 2 = NAO");
            int respostaBreak = input.nextInt();
            input.nextLine();

            if(respostaBreak == 2) {
                estaCadastrando = false;
            }

        }

        System.out.println("------------------------------------------");
        System.out.println("Entregas a seguir: ");
        System.out.println("------------------------------------------");

        for(Entregas entregas : fretes.keySet()) {
            Frete frete = fretes.get(entregas);
            Principal.enviarFrete(frete, entregas.getPeso(), entregas.getCep());
        }
    }

    public static void enviarFrete(Frete frete, float peso, String cep) {
        frete.valor(peso, cep);
    }
}
