package exercicio5;

public class Administrador extends Funcionario {
    private String setor;

    public Administrador(String nome, int matricula, float salario, String setor) {
        super(nome, matricula, salario);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.printf("Setor: %s.", this.getSetor());
    }
}
