package ex1;

public class SmsAviso extends AvisoBase {
    @Override
    public void enviarMensagem(String mensagem, String destino) {
        super.enviarMensagem(mensagem, destino);
        System.out.println("Enviado por SMS");
    }

    @Override
    public String status() {
        return super.status();
    }
}
