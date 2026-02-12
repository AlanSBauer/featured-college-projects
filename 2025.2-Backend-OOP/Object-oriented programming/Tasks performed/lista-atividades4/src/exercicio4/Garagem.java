package exercicio4;

public class Garagem {
    public static void main(String[] args) {
        Carro carro1 = new Carro("Chevrolet", "Onix", 2019, 0, 4);
        Moto moto1 = new Moto("Yamaha", "XJ6", 2015, 0, true);

        carro1.acelerar(20);
        System.out.println(carro1.getVelocidadeAtual());
        carro1.frear(21);
        System.out.println(carro1.getVelocidadeAtual());
        carro1.frear(20);
        System.out.println(carro1.getVelocidadeAtual());
        carro1.exibirInformacoes();

        System.out.println("\n");

        moto1.acelerar(40);
        System.out.println(moto1.getVelocidadeAtual());
        moto1.frear(41);
        System.out.println(moto1.getVelocidadeAtual());
        moto1.frear(40);
        System.out.println(moto1.getVelocidadeAtual());
        moto1.exibirInformacoes();
    }
}
