package exercicio1;

public class EmailAviso extends AvisoBase {
    @Override
    public void enviar(String mensagem, String destino) {
        super.enviar(mensagem, destino);
        System.out.println("Enviado via Email");
    }

    @Override
    public String status() {
        return super.status();
    }
}
