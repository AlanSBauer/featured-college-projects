package exercicio2;

public class TesteAnimais {
    public static void main(String[] args) {
        Animal camelo = new Animal("Camelo", 150, 4, "Amarelo", "Terra", 2.0f);
        Peixe tubarao = new Peixe("Tubarão", 300, 0, "Cinzento", "Mar", 1.5f, "Barbatanas e cauda");
        Mamifero ursoDoCanada = new Mamifero("Urso-do-Canadá", 180, 4, "Vermelho", "Terra", 0.5f, "Mel");

        camelo.dados();
        System.out.println();
        tubarao.dados();
        System.out.println();
        ursoDoCanada.dados();
    }
}