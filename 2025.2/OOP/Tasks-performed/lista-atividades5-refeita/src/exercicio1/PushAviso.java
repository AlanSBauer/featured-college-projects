package exercicio1;

public class PushAviso extends AvisoBase {
    @Override
    public void enviar(String mensagem, String destino) {
        super.enviar(mensagem, destino);
        System.out.println("Enviado por Push");
    }

    @Override
    public String status() {
        return super.status();
    }
}
