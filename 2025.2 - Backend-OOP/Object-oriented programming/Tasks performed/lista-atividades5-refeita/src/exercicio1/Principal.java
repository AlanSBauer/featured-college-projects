package exercicio1;

import java.util.*;

public class    Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Aviso> avisos = new ArrayList<>();
        ArrayList<Mensagem> mensagens = new ArrayList<>();

        Map<Mensagem, Aviso> avisosMap = new LinkedHashMap<>();

        boolean enviandoMensagem = true;

        while (enviandoMensagem) {
            System.out.println("Qual o conteudo do aviso?");
            String mensagem = input.nextLine();

            System.out.println("Qual o destino do aviso?");
            String destino = input.nextLine();

            System.out.println("Como deseja enviar o Aviso? 1 = SMS, 2 = EMAIL, 3 = PUSH");
            int tipoEnvio = input.nextInt();
            input.nextLine();

            switch (tipoEnvio) {
                case 1:
                    avisosMap.put(new Mensagem(mensagem, destino), new SmsAviso());
                    break;
                case 2:
                    avisosMap.put(new Mensagem(mensagem, destino), new EmailAviso());
                    break;
                case 3:
                    avisosMap.put(new Mensagem(mensagem, destino), new PushAviso());
                    break;
            }

            System.out.println("Deseja enviar outra mensagem? 1 = SIM, 2 = NAO");
            int resposta = input.nextInt();
            input.nextLine();

            if (resposta == 2) {
                enviandoMensagem = false;
            }
        }

        System.out.println("--- Avisos ---");

        for (Mensagem mensagem : avisosMap.keySet()) {
            Aviso aviso = avisosMap.get(mensagem);
            Principal.enviarAviso(aviso, mensagem.getMensagem(), mensagem.getDestino());
            System.out.println();
        }
    }

    public static void enviarAviso(Aviso aviso, String mensagem, String destino) {
        aviso.enviar(mensagem, destino);
    }
}