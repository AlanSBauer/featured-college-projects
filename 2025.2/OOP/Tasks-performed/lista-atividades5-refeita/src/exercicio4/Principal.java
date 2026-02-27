package exercicio4;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<Arquivo, Exportador> exportacoes = new LinkedHashMap<>();

        boolean estaExportando = true;

        while (estaExportando) {
            System.out.println("Digite o meio de exportação: 1 = CSV, 2 = PDF, 3 = HTML");
            int respostaMeio = input.nextInt();
            input.nextLine();

            System.out.println("Digite os dados que serão exportados: ");
            String dadosExportacao = input.nextLine();

            System.out.println("Digite o destino: ");
            String destinoExportacao = input.nextLine();

            switch (respostaMeio) {
                case 1:
                    exportacoes.put(new Arquivo(dadosExportacao, destinoExportacao), new ExportadorCsv());
                    break;
                case 2:
                    exportacoes.put(new Arquivo(dadosExportacao, destinoExportacao), new ExportadorPdf());
                    break;
                case 3:
                    exportacoes.put(new Arquivo(dadosExportacao, destinoExportacao), new ExportadorHtml());
                    break;
            }

            boolean exportandoNovamente = true;

            while (exportandoNovamente) {
                System.out.println("-------------------------------");
                System.out.println("Deseja exportar os mesmos dados em outro arquivo? 1 = SIM, 2 = NAO");
                int resposta = input.nextInt();
                input.nextLine();

                if(resposta == 2) {
                    exportandoNovamente = false;
                    continue;
                }

                System.out.println("Por onde deseja exportar os dados novamente? 1 = CSV, 2 = PDF, 3 = HTML");
                int exportarNovamente = input.nextInt();
                input.nextLine();

                switch (exportarNovamente) {
                    case 1:
                        exportacoes.put(new Arquivo(dadosExportacao, destinoExportacao), new ExportadorCsv());
                        break;
                    case 2:
                        exportacoes.put(new Arquivo(dadosExportacao, destinoExportacao), new ExportadorPdf());
                        break;
                    case 3:
                        exportacoes.put(new Arquivo(dadosExportacao, destinoExportacao), new ExportadorHtml());
                        break;
                }
            }

            System.out.println("Deseja exportar outros dados? 1 = SIM, 2 = NAO");
            int continuar = input.nextInt();
            input.nextLine();

            if(continuar == 2) {
                estaExportando = false;
            }
        }

        System.out.println("------------------------------------------");
        System.out.println("Exportações a seguir: ");
        System.out.println("------------------------------------------");

        for(Arquivo arquivo : exportacoes.keySet()) {
            Exportador exportador = exportacoes.get(arquivo);
            exercicio4.Principal.enviarExportacao(exportador, arquivo.getDados(), arquivo.getDestino());
            System.out.println("------------------------------------------");
        }
    }

    public static void enviarExportacao(Exportador exportador, String dados, String destino) {
        exportador.exportar(dados, destino);
    }
}
