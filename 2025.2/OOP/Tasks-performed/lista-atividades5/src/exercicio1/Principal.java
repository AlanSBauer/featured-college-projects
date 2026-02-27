package exercicio1;

import java.util.HashMap;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        EmailAviso email = new EmailAviso();
        PushAviso push = new PushAviso();
        SmsAviso sms = new SmsAviso();

        Map<String, Aviso> avisos = new HashMap<>();

        avisos.put("email", email);
        avisos.put("push", push);
        avisos.put("sms", sms);

        String mensagem = "Mensagem qualquer";
        String destino = "Usuario1234";

        for (Map.Entry<String, Aviso> entrada : avisos.entrySet()) {
            System.out.println("=== Enviando aviso tipo: " + entrada.getKey() + " ===");
            Aviso aviso = entrada.getValue();
            aviso.enviar(mensagem, destino);
            System.out.println("Status: " + aviso.status());
            System.out.println("------------------------");
        }
    }
}
