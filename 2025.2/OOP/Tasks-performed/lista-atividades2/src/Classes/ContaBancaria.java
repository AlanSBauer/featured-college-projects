package Classes;

public class ContaBancaria {
    private int numero;
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, int numero) {
        this.titular = titular;
        this.numero = numero;
        setSaldo(0);
    }

    public int getNumero() {
        return this.numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return this.titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return this.saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if(valor > 0) {
            this.saldo += valor;
            System.out.println("\nDepósito concluído!");
        } else {
            System.out.println("\nDepósito inválido!");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("\nSaque concluído!");
        } else {
            System.out.println("\nSaque inválido!");
        }
    }

    public void exibirSaldo() {
        System.out.printf("Saldo atual: %.2f", getSaldo());
    }
}
