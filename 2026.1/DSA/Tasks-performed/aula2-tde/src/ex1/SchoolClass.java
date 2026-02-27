package ex1;

public class SchoolClass {
    private String id;
    private String name;
    private Teacher teacher;
    private Student[] students;
    private int size;

    public SchoolClass(String id, String name, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.students = new Student[50];
        this.size = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void insertStudent(Student student) {
        students[size] = student;
        size++;
    }

    public void listStudents() {
        System.out.println("Lista de alunos: ");
        for(int i = 0; i < students.length; i++) {
            if(students[i] != null) {
                System.out.println(students[i].getName());
            }
        }
    }
}





