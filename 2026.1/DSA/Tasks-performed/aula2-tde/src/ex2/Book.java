package ex2;

public class Book {
    private String title;
    private String author;
    private int pages;
    private boolean available;

    public Book(String title, String author, int pages, boolean available) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void showBookDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + pages);
    }

    public void reserveBook() {
        if(!available) {
            System.out.println("Livro já reservado por outra pessoa.");
            return;
        }

        setAvailable(false);
        System.out.println("Livro reservado com sucesso!");
    }

    public void returnBook() {
        if(!available) {
            setAvailable(true);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Este livro não foi emprestado!");
        }
    }
}
