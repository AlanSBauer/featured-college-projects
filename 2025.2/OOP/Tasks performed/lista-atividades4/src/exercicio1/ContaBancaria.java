package exercicio1;

public class ContaBancaria {
    private String cliente;
    private int numConta;
    private float saldo;

    public ContaBancaria(String cliente, int numConta, float saldo) {
        this.cliente = cliente;
        this.numConta = numConta;
        this.saldo = saldo;
    }

    public ContaBancaria() {
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void sacar(float valorSaque) {
        if(valorSaque > this.getSaldo()) {
            System.out.println("Saldo insuficiente!");
            return;
        }
        this.setSaldo(this.getSaldo() - valorSaque);
    }

    public void depositar(float valorDeposito) {
        if(valorDeposito > 0) {
            this.setSaldo(this.getSaldo() + valorDeposito);
        }
    }
}
