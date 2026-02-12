package exercicio1;

public class Contas {
    public static void main(String[] args) {
        ContaBancaria contaBC = new ContaBancaria("Cliente1", 123, 0);
        ContaPoupanca contaPoup = new ContaPoupanca("Cliente2", 1234, 0, 2);
        ContaEspecial contaEsp = new ContaEspecial("Cliente3", 12345, 0, 1000);

        contaBC.depositar(1500);
        System.out.println(contaBC.getSaldo());
        contaBC.sacar(1600);
        System.out.println(contaBC.getSaldo());
        contaBC.sacar(800);
        System.out.println(contaBC.getSaldo());

        System.out.println();

        contaPoup.depositar(1000);
        System.out.println(contaPoup.getSaldo());
        contaPoup.calcularNovoSaldo(1.2f, 10);
        System.out.println(contaPoup.getSaldo());

        System.out.println();

        contaEsp.depositar(1000);
        System.out.println(contaEsp.getSaldo());
        contaEsp.sacar(2000);
        System.out.println(contaEsp.getSaldo());
        System.out.println("Cliente: " + contaEsp.getCliente() + " Numero da Conta: " + contaEsp.getNumConta() + " Saldo: " + contaEsp.getSaldo() + " Limite: " + contaEsp.getLimite());

    }
}
