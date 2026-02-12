package ex4;

public class ExportadorCsv extends ExportadorBase {
    @Override
    public void exportar(String dados, String destino) {
        super.exportar(dados, destino);
        System.out.println("Exportado via CSV");
    }
}
