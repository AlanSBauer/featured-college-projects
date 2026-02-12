package exercicio1;

public class ContaPoupanca extends ContaBancaria {
    private int diaDeRendimento;

    public ContaPoupanca(String cliente, int numConta, float saldo, int diaDeRendimento) {
        super(cliente, numConta, saldo);
        this.diaDeRendimento = diaDeRendimento;
    }

    public int getDiaDeRendimento() {
        return diaDeRendimento;
    }

    public void setDiaDeRendimento(int diaDeRendimento) {
        this.diaDeRendimento = diaDeRendimento;
    }

    public void calcularNovoSaldo(float taxaDeRendimento, int diaAtual) {
        if(diaAtual >= this.getDiaDeRendimento()) {
            this.setSaldo(this.getSaldo() * (1 + taxaDeRendimento));
        }
    }
}
