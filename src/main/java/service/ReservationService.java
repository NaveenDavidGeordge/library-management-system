package service;

import model.Book;
import model.Patron;
import observer.NotificationService;

import java.util.*;

public class ReservationService {
    private Map<Book, Queue<Patron>> reservations = new HashMap<>();
    private NotificationService notificationService = new NotificationService();

    public void reserveBook(Book book, Patron patron) {
        reservations.putIfAbsent(book, new LinkedList<>());
        reservations.get(book).add(patron);
        notificationService.attach(patron);
    }

    public void notifyIfAvailable(Book book) {
        if (reservations.containsKey(book)) {
            notificationService.notifyObservers("Book available: " + book.getTitle());
        }
    }
}