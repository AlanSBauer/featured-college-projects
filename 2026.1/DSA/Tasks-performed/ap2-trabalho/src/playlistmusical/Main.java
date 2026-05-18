package playlistmusical;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    static void main() {
        Scanner input = new Scanner(System.in);
        Playlist playlist = new Playlist();
        Musica musicaPronta1 = new Musica("Teste", "Artista teste", "Album teste", 120);
        Musica musicaPronta2 = new Musica("Qualquer nome", "Artista Qualquer", "Album Qualquer", 180);

        boolean menu = true;
        while (menu) {
            System.out.println("\n--- MENU ---");
            System.out.println(
                    "1 - Próxima música\n" +
                    "2 - Música anterior\n" +
                    "3 - Ordenar playlist\n" +
                    "4 - Tocar música\n" +
                    "5 - Adicionar música\n" +
                    "6 - Remover música\n" +
                    "7 - Listar músicas\n" +
                    "8 - Sair"
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
                    playlist.proximaMusica();
                    break;
                case 2:
                    playlist.musicaAnterior();
                    break;
                case 3:
                    System.out.println("1 - Título\n2 - Artista");

                    int ordem;
                    try {
                        ordem = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida!");
                        input.nextLine();
                        break;
                    }

                    if (ordem == 1) {
                        playlist.ordenar(true);
                    } else if (ordem == 2) {
                        playlist.ordenar(false);
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                case 4:
                    playlist.tocarMusica();
                    break;
                case 5:
                    System.out.println("Título:");
                    String titulo = input.nextLine();

                    System.out.println("Artista:");
                    String artista = input.nextLine();

                    System.out.println("Álbum:");
                    String album = input.nextLine();

                    int duracao;

                    try {
                        System.out.println("Duração:");
                        duracao = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        System.out.println("Duração inválida!");
                        input.nextLine();
                        break;
                    }

                    Musica musica = new Musica(titulo, artista, album, duracao);

                    System.out.println("Onde adicionar?");
                    System.out.println("1 - Início");
                    System.out.println("2 - Fim");
                    System.out.println("3 - Posição");

                    int pos;

                    try {
                        pos = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        System.out.println("Opção inválida!");
                        input.nextLine();
                        break;
                    }

                    if (pos == 1) {
                        playlist.adicionarNoInicio(musica);
                    } else if (pos == 2) {
                        playlist.adicionarNoFim(musica);
                    } else if (pos == 3) {

                        int p;

                        try {
                            System.out.println("Digite a posição:");
                            p = input.nextInt();
                            input.nextLine();
                        } catch (Exception e) {
                            System.out.println("Posição inválida!");
                            input.nextLine();
                            break;
                        }

                        playlist.adicionarNaPosicao(musica, p);

                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                case 6:
                    if (playlist.getTamanho() == 0) {
                        System.out.println("Playlist vazia!");
                        break;
                    }

                    System.out.println("Remover música por:");
                    System.out.println("1 - Título");
                    System.out.println("2 - Posição");

                    int opcaoRemover;

                    try {
                        opcaoRemover = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        System.out.println("Opção inválida!");
                        input.nextLine();
                        break;
                    }

                    if (opcaoRemover == 1) {

                        System.out.println("Digite o título:");
                        String tituloRemocao = input.nextLine();

                        playlist.removerMusicaPorTitulo(tituloRemocao);

                    } else if (opcaoRemover == 2) {

                        try {
                            System.out.println("Digite a posição:");
                            int posRemocao = input.nextInt();
                            input.nextLine();

                            playlist.removerPorPosicao(posRemocao);

                        } catch (Exception e) {
                            System.out.println("Posição inválida!");
                            input.nextLine();
                        }

                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                case 7:
                    playlist.listarMusicas();
                    break;
                case 8:
                    menu = false;
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        input.close();
    }
}
