package service;

import model.*;
import strategy.*;
import util.LibraryLogger;

import java.util.*;

public class LibraryService {

    private Map<String, Patron> patrons = new HashMap<>();
    private List<Loan> loans = new ArrayList<>();
    private RecommendationStrategy strategy = new HistoryBasedRecommendationStrategy();

    // Add Patron
    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
        LibraryLogger.logger.info("Patron added: " + patron.getName());
    }

    // ✅ New logic: Prevent removal if patron has active loans
    public void removePatron(String patronId) {
        Patron patron = patrons.get(patronId);

        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        boolean hasActiveLoan = loans.stream()
                .anyMatch(loan -> loan.getPatron().getId().equals(patronId)
                        && !loan.getBook().isAvailable());

        if (hasActiveLoan) {
            System.out.println("Cannot remove patron. Patron has borrowed books that are not returned.");
            return;
        }

        patrons.remove(patronId);
        LibraryLogger.logger.info("Patron removed: " + patronId);
        System.out.println("Patron removed successfully.");
    }

    public Patron getPatronById(String patronId) {
        return patrons.get(patronId);
    }

    public Collection<Patron> getAllPatrons() {
        return patrons.values();
    }

    // Checkout Book
    public void checkoutBook(Book book, Patron patron) {
        if (book == null || patron == null) {
            System.out.println("Book or Patron not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed.");
            return;
        }

        book.setAvailable(false);
        loans.add(new Loan(book, patron));
        patron.addHistory(book);

        LibraryLogger.logger.info("Book checked out: " + book + " by " + patron.getName());
        System.out.println("Book checked out successfully.");
    }

    // Return Book
    public void returnBook(Book book) {
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        book.setAvailable(true);

        // Remove active loan entry
        loans.removeIf(loan -> loan.getBook().equals(book));

        LibraryLogger.logger.info("Book returned: " + book);
        System.out.println("Book returned successfully.");
    }

    public List<Book> searchByTitle(String title, Collection<Book> books) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> recommendBooks(Patron patron, Collection<Book> books) {
        return strategy.recommend(patron, new ArrayList<>(books));
    }

    public List<Book> getAvailableBooks(Collection<Book> books) {
        List<Book> availableBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}