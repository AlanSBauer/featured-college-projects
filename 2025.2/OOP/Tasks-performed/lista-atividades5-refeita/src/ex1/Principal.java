package ex1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<Mensagem, Aviso> avisos = new HashMap<>();

        boolean enviandoMensagem = true;
        while(enviandoMensagem) {
            System.out.println("Qual o conteudo da mensagem? ");
            String mensagem = input.nextLine();

            System.out.println("Qual o destino da mensagem? ");
            String destinoMsg = input.nextLine();

            System.out.println("Como deseja enviar a mensagem? 1 = Email, 2 = SMS, 3 = Push");
            int opcaoEnvio = input.nextInt();
            input.nextLine();

            switch (opcaoEnvio) {
                case 1:
                    avisos.put(new Mensagem(mensagem, destinoMsg), new EmailAviso());
                    break;
                case 2:
                    avisos.put(new Mensagem(mensagem, destinoMsg), new SmsAviso());
                    break;
                case 3:
                    avisos.put(new Mensagem(mensagem, destinoMsg), new PushAviso());
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            System.out.println("Deseja enviar outra mensagem? 1 = SIM, 2 = NAO");
            int resposta = input.nextInt();
            input.nextLine();

            if(resposta == 2) {
                enviandoMensagem = false;
            }
        }

        System.out.println("--- Mensagens Enviadas ---");
        for(Mensagem mensagem : avisos.keySet()) {
            Aviso aviso = avisos.get(mensagem);
            Principal.enviarAviso(aviso, mensagem.getDescricao(), mensagem.getDestino());
            System.out.println();
        }
    }

    public static void enviarAviso(Aviso aviso, String mensagem, String destino) {
        aviso.enviarMensagem(mensagem, destino);
    }
}
