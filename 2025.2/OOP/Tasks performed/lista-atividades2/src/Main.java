import Classes.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//      Classe Pessoa
        Pessoa pessoa1 = new Pessoa("Alan", 18);

        pessoa1.apresentar();

        System.out.println("\n");

//      Classe Produto
        Produto produto1 = new Produto();
        produto1.setNome("Refrigeirante");
        produto1.setPreco(5);
        produto1.setQuantidade(10);

        System.out.printf("Valor total: R$%.2f", produto1.calcularTotal());

        System.out.println("\n");

//      Classe Aluno
        Aluno aluno1 = new Aluno("Aluno");
        aluno1.setNota(6.9f);
        aluno1.setNota(aluno1.getNota());

        if(aluno1.aprovado()) {
            System.out.println("Aprovado!");
        } else {
            System.out.println("Reprovado!");
        }

        System.out.println("O aluno foi aprovado? " + aluno1.aprovado());

        System.out.println();

//      Classe ContaBancaria
        ContaBancaria contaBancaria1 = new ContaBancaria("Titular1", 1234);
        contaBancaria1.exibirSaldo();
        contaBancaria1.depositar(1200);
        contaBancaria1.exibirSaldo();
        contaBancaria1.sacar(600);
        contaBancaria1.exibirSaldo();
        contaBancaria1.sacar(600);
        contaBancaria1.exibirSaldo();

        System.out.println("\n");

//      Classe Retangulo
        Retangulo retangulo1 = new Retangulo(5,10);
        System.out.println("Perímetro: " + retangulo1.calcularPerimetro());
        System.out.println("Área: " + retangulo1.calcularArea());

//      Classe Carro
        Carro carro1 = new Carro("Gol", 2012);
        carro1.status();
        carro1.acelerar();
        carro1.acelerar();
        carro1.acelerar();
        carro1.status();
        carro1.frear();
        carro1.status();

        System.out.println();

//      Classe Livro
        Livro livro1 = new Livro("teste", "eu");
        System.out.println(livro1.getTitulo());
        System.out.println(livro1.getAutor());
        livro1.emprestar();
        livro1.emprestar();

        System.out.println();

//      Classe Calculadora
        Calculadora calculadora = new Calculadora();
        System.out.println(calculadora.dividir(8,0));

        System.out.println();

//      Classe Elevador
        Elevador elevador1 = new Elevador(3, 6, 0, 0);
        System.out.println("Andar atual: " + elevador1.getAndarAtual());
        System.out.println("Total de andares: " + elevador1.getTotalAndares());
        System.out.println("Capacidade de pessoas: " + elevador1.getCapacidade());
        elevador1.entrar();
        elevador1.entrar();
        elevador1.entrar();
        elevador1.entrar();
        elevador1.entrar();
        elevador1.entrar();
        elevador1.entrar(); // capacidade max atingida!
        System.out.println("Pessoas no elevador: " + elevador1.getPessoas());
        elevador1.descer(); // ja esta no terreo - nao tem como descer!
        elevador1.subir();
        elevador1.subir();
        elevador1.subir();
        System.out.println("Andar atual: " + elevador1.getAndarAtual());
        elevador1.subir(); // ja esta no ultimo andar - Nao sobe mais
        elevador1.sair();
        elevador1.sair();
        System.out.println("Pessoas no elevador: " + elevador1.getPessoas());

        System.out.println();

//      Classe Agenda
        Agenda agenda = new Agenda();
        agenda.adicionarContato("Alan", "0987654321");
        agenda.adicionarContato("Teste2", "12345678910");
        agenda.adicionarContato("Teste3", "1234");
        agenda.listarContatos();
        agenda.removerContato("Alisson","1234567891"); // retorna contato nao encontrado ( falta um numero no final )
        agenda.removerContato("Teste3", "1234"); // contato removido!
        agenda.listarContatos();
    }
}