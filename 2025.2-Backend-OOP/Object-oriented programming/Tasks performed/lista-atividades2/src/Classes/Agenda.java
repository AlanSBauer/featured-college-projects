package Classes;
import java.util.ArrayList;

public class Agenda {

    ArrayList<Contato> contatos = new ArrayList<>();

    public void adicionarContato(String nome, String telefone) {
        contatos.add(new Contato(nome, telefone));
    }

    public void removerContato(String nome, String telefone) {
        boolean removerContato = contatos.removeIf(c -> c.getNome().equals(nome) && c.getTelefone().equals(telefone));

        if(removerContato) {
            System.out.println("Contato removido!");
        } else {
            System.out.println("Contato não encontrado!");
        }
    }

    public void listarContatos() {
        if(contatos.isEmpty()) {
            System.out.println("Lista de Contatos está Vazia!");
        } else {
            int contador = 1;
            System.out.println("--- Contatos salvos: ---");
            for(Contato i : contatos) {
                System.out.printf("%d - Nome: %s - Telefone: %s\n", contador, i.getNome(), i.getTelefone());
                contador++;
            }
        }
    }
}