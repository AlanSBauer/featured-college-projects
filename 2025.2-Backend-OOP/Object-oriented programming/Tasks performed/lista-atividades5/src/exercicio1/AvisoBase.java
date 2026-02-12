package exercicio1;

public class AvisoBase implements Aviso {
    protected boolean falhou = false;

    @Override
    public void enviar(String mensagem, String destino) {
        System.out.println("A mensagem é: " + mensagem);
        System.out.println("O destino é: " + destino);
    }

    @Override
    public String status() {
        if(falhou) {
            return "Falha ao enviar!";
        }
        return "Enviado com sucesso!";
    }
}
