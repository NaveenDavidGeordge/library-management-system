package model;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean available = true;

    public Book(String title, String author, String isbn, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = year;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | ISBN: " + isbn;
    }
}