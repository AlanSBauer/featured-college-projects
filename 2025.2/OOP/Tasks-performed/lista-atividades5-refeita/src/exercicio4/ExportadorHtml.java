package exercicio4;

public class ExportadorHtml extends ExportadorBase {
    @Override
    public void exportar(String dados, String destino) {
        super.exportar(dados, destino);
        System.out.println("Exportado via HTML");
    }
}
