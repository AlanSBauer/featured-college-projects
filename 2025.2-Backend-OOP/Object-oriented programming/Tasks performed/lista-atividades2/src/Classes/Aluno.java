package Classes;

public class Aluno {
    private String nome;
    private float nota;

    public Aluno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getNota() {
        return this.nota;
    }
    public void setNota(float nota) {
        this.nota = nota;
    }

    public boolean aprovado() {
        if (getNota() >= 7) {
            return true;
        } else {
            return false;
        }
    }
}
