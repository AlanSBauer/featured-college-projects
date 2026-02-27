package ex1;

public class Teacher extends Person {
    public Teacher(String name, String email) {
        super(name, email);
    }

    public void logar(){
        System.out.println("Professor logado.");
    }
}
