package Classes;

public class Elevador {
    private int andarAtual;
    private int totalAndares;
    private int capacidade;
    private int pessoas;

    public Elevador(int totalAndares, int capacidade, int pessoas, int andarAtual) {
        this.totalAndares = totalAndares;
        this.capacidade = capacidade;
        this.pessoas = pessoas;
        this.andarAtual = andarAtual;
    }

    public int getAndarAtual() {
        return this.andarAtual;
    }
    public void setAndarAtual(int andarAtual) {
        this.andarAtual = andarAtual;
    }

    public int getTotalAndares() {
        return this.totalAndares;
    }
    public void setTotalAndares(int totalAndares) {
        this.totalAndares = totalAndares;
    }

    public int getCapacidade() {
        return this.capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getPessoas() {
        return this.pessoas;
    }
    public void setPessoas(int pessoas) {
        this.pessoas = pessoas;
    }

    public void entrar() {
        if(getPessoas() < getCapacidade()) {
            this.pessoas += 1;
        } else {
            System.out.println("Capacidade máxima do elevador atingida!");
        }
    }

    public void sair() {
        if(getPessoas() > 0) {
            this.pessoas -= 1;
        } else {
            System.out.println("Não há pessoas no elevador!");
        }
    }

    public void subir() {
        if(getAndarAtual() == getTotalAndares()) {
            System.out.println("Você ja está no ultimo andar!");
        } else {
            this.andarAtual += 1;
        }
    }

    public void descer() {
        if(getAndarAtual() > 0) {
            this.andarAtual -= 1;
        } else {
            System.out.println("Você ja está no terreo!");
        }
    }
}