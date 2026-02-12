package exercicio5;

import java.util.ArrayList;
import java.util.Scanner;

public class Escola {
    public static void main(String[] args) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        funcionarios.add(new Professor("Professor1", 123, 1200, "Matemática"));
        funcionarios.add(new Professor("Professor2", 10, 1350, "Português"));
        funcionarios.add(new Administrador("Adm1", 1234, 2000, "Sla"));
        funcionarios.add(new Administrador("Adm2", 12345, 2100, "Sla"));

        String opcao = "";
        while (!opcao.equals("5")) {
            System.out.println("Opções:");
            System.out.println("1 - Cadastrar um Professor \n2 - Cadastrar um administrador \n3 - Aplicar aumento de salário para um funcionário \n4 - Listar todos funcionários \n5 - Sair");
            opcao = input.nextLine();

            if(opcao.equals("1")) {
                System.out.println("Qual o nome do Professor? ");
                String nomeProfessor = input.nextLine();

                System.out.println("Matricula do Professor: ");
                int matriculaProfessor = input.nextInt();
                input.nextLine();

                System.out.println("Salário do Professor: ");
                float salarioProfessor = input.nextFloat();
                input.nextLine();

                System.out.println("Disciplina do Professor: ");
                String disciplinaProfessor = input.nextLine();

                funcionarios.add(new Professor(nomeProfessor, matriculaProfessor, salarioProfessor, disciplinaProfessor));
                System.out.println("Professor adicionado!");
            } else if (opcao.equals("2")) {
                System.out.println("Qual o nome do Administrador? ");
                String nomeAdm = input.nextLine();

                System.out.println("Matricula do Administrador: ");
                int matriculaAdm = input.nextInt();
                input.nextLine();

                System.out.println("Salário do Administrador: ");
                float salarioAdm = input.nextFloat();
                input.nextLine();

                System.out.println("Setor do Administrador: ");
                String setorAdm = input.nextLine();

                funcionarios.add(new Administrador(nomeAdm, matriculaAdm, salarioAdm, setorAdm));
                System.out.println("Administrador adicionado!");
            } else if(opcao.equals("3")) {
                for(Funcionario f : funcionarios) {
                    System.out.println("Digite o nome do funcionário para dar aumento: ");
                    String aumentoFuncionario = input.nextLine();

                    System.out.println("Digite a taxa de aumento do salário (em %): ");
                    float taxaAumento = input.nextFloat();
                    input.nextLine();
                    float taxaCorreta = taxaAumento / 100;

                    boolean encontrado = false;
                    if(f.getNome().equalsIgnoreCase(aumentoFuncionario)) {
                        f.aumentarSalario(taxaCorreta);
                        System.out.printf("Novo salário do %s é: %.2f.", f.getNome(), f.getSalario());
                        System.out.println();
                        encontrado = true;
                        break;
                    }

                    if(!encontrado) {
                        System.out.println("Funcionário não encontrado!");
                    }
                }
            } else if(opcao.equals("4")) {
                if(funcionarios.isEmpty()) {
                    System.out.println("Não há funcionários!");
                    System.out.println();
                    continue;
                }

                System.out.println("==== Lista de Funcionários ====");
                System.out.println("--- Professores ---");
                for(Funcionario f : funcionarios) {
                    if(f instanceof Professor) {
                        f.exibirDados();
                        System.out.println();
                    }
                }
                System.out.println();
                System.out.println("--- Administradores ---");
                for(Funcionario f : funcionarios) {
                    if(f instanceof Administrador) {
                        f.exibirDados();
                        System.out.println();
                    }
                }
                System.out.println();
            } else if(opcao.equals("5")) {
                System.out.println("Sistema encerrado!");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}
