package model;

import java.util.*;

public class LibraryBranch {
    private String name;
    private Map<String, Book> inventory = new HashMap<>();

    public LibraryBranch(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        inventory.put(book.getIsbn(), book);
    }

    public void removeBook(String isbn) {
        inventory.remove(isbn);
    }

    public Book getBook(String isbn) {
        return inventory.get(isbn);
    }

    public Collection<Book> getAllBooks() {
        return inventory.values();
    }

    public String getName() {
        return name;
    }
}