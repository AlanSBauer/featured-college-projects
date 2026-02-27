package ex1;

public class AvisoBase implements Aviso {
    @Override
    public void enviarMensagem(String mensagem, String destino) {
        System.out.println("A mensagem é: " + mensagem);
        System.out.println("O destino é: " + destino);
    }

    @Override
    public String status() {
        return "OK";
    }
}
