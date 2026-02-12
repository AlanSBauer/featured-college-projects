package exercicio3;

import java.util.ArrayList;
import java.util.Scanner;

public class Biblioteca {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Publicacao> publicacoes = new ArrayList<>();

        publicacoes.add(new Livro("Livro1", "Autor1", 2017, 250,"Ação"));
        publicacoes.add(new Livro("Livro2", "Autor2", 2023, 320, "Poesia"));
        publicacoes.add(new Revista("Revista1", "Autor1", 2019, 1, "Abril"));
        publicacoes.add(new Revista("Revista2", "Autor2", 2021, 1, "Outubro"));

        String opcao = "";
        while (!opcao.equals("5")) {
            System.out.println("Opções:");
            System.out.println("1 - Add Livro \n2 - Add Revista \n3 - Pesquisar autor\n4 - Ver todas Publicações \n5 - Sair");
            System.out.println("Qual a opcäo?");
            opcao = input.nextLine();

            if(opcao.equals("1")) {
                System.out.println("Nome do livro? ");
                String nomeLivro = input.nextLine();

                System.out.println("Autor do livro: ");
                String autorLivro = input.nextLine();

                System.out.println("Ano de Publicação: ");
                int anoPublicacao = input.nextInt();
                input.nextLine();

                System.out.println("Número de páginas: ");
                int numPaginas = input.nextInt();
                input.nextLine();

                System.out.println("Genero do livro: ");
                String generoLivro = input.nextLine();

                publicacoes.add(new Livro(nomeLivro, autorLivro, anoPublicacao, numPaginas, generoLivro));
                System.out.println("Livro adicionado!");
            } else if(opcao.equals("2")) {
                System.out.println("Nome da Revista? ");
                String nomeRevista = input.nextLine();

                System.out.println("Autor da Revista: ");
                String autorRevista = input.nextLine();

                System.out.println("Ano de Publicação: ");
                int anoPublicacao = input.nextInt();
                input.nextLine();

                System.out.println("Edição da Revista: ");
                int edicaoRevista = input.nextInt();
                input.nextLine();

                System.out.println("Mês de Publicação: ");
                String mesPublicacao = input.nextLine();

                publicacoes.add(new Revista(nomeRevista, autorRevista, anoPublicacao, edicaoRevista, mesPublicacao));
                System.out.println("Revista adicionada!");

            } else if (opcao.equals("3")) {
                System.out.println("Digite o nome do autor que deseja pesquisar: ");
                String autorPesquisa = input.nextLine();

                boolean autorEncontrado = false;

                System.out.println();
                System.out.println("Publicações do " + autorPesquisa);
                for(Publicacao p : publicacoes) {
                    if(p.getAutor().equalsIgnoreCase(autorPesquisa)) {
                        p.exibirInformacoes();
                        autorEncontrado = true;
                        System.out.println();
                    }
                }
                if(!autorEncontrado) {
                    System.out.println("Nenhuma publicação foi encontrada com esse autor.");
                }

                System.out.println();

            } else if (opcao.equals("4")) {
                System.out.println("==== Livros ====");
                for(Publicacao p : publicacoes) {
                    if(p instanceof Livro) {
                        p.exibirInformacoes();
                        System.out.println();
                    }
                }

                System.out.println();

                System.out.println("==== Revistas ====");
                for(Publicacao p : publicacoes) {
                    if(p instanceof Revista) {
                        p.exibirInformacoes();
                        System.out.println();
                    }
                }

                System.out.println();

            } else if (opcao.equals("5")) {
                System.out.println("Sistema encerrado!");
                break;
            } else {
                System.out.println("Opcão inválida!");
            }
        }

        input.close();
    }
}
