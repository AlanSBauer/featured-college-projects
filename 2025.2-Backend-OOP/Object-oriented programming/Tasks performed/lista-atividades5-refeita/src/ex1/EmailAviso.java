package ex1;

public class EmailAviso extends AvisoBase {
    @Override
    public void enviarMensagem(String mensagem, String destino) {
        super.enviarMensagem(mensagem, destino);
        System.out.println("Enviado por E-mail");
    }

    @Override
    public String status() {
        return super.status();
    }
}
