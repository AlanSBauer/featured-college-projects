package ex4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Map<Arquivo, Exportador> arquivos = new LinkedHashMap<>();

        boolean exportando = true;
        while (exportando) {
            System.out.println("Digite qual o meio de exportação: 1 = PDF, 2 = CSV, 3 = HTML");
            int meioExportacao = input.nextInt();
            input.nextLine();

            System.out.println("Digite os dados que serao exportados: ");
            String dados = input.nextLine();

            System.out.println("Digite o destino de exportaçao: ");
            String destino = input.nextLine();

            switch (meioExportacao) {
                case 1:
                    arquivos.put(new Arquivo(dados,destino), new ExportadorPdf());
                    break;
                case 2:
                    arquivos.put(new Arquivo(dados, destino), new ExportadorCsv());
                    break;
                case 3:
                    arquivos.put(new Arquivo(dados, destino), new ExportadorHtml());
                    break;
                default:
                    System.out.println("Opção inválida.");
                    continue;
            }

            boolean exportandoNovamente = true;
            while (exportandoNovamente) {
                System.out.println();
                System.out.println("Deseja exportar os mesmos dados em outro arquivo? 1 = Sim, 2 = Nao");
                int resposta = input.nextInt();
                input.nextLine();

                if(resposta == 2) {
                    exportandoNovamente = false;
                    continue;
                }

                System.out.println("Por onde deseja exportar os dados novamente? 1 = PDF, 2 = CSV, 3 = HTML");
                int exportarNovamente = input.nextInt();
                input.nextLine();

                switch (exportarNovamente) {
                    case 1:
                        arquivos.put(new Arquivo(dados, destino), new ExportadorPdf());
                        break;
                    case 2:
                        arquivos.put(new Arquivo(dados, destino), new ExportadorCsv());
                        break;
                    case 3:
                        arquivos.put(new Arquivo(dados, destino), new ExportadorHtml());
                        break;
                    default:
                        System.out.println("--- ERRO ---");
                        break;
                }

            }
                System.out.println("Deseja exportar outros dados? 1 = SIM, 2 = NAO");
                int continuar = input.nextInt();
                input.nextLine();

                if(continuar == 2) {
                    exportando = false;
                }


        }

        System.out.println("------------------------------------------");
        System.out.println("Exportações a seguir: ");
        System.out.println("------------------------------------------");

        for(Arquivo arquivo : arquivos.keySet()) {
            Exportador exportador = arquivos.get(arquivo);
            Principal.enviarExportacao(exportador, arquivo.getDados(), arquivo.getDestino());
            System.out.println("------------------------------------------");
        }
    }
        public static void enviarExportacao(Exportador exportador, String dados, String destino) {
            exportador.exportar(dados, destino);
        }
}
