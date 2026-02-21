package model;

import observer.Observer;
import java.util.*;

public class Patron implements Observer {

    private String id;
    private String name;
    private List<Book> borrowingHistory = new ArrayList<>();

    public Patron(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addHistory(Book book) {
        borrowingHistory.add(book);
    }

    public List<Book> getBorrowingHistory() {
        return borrowingHistory;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }

    @Override
    public String toString() {
        return "Patron{id='" + id + "', name='" + name + "'}";
    }
}