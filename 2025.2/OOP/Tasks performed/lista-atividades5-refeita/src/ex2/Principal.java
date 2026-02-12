package ex2;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Registro> registros = new ArrayList<>();
        int proximoId = 0;

        boolean rodando = true;
        while (rodando) {
            System.out.println("--- Menu ---");
            System.out.println("1 - Fazer pagamento");
            System.out.println("2 - Fazer estorno");
            System.out.println("3 - Listar regitros");
            System.out.println("4 - Sair");

            System.out.println("Digite a opção: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o meio de pagamento: 1 = Cartao, 2 = Pix, 3 = Boleto");
                    int meioPagamento = input.nextInt();
                    input.nextLine();

                    System.out.println("Digite o valor do pagamento: ");
                    float valorPagamento = input.nextFloat();
                    input.nextLine();

                    Registro registroPagamento = new Registro(valorPagamento, proximoId, "Pagamento");

                    if(meioPagamento == 1) {
                        PagamentoCartao cartao = new PagamentoCartao();
                        registroPagamento.setMetodo(cartao);
                        Principal.realizarPagamento(cartao, valorPagamento);
                    } else if (meioPagamento == 2) {
                        PagamentoPix pix = new PagamentoPix();
                        registroPagamento.setMetodo(pix);
                        Principal.realizarPagamento(pix, valorPagamento);
                    } else if (meioPagamento == 3) {
                        PagamentoBoleto boleto = new PagamentoBoleto();
                        registroPagamento.setMetodo(boleto);
                        Principal.realizarPagamento(boleto, valorPagamento);
                    } else {
                        System.out.println("Opção inválida!");
                    }

                    registros.add(registroPagamento);
                    proximoId++;
                    break;
                case 2:
                    System.out.println("Digite o id da transação para o estorno: ");
                    int idTransacao = input.nextInt();
                    input.nextLine();

                    Registro pagamentoOriginal = null;
                    for(Registro r : registros) {
                        if(r.getIdTransacao() == idTransacao && r.getTipo().equals("Pagamento")) {
                            pagamentoOriginal = r;
                            break;
                        }
                    }

                    if(pagamentoOriginal == null) {
                        System.out.println("Pagamento não encontrado ou já estornado!");
                        break;
                    }

                    Principal.realizarEstorno(pagamentoOriginal.getMetodo(), proximoId);

                    Registro registroEstorno = new Registro(pagamentoOriginal.getValor(), proximoId, "Estorno", pagamentoOriginal.getMetodo());
                    registros.add(registroEstorno);

                    proximoId++;
                    break;
                case 3:
                    for(Registro r : registros) {
                        System.out.println(r);
                    }
                    break;
                case 4:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    public static void realizarPagamento(Checkout metodo, float valor) {
        metodo.processar(valor);
    }

    public static void realizarEstorno(Checkout metodo, int id) {
        metodo.estornar(id);
    }
}
