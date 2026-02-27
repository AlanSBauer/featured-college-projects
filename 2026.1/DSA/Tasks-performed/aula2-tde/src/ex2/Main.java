package ex2;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("Livro 1", "Autor 1", 150, true);
        Book book2 = new Book("Livro 2", "Autor 2", 130, true);
        Book book3 = new Book("Livro 3", "Autor 3", 120, true);
        Book book4 = new Book("Livro 4", "Autor 4", 110, true);
        Library library = new Library();

        library.insertBook(book1);
        library.insertBook(book2);
        library.insertBook(book3);
        library.insertBook(book4);

        System.out.println();

        book2.reserveBook();
        book1.reserveBook();
        book2.returnBook();

        System.out.println();

        book1.reserveBook();
        book3.returnBook();

        System.out.println();

        library.listBooksAvailable();
        System.out.println();
        library.listBooks();
    }
}
