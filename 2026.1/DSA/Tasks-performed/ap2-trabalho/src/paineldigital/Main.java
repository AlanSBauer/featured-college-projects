package paineldigital;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner input = new Scanner(System.in);
        PainelDigital painel = new PainelDigital();

        boolean menu = true;
        while (menu) {
            System.out.println(
                    "1 - Exibir e Avançar\n" +
                            "2 - Adicionar anúncio\n" +
                            "3 - Remover anúncio\n" +
                            "4 - Listar ciclo completo\n" +
                            "5 - Sair"
            );

            int opcao;
            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                input.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    painel.exibirEAvancar();
                    break;
                case 2:
                    try {
                        System.out.println("Empresa:");
                        String empresa = input.nextLine();

                        System.out.println("Descrição:");
                        String descricao = input.nextLine();

                        Anuncio anuncio = new Anuncio(empresa, descricao);

                        painel.adicionar(anuncio);

                        System.out.println("Anúncio adicionado com sucesso!");

                    } catch (Exception e) {
                        System.out.println("Erro ao criar anúncio!");
                        input.nextLine();
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Digite o ID do anúncio:");
                        int id = input.nextInt();
                        input.nextLine();

                        painel.remover(id);

                    } catch (Exception e) {
                        System.out.println("ID inválido!");
                        input.nextLine();
                    }
                    break;
                case 4:
                    painel.listar();
                    break;
                case 5:
                    menu = false;
                    System.out.println("Sistema encerrado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
