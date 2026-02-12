package exercicio5;

public class Funcionario {
    private String nome;
    private int matricula;
    private float salario;

    public Funcionario(String nome, int matricula, float salario) {
        this.nome = nome;
        this.matricula = matricula;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void aumentarSalario(float percentual) {
        if(percentual < 0) {
            System.out.println("Porcentagem de aumento errada!");
            return;
        }
        this.setSalario(this.getSalario() * (1 + percentual));
    }

    public void exibirDados() {
        System.out.printf("Nome: %s, Matrícula: %d, Salário: %.2f", this.getNome(), this.getMatricula(), this.getSalario());
    }
}
