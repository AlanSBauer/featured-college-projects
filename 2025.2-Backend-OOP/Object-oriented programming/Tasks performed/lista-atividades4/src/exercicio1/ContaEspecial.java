package exercicio1;

public class ContaEspecial extends ContaBancaria {
    private float limite;

    public ContaEspecial(String cliente, int numConta, float saldo, float limite) {
        super(cliente, numConta, saldo);
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public void sacar(float valorSaque) {
        float limiteNegativo = this.getLimite() * (-1);

        if((this.getSaldo() - valorSaque) < limiteNegativo) {
            System.out.println("Saldo insuficiente - Ultrapassando limite!");
            return;
        }

        this.setSaldo(this.getSaldo() - valorSaque);
    }
}
