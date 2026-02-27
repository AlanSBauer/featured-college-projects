package ex1;

public class Student extends Person {
    public Student(String name, String email) {
        super(name, email);
    }

    public void logar(){
        System.out.println("Aluno logado.");
    }
}
