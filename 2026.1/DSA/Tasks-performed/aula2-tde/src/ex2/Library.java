package ex2;

import java.util.List;
import java.util.ArrayList;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void insertBook(Book book) {
        books.add(book);
        System.out.println("Livro inserido com sucesso!");

    }

    public void listBooks() {
        System.out.println("Lista completa de livros: ");
        for(Book book : books) {
            System.out.printf("Nome do Livro: %s - Autor: %s\n", book.getTitle(), book.getAuthor());
        }
    }

    public void listBooksAvailable() {
        System.out.println("Lista de livros disponíveis: ");
        for(Book book : books) {
            if(book.isAvailable()) {
                System.out.printf("Nome do Livro: %s - Autor: %s\n", book.getTitle(), book.getAuthor());
            }
        }
    }
}
