package exercicio2;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Registro> registros = new ArrayList<>();
        int proximoId = 0;

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Fazer pagamento");
            System.out.println("2 - Fazer estorno");
            System.out.println("3 - Listar registros");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o metodo de pagamento: 1 = Cartao, 2 = Pix, 3 = Boleto");
                    int metodoPagamento = input.nextInt();
                    input.nextLine();

                    System.out.println("Digite o valor do pagamento: ");
                    float valor = input.nextFloat();
                    input.nextLine();

                    Registro registro = new Registro();
                    registro.setId(proximoId);
                    registro.setTipo("Pagamento");

                    if(metodoPagamento == 1) {
                        PagamentoCartao cartao = new PagamentoCartao();
                        registro.setMetodo(cartao);
                        registro.setValor(valor);
                        cartao.processar(valor);

                        registros.add(registro);
                    } else if (metodoPagamento == 2) {
                        PagamentoPix pix = new PagamentoPix();
                        registro.setMetodo(pix);
                        registro.setValor(valor);
                        pix.processar(valor);

                        registros.add(registro);
                    } else if (metodoPagamento == 3) {
                        PagamentoBoleto boleto = new PagamentoBoleto();
                        registro.setMetodo(boleto);
                        registro.setValor(valor);
                        boleto.processar(valor);

                        registros.add(registro);
                    } else {
                        System.out.println("Opcao invalida!");
                    }

                    proximoId++;

                    break;

                case 2:
                    Registro registroEstorno = new Registro();
                    registroEstorno.setId(proximoId);
                    registroEstorno.setTipo("Estorno");

                    System.out.println("ID do pagamento para estorno: ");
                    int idEstorno = input.nextInt();
                    input.nextLine();

                    Registro pagamentoOriginal = null;
                    for(Registro r : registros) {
                        if(r.getId() == idEstorno && r.getTipo().equals("Pagamento")) {
                            pagamentoOriginal = r;
                        }
                    }

                    if(pagamentoOriginal == null) {
                        System.out.println("Id de pagamento inválido ou ja estornado!");
                        break;
                    }

                    Checkout metodo = pagamentoOriginal.getMetodo();
                    registroEstorno.setValor(pagamentoOriginal.getValor());
                    registroEstorno.setMetodo(metodo);
                    registros.add(registroEstorno);
                    metodo.estornar(proximoId);

                    break;
                case 3:
                    System.out.println("--- REGISTROS ---");
                    for(Registro r : registros) {
                        System.out.println(r.toString());
                    }
                    break;
                case 4:
                    System.out.println("Sistema encerrado!");
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");

            }
        }
    }
}