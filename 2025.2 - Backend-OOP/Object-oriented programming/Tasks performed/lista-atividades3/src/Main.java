import classes.*;

public class Main {
    public static void main(String[] args) {
//      Classe Autor e Livro
        Autor autor1 = new Autor("Alan", "Brasil");
        Livro livro = new Livro("Sei la", 2020, autor1);
        System.out.println(livro.descricao());

        System.out.println();

//      Classe Computador e Processador
        Processador processador1 = new Processador("Intel", 4.4f, "I5 12400F");
        Computador computador = new Computador("HyperX - 16GB DDR4", processador1);
        System.out.println(computador.detalhes());

        System.out.println();

//      Classe Escola e Diretor
        Diretor diretor1 = new Diretor("Diretor 1", "2 Anos");
        Escola escola1 = new Escola("Escola 1", "avenida 123", diretor1);
        escola1.apresentarEscola();

        System.out.println("\n");

//      Classe ContaBancaria e Cliente
        Cliente cliente1 = new Cliente("Alan", "12345678900");
        ContaBancaria contaBancaria1 = new ContaBancaria(1020, 0, cliente1);
        System.out.println("Saldo: " + contaBancaria1.getSaldo());
        contaBancaria1.depositar(0);
        contaBancaria1.depositar(-100);
        contaBancaria1.depositar(1500);
        System.out.println("Saldo: " + contaBancaria1.getSaldo());
        contaBancaria1.sacar(200);
        contaBancaria1.sacar(2000);
        contaBancaria1.sacar(-100);
        System.out.println("Saldo: " + contaBancaria1.getSaldo());

        System.out.println();

//      Classe Celular e Bateria
        Bateria bateria = new Bateria(4000, 40);
        Celular celular = new Celular("Samsung", "S24", bateria);
        System.out.println(celular.getBateria().getCapacidadeMah());
        System.out.println(celular.getBateria().getPorcentagemAtual());
        celular.carregar();
        System.out.println(celular.getBateria().getPorcentagemAtual());

    }
}