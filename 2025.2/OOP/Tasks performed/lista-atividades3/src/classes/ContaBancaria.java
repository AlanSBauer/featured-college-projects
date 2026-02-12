package classes;

public class ContaBancaria {
    private int numeroConta;
    private double saldo;
    private Cliente cliente;

    public ContaBancaria(int numeroConta, double saldo, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void sacar(double valor) {
        if(valor > this.getSaldo()) {
            System.out.println("Saldo insuficiente!");
        } else if (valor <= 0){
            System.out.println("Valor incorreto!");
        } else {
            this.setSaldo(this.getSaldo() - valor);
        }
    }

    public void depositar(double valor) {
        if(valor > 0) {
            this.setSaldo(this.getSaldo() + valor);
        } else {
            System.out.println("Valor inválido!");
        }
    }
}
