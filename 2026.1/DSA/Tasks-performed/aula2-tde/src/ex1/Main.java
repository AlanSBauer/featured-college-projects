package ex1;

public class Main {
    public static void main(String[] args) {
        Teacher t1 = new Teacher("Juliano", "teste@gmail.com");
        Student s1 = new Student("Alan", "alan@gmail.com");
        Student s2 = new Student("Leonardo", "leonardo@gmail.com");
        Student s3 = new Student("João", "joao@gmail.com");
        SchoolClass class1 = new SchoolClass("turma1234", "Estrutura de Dados", t1);

        class1.insertStudent(s1);
        class1.insertStudent(s2);
        class1.insertStudent(s3);

        class1.listStudents();
    };
}
