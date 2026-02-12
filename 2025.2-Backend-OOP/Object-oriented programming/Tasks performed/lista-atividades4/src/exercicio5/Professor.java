package exercicio5;

public class Professor extends Funcionario {
    private String disciplina;

    public Professor(String nome, int matricula, float salario, String disciplina) {
        super(nome, matricula, salario);
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.printf(", Disciplina: %s.", this.getDisciplina());
    }
}
