package exercicio1;

public class AvisoBase implements Aviso {
    @Override
    public void enviar(String mensagem, String destino) {
        System.out.println("A mensagem é: " + mensagem);
        System.out.println("Para: " + destino);
    }

    @Override
    public String status() {
        return "OK";
    }
}
