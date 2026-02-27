package exercicio4;

public class ExportadorBase implements Exportador {
    @Override
    public void exportar(String dados, String destino) {
        System.out.println("Dados exportados: " + dados);
        System.out.println("Destino: " + destino);
    }
}
