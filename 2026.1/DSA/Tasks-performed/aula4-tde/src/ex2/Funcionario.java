package ex2;

public class Funcionario {
    private String name;
    private double salario;

    public Funcionario(String name, double salario) {
        this.name = name;
        this.salario = salario;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "(name: " + name + " salario: " + salario + ")";
    }
}
