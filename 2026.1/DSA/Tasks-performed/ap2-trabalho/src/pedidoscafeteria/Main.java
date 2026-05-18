package pedidoscafeteria;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static void main() {
        Scanner input = new Scanner(System.in);

        Pilha pedidosCancelados = new Pilha();
        Fila pedidosPendentes = new Fila();

        boolean menu = true;

            while (menu) {
                try {
                    System.out.println(" --- Menu --- ");
                    System.out.println("1 - Adicionar novo pedido\n" +
                            "2 - Atender pedido\n" +
                            "3 - Cancelar pedido\n" +
                            "4 - Restaurar pedido\n" +
                            "5 - Imprimir pedidos pendentes\n" +
                            "6 - Imprimir pedidos cancelados\n" +
                            "7 - Sair");
                    int opcao = input.nextInt();
                    input.nextLine();

                    switch (opcao) {
                        case 1:
                            System.out.println("Qual a descrição do pedido? ");
                            String descricao = input.nextLine();

                            System.out.println("Qual o valor do pedido? ");
                            String valor = input.nextLine();

                            if(valor.trim().isEmpty()) {
                                System.out.println("Digite novamente, o valor não pode estar vazio!");
                                continue;
                            }

                            try {
                                BigDecimal valorBd = new BigDecimal(valor);

                                Pedido novoPedido = new Pedido(UUID.randomUUID(), descricao, valorBd);
                                pedidosPendentes.enqueue(novoPedido);

                                System.out.println("Pedido adicionado com sucesso!");
                                System.out.println();

                            } catch (NumberFormatException e) {
                                System.out.println("Digite um valor numérico válido!");
                                System.out.println();
                            }

                            break;
                        case 2:
                            System.out.println("Pedido Atendido: ");

                            Pedido pedidoAtendido = pedidosPendentes.dequeue();

                            if(pedidoAtendido == null) {
                                System.out.println("Não há pedidos pendentes!");
                                System.out.println();

                            } else {
                                System.out.println("ID: " + pedidoAtendido.getId() +
                                        " Descrição: " + pedidoAtendido.getDescricao());
                                System.out.println();
                            }

                            break;

                        case 3:
                            System.out.println("Pedido Cancelado: ");

                            Pedido pedidoRemovido = pedidosPendentes.dequeue();

                            if(pedidoRemovido == null) {
                                System.out.println("Não há pedidos para cancelar!");
                                System.out.println();

                            } else {
                                pedidosCancelados.push(pedidoRemovido);

                                System.out.println("ID: " + pedidoRemovido.getId() +
                                        " Descrição: " + pedidoRemovido.getDescricao());
                                System.out.println();
                            }

                            break;
                        case 4:
                            System.out.println("Pedido Restaurado: ");
                            Pedido pedidoRestaurado = pedidosCancelados.pop();
                            if(pedidoRestaurado == null) {
                                System.out.println("Não há pedido para ser restaurado!");
                                System.out.println();

                            } else {
                                pedidosPendentes.enqueue(pedidoRestaurado);
                                System.out.println("ID: " + pedidoRestaurado.getId() + " Descrição: " + pedidoRestaurado.getDescricao());
                                System.out.println();
                            }
                            break;
                        case 5:
                            System.out.println("--- Lista de Pedidos Pendentes ---");
                            pedidosPendentes.printQueue();
                            System.out.println();
                            break;
                        case 6:
                            System.out.println("--- Lista de Pedidos Cancelados ---");
                            pedidosCancelados.printStack();
                            System.out.println();
                            break;
                        case 7:
                            System.out.println();
                            System.out.println("Sistema encerrado com sucesso.");
                            menu = false;
                            break;
                        default:
                            System.out.println("Opção inválida! Por favor digite um número de 1 a 7.");
                            System.out.println();
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Opção inválida! Por favor digite um número de 1 a 7. ");
                    System.out.println();
                    input.nextLine();
                }
            }


        input.close();
    }
}
