package ex4;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        FilaClientes clientes = new FilaClientes(5);
        ArrayList<Cliente> clientesAtendidos = new ArrayList<Cliente>();
        Scanner input = new Scanner(System.in);

        boolean menu = true;
        while (menu) {
            System.out.println("1 - Inserir Cliente na fila");
            System.out.println("2 - Atender Cliente");
            System.out.println("3 - Encerrar");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    if(!clientes.estaCheia()) {
                        System.out.println("Digite o nome do cliente: ");
                        String nome = input.nextLine();

                        System.out.println("Digite o tempo de atendimento estimado: ");
                        int tempo = input.nextInt();
                        input.nextLine();

                        Cliente cliente = new Cliente(nome, tempo);
                        clientes.enfileirar(cliente);
                    } else {
                        System.out.println("A fila está cheia!");
                    }
                    break;
                case 2:
                    if(!clientes.estaVazia()) {
                        Cliente clienteAtendido = clientes.desenfileirar();
                        System.out.println("Nome: " + clienteAtendido.getNome() + " - Tempo: " + clienteAtendido.getTempo());
                        clientesAtendidos.add(clienteAtendido);
                    } else {
                        System.out.println("Todos clientes foram atendidos!");
                        System.out.println();

                        int totalTempo = 0;

                        System.out.println("Lista dos atendidos:");
                        for(Cliente c : clientesAtendidos) {
                            System.out.println("Cliente: " + c.getNome() + " - Tempo: " + c.getTempo());
                            totalTempo += c.getTempo();
                        }

                        System.out.println();
                        System.out.println("Total de clientes: " + clientesAtendidos.size());
                        System.out.println("Tempo total: " + totalTempo + " minutos");
                        System.out.println();
                    }
                    break;
                case 3:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        input.close();
    }
}
