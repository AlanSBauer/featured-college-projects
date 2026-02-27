package exercicio1;

public class SmsAviso extends AvisoBase {
    @Override
    public void enviar(String mensagem, String destino) {
        super.enviar(mensagem, destino);
        System.out.println("Enviado via SMS");
    }

    @Override
    public String status() {
        return super.status();
    }
}
