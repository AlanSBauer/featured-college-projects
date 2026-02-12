package exercicio1;

public class EmailAviso extends AvisoBase {

    @Override
    public void enviar(String mensagem, String destino) {
        super.enviar(mensagem, destino);
        System.out.println("Enviado por E-mail");
    }

    @Override
    public String status() {
        return super.status();
    }
}
