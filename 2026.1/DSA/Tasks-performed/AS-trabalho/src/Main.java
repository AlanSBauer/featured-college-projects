import estrutura.FilaHistorico;
import estrutura.FilaPrisao;
import estrutura.NoCasa;
import estrutura.NoJogador;
import estrutura.PilhaBaralho;
import model.Carta;
import model.Casa;
import model.HistoricoRodada;
import model.Imovel;
import model.Jogador;
import model.enums.EfeitoCarta;
import model.enums.TipoCarta;
import model.enums.TipoCasa;
import personagem.Advogado;
import personagem.Construtor;
import personagem.Especulador;
import personagem.Negociante;
import personagem.Personagem;
import service.ImovelService;
import service.JogadorService;
import service.TabuleiroService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static final ImovelService imovelService = new ImovelService();
    private static final JogadorService jogadorService = new JogadorService();
    private static final TabuleiroService tabuleiroService = new TabuleiroService();

    private static FilaHistorico historico = new FilaHistorico(10);
    private static FilaPrisao filaPrisao = new FilaPrisao();
    private static PilhaBaralho baralho = new PilhaBaralho();

    private static double saldoInicial = 1500000.0;
    private static double salario = 200000.0;
    private static double fianca = 20000.0;
    private static int maxRodadas = 20;
    private static int capacidadeHistorico = 10;
    private static int rodadaAtual = 1;
    private static double maiorAluguelCobrado = 0.0;
    private static Imovel imovelMaiorAluguel;

    public static void main(String[] args) {
        carregarDadosIniciais();
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int opcao;

        do {
            System.out.println();
            System.out.println("=== Banco Imobiliario Estruturas ===");
            System.out.println("1 - Gerenciar jogadores");
            System.out.println("2 - Gerenciar imoveis");
            System.out.println("3 - Exibir tabuleiro");
            System.out.println("4 - Configurar partida");
            System.out.println("5 - Iniciar partida");
            System.out.println("6 - Exibir historico de rodadas");
            System.out.println("0 - Sair");
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1:
                    menuJogadores();
                    break;
                case 2:
                    menuImoveis();
                    break;
                case 3:
                    System.out.println("Tabuleiro criado com lista duplamente ligada circular");
                    tabuleiroService.getTabuleiro().listarComLigacoes();
                    break;
                case 4:
                    configurarPartida();
                    break;
                case 5:
                    iniciarPartida();
                    break;
                case 6:
                    historico.listar();
                    break;
                case 0:
                    System.out.println("Encerrando.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void carregarDadosIniciais() {
        cadastrarImoveisPadrao();
        montarTabuleiro();
        cadastrarJogadoresPadrao();
    }

    private static void cadastrarImoveisPadrao() {
        imovelService.cadastrarImovel("Chale da Serra Gaucha", 200000, 1000);
        imovelService.cadastrarImovel("Flat Paulista", 350000, 1750);
        imovelService.cadastrarImovel("Sobrado de Ouro Preto", 400000, 2000);
        imovelService.cadastrarImovel("Pousada do Pantanal", 500000, 2500);
        imovelService.cadastrarImovel("Mansao de Gramado", 600000, 3000);
        imovelService.cadastrarImovel("Cobertura de Florianopolis", 750000, 3750);
        imovelService.cadastrarImovel("Fazenda do Cerrado", 280000, 1400);
        imovelService.cadastrarImovel("Bangalo de Buzios", 450000, 2250);
        imovelService.cadastrarImovel("Penthouse de Salvador", 850000, 4250);
        imovelService.cadastrarImovel("Casa de Bonito", 220000, 1100);
        imovelService.cadastrarImovel("Palacete de Petropolis", 1000000, 5000);
        imovelService.cadastrarImovel("Rancho do Vale do Sao Francisco", 310000, 1550);
    }

    private static void montarTabuleiro() {
        ArrayList<Imovel> imoveis = imovelService.getImoveis();

        tabuleiroService.adicionarCasa(new Casa("Inicio", TipoCasa.INICIO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(0)));
        tabuleiroService.adicionarCasa(new Casa("Sorte/Reves", TipoCasa.SORTE_REVES));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(1)));
        tabuleiroService.adicionarCasa(new Casa("Imposto", TipoCasa.IMPOSTO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(2)));
        tabuleiroService.adicionarCasa(new Casa("Restituicao", TipoCasa.RESTITUICAO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(3)));
        tabuleiroService.adicionarCasa(new Casa("Prisao", TipoCasa.PRISAO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(4)));
        tabuleiroService.adicionarCasa(new Casa("Leilao", TipoCasa.LEILAO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(5)));
        tabuleiroService.adicionarCasa(new Casa("Sorte/Reves", TipoCasa.SORTE_REVES));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(6)));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(7)));
        tabuleiroService.adicionarCasa(new Casa("Imposto", TipoCasa.IMPOSTO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(8)));
        tabuleiroService.adicionarCasa(new Casa("Sorte/Reves", TipoCasa.SORTE_REVES));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(9)));
        tabuleiroService.adicionarCasa(new Casa("Restituicao", TipoCasa.RESTITUICAO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(10)));
        tabuleiroService.adicionarCasa(new Casa("Leilao", TipoCasa.LEILAO));
        tabuleiroService.adicionarCasa(new Casa(TipoCasa.IMOVEL, imoveis.get(11)));
    }

    private static void cadastrarJogadoresPadrao() {
        jogadorService.cadastrarJogador(new Jogador("Marina", saldoInicial, tabuleiroService.getTabuleiro().getInicio(), new Especulador()));
        jogadorService.cadastrarJogador(new Jogador("Rafael", saldoInicial, tabuleiroService.getTabuleiro().getInicio(), new Negociante()));
        jogadorService.cadastrarJogador(new Jogador("Livia", saldoInicial, tabuleiroService.getTabuleiro().getInicio(), new Advogado()));
        jogadorService.cadastrarJogador(new Jogador("Gustavo", saldoInicial, tabuleiroService.getTabuleiro().getInicio(), new Construtor()));
    }

    private static void menuJogadores() {
        int opcao;

        do {
            System.out.println();
            System.out.println("=== Jogadores ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Editar");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1:
                    System.out.println("Cadastro de jogador");
                    String nome = lerTexto("Nome: ");
                    Personagem personagem = escolherPersonagem();
                    jogadorService.cadastrarJogador(new Jogador(nome, saldoInicial, tabuleiroService.getTabuleiro().getInicio(), personagem));
                    break;
                case 2:
                    jogadorService.listarJogadores();
                    break;
                case 3:
                    int idEditar = lerInt("ID do jogador: ");
                    String novoNome = lerTexto("Novo nome: ");
                    double novoSaldo = lerDouble("Novo saldo: ");
                    jogadorService.editarJogador(idEditar, novoNome, novoSaldo);
                    break;
                case 4:
                    int idRemover = lerInt("ID do jogador: ");
                    jogadorService.removerJogador(idRemover);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (opcao != 0);

    }

    private static Personagem escolherPersonagem() {
        System.out.println("1 - Especulador (+20% salario, +10% imposto)");
        System.out.println("2 - Negociante (-10% aluguel pago)");
        System.out.println("3 - Advogado (uma saida gratis da prisao)");
        System.out.println("4 - Construtor (+15% aluguel base ao comprar)");
        int opcao = lerInt("Personagem: ");

        switch (opcao) {
            case 1:
                return new Especulador();
            case 2:
                return new Negociante();
            case 3:
                return new Advogado();
            case 4:
                return new Construtor();
            default:
                System.out.println("Personagem invalido. Usando Negociante.");
                return new Negociante();
        }
    }

    private static void menuImoveis() {
        int opcao;

        do {
            System.out.println();
            System.out.println("=== Imoveis ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Editar");
            System.out.println("4 - Remover");
            System.out.println("0 - Voltar");
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1:
                    System.out.println("Cadastro de propriedade");
                    imovelService.cadastrarImovel(
                            lerTexto("Nome: "),
                            lerDouble("Valor de compra: "),
                            lerDouble("Aluguel base: ")
                    );
                    break;
                case 2:
                    System.out.println("Listagem de propriedades");
                    imovelService.listarImoveis();
                    break;
                case 3:
                    imovelService.editarImovel(
                            lerInt("ID: "),
                            lerTexto("Nome: "),
                            lerDouble("Valor de compra: "),
                            lerDouble("Aluguel base: ")
                    );
                    break;
                case 4:
                    imovelService.removerImovel(lerInt("ID: "));
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void configurarPartida() {
        saldoInicial = lerDouble("Saldo inicial: ");
        salario = lerDouble("Salario por volta: ");
        maxRodadas = lerInt("Maximo de rodadas: ");
        fianca = lerDouble("Fianca: ");
        capacidadeHistorico = lerInt("Capacidade do historico: ");
        historico = new FilaHistorico(capacidadeHistorico);
        System.out.println("Configuracoes atualizadas.");
    }

    private static void iniciarPartida() {
        if (jogadorService.getJogadores().size() < 2) {
            System.out.println("Cadastre ao menos 2 jogadores antes de iniciar.");
            return;
        }

        if (imovelService.getImoveis().size() < 10) {
            System.out.println("Cadastre ao menos 10 imoveis antes de iniciar.");
            return;
        }

        prepararNovaPartida();
        System.out.println();
        System.out.println("=== Inicio da partida ===");
        System.out.println("Partida interativa: em cada turno o jogador decide quando rolar os dados, comprar imoveis, pagar fianca e dar lances.");

        while (rodadaAtual <= maxRodadas && contarJogadoresAtivos() > 1) {
            System.out.println();
            System.out.println("--- Rodada " + rodadaAtual + " ---");

            for (Jogador jogador : jogadorService.getJogadores()) {
                if (jogador.isFalido()) {
                    continue;
                }

                if (!executarTurnoInterativo(jogador)) {
                    imprimirRelatorioFinal();
                    return;
                }

                verificarFalencia(jogador);

                if (contarJogadoresAtivos() <= 1) {
                    break;
                }
            }

            rodadaAtual++;
        }

        imprimirRelatorioFinal();
    }

    private static void prepararNovaPartida() {
        rodadaAtual = 1;
        historico = new FilaHistorico(capacidadeHistorico);
        filaPrisao = new FilaPrisao();
        baralho = new PilhaBaralho();
        maiorAluguelCobrado = 0.0;
        imovelMaiorAluguel = null;

        for (Imovel imovel : imovelService.getImoveis()) {
            imovel.resetarParaNovaPartida();
        }

        for (Jogador jogador : jogadorService.getJogadores()) {
            jogador.setSaldo(saldoInicial);
            jogador.setPosicaoAtual(tabuleiroService.getTabuleiro().getInicio());
            jogador.getPropriedades().clear();
            jogador.setIsencaoAdvogadoUsada(false);
            jogador.setPreso(false);
            jogador.setFalido(false);
            jogador.setTentativasPrisao(0);
            jogador.setVoltasCompletas(0);
        }

        reabastecerBaralho();
    }

    private static boolean executarTurnoInterativo(Jogador jogador) {
        while (true) {
            System.out.println();
            System.out.println("Turno de " + jogador.getNome()
                    + " | personagem: " + jogador.getPersonagem().getNome()
                    + " | saldo: " + dinheiro(jogador.getSaldo())
                    + " | casa atual: " + jogador.getPosicaoAtual().getCasa().getNome()
                    + " | propriedades: " + jogador.getPropriedades().size()
                    + (jogador.isPreso() ? " | PRESO" : ""));
            System.out.println("1 - " + (jogador.isPreso() ? "Tentar sair da prisao" : "Lancar dados"));
            System.out.println("2 - Ver jogadores");
            System.out.println("3 - Ver historico");
            System.out.println("4 - Ver tabuleiro");
            System.out.println("5 - Encerrar partida agora");

            int opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1:
                    if (jogador.isPreso()) {
                        tentarSairDaPrisao(jogador, false);
                    } else {
                        jogarTurno(jogador, false);
                    }
                    return true;
                case 2:
                    jogadorService.listarJogadores();
                    break;
                case 3:
                    historico.listar();
                    break;
                case 4:
                    tabuleiroService.getTabuleiro().listarComLigacoes();
                    break;
                case 5:
                    return false;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }

    private static void jogarTurno(Jogador jogador, boolean automatico) {
        int dado1 = rolarDado();
        int dado2 = rolarDado();
        int soma = dado1 + dado2;
        double saldoAntes = jogador.getSaldo();

        System.out.println();
        System.out.println(jogador.getNome() + " joga. Dados: " + dado1 + " + " + dado2 + " = " + soma);
        moverParaFrente(jogador, soma);
        String efeito = processarCasaAtual(jogador, automatico);

        historico.registrar(new HistoricoRodada(
                rodadaAtual,
                jogador.getNome(),
                soma,
                jogador.getPosicaoAtual().getCasa().getNome(),
                efeito + " | saldo: " + dinheiro(saldoAntes) + " -> " + dinheiro(jogador.getSaldo())
        ));
    }

    private static String processarCasaAtual(Jogador jogador, boolean automatico) {
        Casa casa = jogador.getPosicaoAtual().getCasa();
        System.out.println("Parou em: " + casa.getNome() + " [" + casa.getTipo() + "]");

        switch (casa.getTipo()) {
            case INICIO:
                return "Parou no Inicio";
            case IMOVEL:
                return processarImovel(jogador, casa.getImovel(), automatico);
            case IMPOSTO:
                return processarImposto(jogador);
            case RESTITUICAO:
                return processarRestituicao(jogador);
            case PRISAO:
                return "Parou na Prisao (sem prisao)";
            case LEILAO:
                return realizarLeilao(jogador, automatico);
            case SORTE_REVES:
                return processarCarta(jogador, automatico);
            default:
                return "Sem efeito";
        }
    }

    private static String processarImovel(Jogador visitante, Imovel imovel, boolean automatico) {
        if (imovel == null) {
            return "Casa de imovel sem cadastro";
        }

        if (imovel.getDono() == visitante) {
            System.out.println("O dono parou na propria propriedade. Nada acontece.");
            return "Dono visitou seu proprio imovel";
        }

        imovelService.registrarVisita(imovel);

        if (imovel.getDono() == null) {
            boolean deveComprar = automatico || lerSimNao("Imovel sem dono. Comprar por " + dinheiro(imovel.getValorCompra()) + "?");

            if (deveComprar) {
                return comprarImovel(visitante, imovel);
            }

            return "Imovel sem dono visitado; demanda agora " + formatarNumero(imovel.getMultiplicadorDemanda());
        }

        Jogador dono = imovel.getDono();
        double aluguelBase = imovel.calcularAluguel();
        double aluguelFinal = visitante.getPersonagem().modificarAluguel(aluguelBase);

        visitante.setSaldo(visitante.getSaldo() - aluguelFinal);
        dono.setSaldo(dono.getSaldo() + aluguelFinal);

        if (aluguelFinal > maiorAluguelCobrado) {
            maiorAluguelCobrado = aluguelFinal;
            imovelMaiorAluguel = imovel;
        }

        System.out.println("Pagamento de aluguel");
        System.out.println("Imovel: " + imovel.getNome());
        System.out.println("Dono: " + dono.getNome());
        System.out.println("Multiplicador de demanda: " + formatarNumero(imovel.getMultiplicadorDemanda()));
        System.out.println("Aluguel base calculado: " + dinheiro(aluguelBase));

        if ("Negociante".equals(visitante.getPersonagem().getNome())) {
            System.out.println("Habilidade passiva ativa: Negociante paga 10% menos aluguel.");
        }

        System.out.println(visitante.getNome() + " pagou " + dinheiro(aluguelFinal) + " para " + dono.getNome());
        return "Aluguel pago: " + dinheiro(aluguelFinal);
    }

    private static String comprarImovel(Jogador jogador, Imovel imovel) {
        if (jogador.getSaldo() < imovel.getValorCompra()) {
            System.out.println("Saldo insuficiente para comprar " + imovel.getNome());
            return "Nao comprou por saldo insuficiente";
        }

        double aluguelOriginal = imovel.getAluguelBase();

        if ("Construtor".equals(jogador.getPersonagem().getNome())) {
            imovel.setAluguelBase(jogador.getPersonagem().modificarAluguel(imovel.getAluguelBase()));
            System.out.println("Habilidade passiva ativa: Construtor aumentou o aluguel base de "
                    + dinheiro(aluguelOriginal) + " para " + dinheiro(imovel.getAluguelBase()));
        }

        imovel.setDono(jogador);
        jogador.getPropriedades().add(imovel);
        jogador.setSaldo(jogador.getSaldo() - imovel.getValorCompra());

        System.out.println("Compra de propriedade");
        System.out.println(jogador.getNome() + " comprou " + imovel.getNome()
                + " por " + dinheiro(imovel.getValorCompra())
                + ". Saldo atual: " + dinheiro(jogador.getSaldo()));
        return "Comprou " + imovel.getNome();
    }

    private static String processarImposto(Jogador jogador) {
        double patrimonio = jogador.calcularPatrimonio();
        double impostoBase = patrimonio * 0.05;
        double impostoFinal = jogador.getPersonagem().modificarImposto(impostoBase);

        jogador.setSaldo(jogador.getSaldo() - impostoFinal);

        if ("Especulador".equals(jogador.getPersonagem().getNome())) {
            System.out.println("Habilidade passiva ativa: Especulador paga 10% a mais de imposto.");
        }

        System.out.println("Imposto: 5% do patrimonio " + dinheiro(patrimonio)
                + " = " + dinheiro(impostoFinal));
        return "Pagou imposto de " + dinheiro(impostoFinal);
    }

    private static String processarRestituicao(Jogador jogador) {
        double valor = salario * 0.10;
        jogador.setSaldo(jogador.getSaldo() + valor);
        System.out.println("Restituicao recebida: " + dinheiro(valor));
        return "Recebeu restituicao de " + dinheiro(valor);
    }

    private static String processarCarta(Jogador jogador, boolean automatico) {
        if (baralho.estaVazia()) {
            reabastecerBaralho();
        }

        Carta carta = baralho.pegarCarta();
        System.out.println("Carta de Sorte/Reves retirada do topo da pilha");
        System.out.println("Carta: " + carta);

        switch (carta.getEfeitoCarta()) {
            case GANHAR_DINHEIRO:
                jogador.setSaldo(jogador.getSaldo() + carta.getValor());
                System.out.println(jogador.getNome() + " recebeu " + dinheiro(carta.getValor()));
                return "Carta: recebeu " + dinheiro(carta.getValor());
            case PERDER_DINHEIRO:
                jogador.setSaldo(jogador.getSaldo() - carta.getValor());
                System.out.println(jogador.getNome() + " pagou " + dinheiro(carta.getValor()));
                return "Carta: pagou " + dinheiro(carta.getValor());
            case AVANCAR:
                if (carta.getQuantidadeCasas() >= 99) {
                    moverAteInicio(jogador);
                } else {
                    moverParaFrente(jogador, (int)carta.getQuantidadeCasas());
                }
                return "Carta: avancou para " + jogador.getPosicaoAtual().getCasa().getNome()
                        + " / " + processarCasaAtual(jogador, automatico);
            case RETROCEDER:
                moverParaTras(jogador, (int)carta.getQuantidadeCasas());
                return "Carta: retrocedeu para " + jogador.getPosicaoAtual().getCasa().getNome()
                        + " / " + processarCasaAtual(jogador, automatico);
            case IR_PARA_PRISAO:
                enviarParaPrisao(jogador);
                return "Carta: foi para prisao";
            default:
                return "Carta sem efeito";
        }
    }

    private static void moverParaFrente(Jogador jogador, int passos) {
        NoCasa atual = jogador.getPosicaoAtual();

        for (int i = 0; i < passos; i++) {
            atual = atual.getProximo();

            if (atual.getCasa().getTipo() == TipoCasa.INICIO) {
                pagarSalario(jogador);
            }
        }

        jogador.setPosicaoAtual(atual);
    }

    private static void moverAteInicio(Jogador jogador) {
        NoCasa atual = jogador.getPosicaoAtual();

        do {
            atual = atual.getProximo();

            if (atual.getCasa().getTipo() == TipoCasa.INICIO) {
                pagarSalario(jogador);
            }
        } while (atual.getCasa().getTipo() != TipoCasa.INICIO);

        jogador.setPosicaoAtual(atual);
    }

    private static void moverParaTras(Jogador jogador, int passos) {
        NoCasa atual = jogador.getPosicaoAtual();

        for (int i = 0; i < passos; i++) {
            atual = atual.getAnterior();

            if (atual.getCasa().getTipo() == TipoCasa.INICIO) {
                System.out.println("Retrocesso passou pelo Inicio. Salario nao aplicado em movimento para tras.");
            }
        }

        jogador.setPosicaoAtual(atual);
    }

    private static void pagarSalario(Jogador jogador) {
        double valor = jogador.getPersonagem().bonusSalario(salario);
        jogador.setSaldo(jogador.getSaldo() + valor);
        jogador.setVoltasCompletas(jogador.getVoltasCompletas() + 1);

        System.out.println("Passagem pelo Inicio: " + jogador.getNome()
                + " recebeu salario de " + dinheiro(valor));

        if ("Especulador".equals(jogador.getPersonagem().getNome())) {
            System.out.println("Habilidade passiva ativa: Especulador recebeu +20% no salario.");
        }
    }

    private static void enviarParaPrisao(Jogador jogador) {
        if (jogador.isPreso()) {
            return;
        }

        NoCasa prisao = encontrarCasa(TipoCasa.PRISAO);

        if (prisao != null) {
            jogador.setPosicaoAtual(prisao);
        }

        jogador.setPreso(true);
        jogador.setTentativasPrisao(0);
        filaPrisao.enfileirar(jogador);

        System.out.println("Prisao - entrada na fila");
        System.out.println(jogador.getNome() + " entrou na fila de soltura. Posicao na fila: "
                + posicaoNaFilaPrisao(jogador));
    }

    private static void tentarSairDaPrisao(Jogador jogador, boolean automatico) {
        System.out.println("Prisao - tentativa de saida");

        if (filaPrisao.espiar() != jogador) {
            System.out.println(jogador.getNome() + " ainda aguarda na fila. Posicao: " + posicaoNaFilaPrisao(jogador));
            historico.registrar(new HistoricoRodada(rodadaAtual, jogador.getNome(), 0, "Prisao", "Aguardou fila de soltura"));
            return;
        }

        if (jogador.getPersonagem().podeSairSemFianca() && !jogador.isIsencaoAdvogadoUsada()) {
            jogador.setIsencaoAdvogadoUsada(true);
            soltarDaPrisao(jogador);
            System.out.println("Advogado usou a isencao de fianca uma unica vez e saiu sem custo.");
            historico.registrar(new HistoricoRodada(rodadaAtual, jogador.getNome(), 0, "Prisao", "Saiu com isencao do Advogado"));
            return;
        }

        boolean pagar = automatico && jogador.getSaldo() >= fianca;

        if (!automatico) {
            pagar = lerSimNao("Pagar fianca de " + dinheiro(fianca) + "?");
        }

        if (pagar && jogador.getSaldo() >= fianca) {
            jogador.setSaldo(jogador.getSaldo() - fianca);
            soltarDaPrisao(jogador);
            System.out.println(jogador.getNome() + " pagou fianca de " + dinheiro(fianca) + " e saiu.");
            historico.registrar(new HistoricoRodada(rodadaAtual, jogador.getNome(), 0, "Prisao", "Pagou fianca e saiu"));
            return;
        }

        int dado1 = rolarDado();
        int dado2 = rolarDado();
        jogador.setTentativasPrisao(jogador.getTentativasPrisao() + 1);
        System.out.println("Tentativa " + jogador.getTentativasPrisao() + ": dados " + dado1 + " e " + dado2);

        if (dado1 == dado2) {
            soltarDaPrisao(jogador);
            System.out.println("Dados duplos! Saiu e avancou " + (dado1 + dado2) + " casas.");
            moverParaFrente(jogador, dado1 + dado2);
            processarCasaAtual(jogador, automatico);
        } else if (jogador.getTentativasPrisao() >= 3) {
            soltarDaPrisao(jogador);
            System.out.println("Terceira tentativa sem duplos. Saiu, mas nao joga nesta rodada.");
        } else {
            System.out.println("Nao conseguiu sair nesta rodada.");
        }

        historico.registrar(new HistoricoRodada(rodadaAtual, jogador.getNome(), dado1 + dado2, "Prisao", "Tentou sair da prisao"));
    }

    private static void soltarDaPrisao(Jogador jogador) {
        filaPrisao.desenfileirar();
        jogador.setPreso(false);
        jogador.setTentativasPrisao(0);
    }

    private static String realizarLeilao(Jogador jogadorQueParou, boolean automatico) {
        Imovel imovel = sortearImovelSemDono();

        if (imovel == null) {
            System.out.println("Nao ha imoveis sem dono para leilao.");
            return "Leilao sem imoveis disponiveis";
        }

        System.out.println("Leilao iniciado");
        System.out.println("Imovel sorteado: " + imovel.getNome() + " | preco original: " + dinheiro(imovel.getValorCompra()));

        Jogador vencedor = null;
        double maiorLance = 0.0;
        ArrayList<Jogador> jogadores = jogadorService.getJogadores();
        int indiceInicial = jogadores.indexOf(jogadorQueParou) + 1;

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get((indiceInicial + i) % jogadores.size());

            if (jogador.isFalido() || jogador == imovel.getDono()) {
                continue;
            }

            double lance = automatico
                    ? Math.min(jogador.getSaldo(), imovel.getValorCompra() * (0.50 + (0.05 * i)))
                    : lerLanceValido(jogador);

            if (lance > maiorLance && lance <= jogador.getSaldo()) {
                maiorLance = lance;
                vencedor = jogador;
            }

            System.out.println(jogador.getNome() + " ofereceu " + dinheiro(lance));
        }

        if (vencedor != null && maiorLance >= imovel.getValorCompra() * 0.50) {
            imovel.setDono(vencedor);
            vencedor.getPropriedades().add(imovel);
            vencedor.setSaldo(vencedor.getSaldo() - maiorLance);
            System.out.println("Vencedor: " + vencedor.getNome() + " arrematou por " + dinheiro(maiorLance));
            return "Leilao arrematado por " + vencedor.getNome();
        }

        System.out.println("Nenhum lance atingiu 50% do valor original. Imovel continua sem dono.");
        return "Leilao sem arremate";
    }

    private static Imovel sortearImovelSemDono() {
        ArrayList<Imovel> disponiveis = new ArrayList<>();

        for (Imovel imovel : imovelService.getImoveis()) {
            if (imovel.getDono() == null) {
                disponiveis.add(imovel);
            }
        }

        if (disponiveis.isEmpty()) {
            return null;
        }

        return disponiveis.get(random.nextInt(disponiveis.size()));
    }

    private static void verificarFalencia(Jogador jogador) {
        if (jogador.getSaldo() >= 0 || jogador.isFalido()) {
            return;
        }

        if (!jogador.getPropriedades().isEmpty()) {
            System.out.println(jogador.getNome() + " esta com saldo negativo, mas ainda possui imoveis.");
            return;
        }

        // Devolver quaisquer propriedades do jogador ao pool (remover dono)
        for (Imovel imovel : new ArrayList<>(jogador.getPropriedades())) {
            imovel.setDono(null);
        }
        jogador.getPropriedades().clear();

        jogador.setFalido(true);
        jogador.setPreso(false);
        System.out.println("Falencia");
        System.out.println(jogador.getNome() + " foi declarado falido. Propriedades retornaram ao pool.");
    }

    public static void executarTestes() {
        // test/demo helpers removed for delivery
    }

        // Demonstration helpers removed for delivery

    private static void imprimirRelatorioFinal() {
        System.out.println();
        System.out.println("Encerramento da partida e relatorio final");
        System.out.println("Rodadas executadas: " + (rodadaAtual - 1));
        System.out.println("Classificacao por patrimonio:");

        ArrayList<Jogador> ranking = new ArrayList<>(jogadorService.getJogadores());
        Collections.sort(ranking, new Comparator<Jogador>() {
            @Override
            public int compare(Jogador a, Jogador b) {
                return Double.compare(b.calcularPatrimonio(), a.calcularPatrimonio());
            }
        });

        for (int i = 0; i < ranking.size(); i++) {
            Jogador jogador = ranking.get(i);
            System.out.println((i + 1) + " - " + jogador.getNome()
                    + " | patrimonio: " + dinheiro(jogador.calcularPatrimonio())
                    + " | voltas: " + jogador.getVoltasCompletas()
                    + " | propriedades: " + jogador.getPropriedades().size()
                    + " | status: " + (jogador.isFalido() ? "Falido" : "Ativo"));
        }

        if (imovelMaiorAluguel != null) {
            System.out.println("Maior aluguel cobrado: " + imovelMaiorAluguel.getNome()
                    + " com " + dinheiro(maiorAluguelCobrado));
        } else {
            System.out.println("Maior aluguel cobrado: nenhum aluguel foi cobrado.");
        }

        System.out.println("Historico final:");
        historico.listar();
    }

    private static void reabastecerBaralho() {
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta(TipoCarta.SORTE, EfeitoCarta.GANHAR_DINHEIRO, "Receba R$ 50000 do banco", 50000, 0));
        cartas.add(new Carta(TipoCarta.SORTE, EfeitoCarta.GANHAR_DINHEIRO, "Receba R$ 30000 por consultoria", 30000, 0));
        cartas.add(new Carta(TipoCarta.SORTE, EfeitoCarta.AVANCAR, "Avance 3 casas", 0, 3));
        cartas.add(new Carta(TipoCarta.SORTE, EfeitoCarta.AVANCAR, "Avance 5 casas", 0, 5));
        cartas.add(new Carta(TipoCarta.SORTE, EfeitoCarta.AVANCAR, "Avance diretamente para o Inicio", 0, 99));
        cartas.add(new Carta(TipoCarta.SORTE, EfeitoCarta.GANHAR_DINHEIRO, "Receba R$ 70000 de dividendos", 70000, 0));
        cartas.add(new Carta(TipoCarta.REVES, EfeitoCarta.PERDER_DINHEIRO, "Pague R$ 25000 ao banco", 25000, 0));
        cartas.add(new Carta(TipoCarta.REVES, EfeitoCarta.PERDER_DINHEIRO, "Pague R$ 40000 de manutencao", 40000, 0));
        cartas.add(new Carta(TipoCarta.REVES, EfeitoCarta.RETROCEDER, "Volte 2 casas", 0, 2));
        cartas.add(new Carta(TipoCarta.REVES, EfeitoCarta.RETROCEDER, "Volte 4 casas", 0, 4));
        cartas.add(new Carta(TipoCarta.REVES, EfeitoCarta.IR_PARA_PRISAO, "Va diretamente para a prisao", 0, 0));
        cartas.add(new Carta(TipoCarta.REVES, EfeitoCarta.PERDER_DINHEIRO, "Pague R$ 60000 por multa", 60000, 0));

        Collections.shuffle(cartas, random);

        for (Carta carta : cartas) {
            baralho.empilhar(carta);
        }

        System.out.println("Baralho reabastecido e embaralhado com 12 cartas na pilha.");
    }

    private static NoCasa encontrarCasa(TipoCasa tipo) {
        NoCasa atual = tabuleiroService.getTabuleiro().getInicio();

        if (atual == null) {
            return null;
        }

        do {
            if (atual.getCasa().getTipo() == tipo) {
                return atual;
            }

            atual = atual.getProximo();
        } while (atual != tabuleiroService.getTabuleiro().getInicio());

        return null;
    }

    private static int posicaoNaFilaPrisao(Jogador jogador) {
        NoJogador atual = filaPrisao.getInicio();
        int posicao = 1;

        while (atual != null) {
            if (atual.getJogador() == jogador) {
                return posicao;
            }

            atual = atual.getProximo();
            posicao++;
        }

        return -1;
    }

    private static int contarJogadoresAtivos() {
        int total = 0;

        for (Jogador jogador : jogadorService.getJogadores()) {
            if (!jogador.isFalido()) {
                total++;
            }
        }

        return total;
    }

    private static int rolarDado() {
        return random.nextInt(6) + 1;
    }

    private static int lerInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem + " (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("s") || resposta.equals("sim")) {
                return true;
            }

            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                return false;
            }

            System.out.println("Resposta invalida. Digite s para sim ou n para nao.");
        }
    }

    private static double lerLanceValido(Jogador jogador) {
        while (true) {
            double lance = lerDouble("Lance de " + jogador.getNome() + " (0 para passar): ");
            if (lance < 0) {
                System.out.println("Lance invalido. Digite 0 para passar ou um valor positivo.");
            } else if (lance == 0) {
                // 0 significa passar — deve ser aceito mesmo que o jogador tenha saldo negativo
                return 0;
            } else if (lance > jogador.getSaldo()) {
                System.out.println("Lance maior que o saldo disponivel. Saldo: " + dinheiro(jogador.getSaldo()));
            } else {
                return lance;
            }
        }
    }

    private static String dinheiro(double valor) {
        return "R$ " + String.format("%.2f", valor);
    }

    private static String formatarNumero(double valor) {
        return String.format("%.2f", valor);
    }
}
