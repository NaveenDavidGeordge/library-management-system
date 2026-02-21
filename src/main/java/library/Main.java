package library;

import model.*;
import factory.BookFactory;
import service.*;

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static LibraryBranch branch = new LibraryBranch("Central Library");
    static LibraryService libraryService = new LibraryService();
    static ReservationService reservationService = new ReservationService();

    public static void main(String[] args) {

        seedData();

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by Title");
            System.out.println("3. Checkout Book");
            System.out.println("4. Return Book");
            System.out.println("5. Reserve Book");
            System.out.println("6. Recommend Books");
            System.out.println("7. View All Books");
            System.out.println("8. Add Patron");
            System.out.println("9. Remove Patron");
            System.out.println("10. View All Patrons");
            System.out.println("11. View Available Books");
            System.out.println("0. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> checkoutBook();
                case 4 -> returnBook();
                case 5 -> reserveBook();
                case 6 -> recommendBooks();
                case 7 -> viewAllBooks();
                case 8 -> addPatron();
                case 9 -> removePatron();
                case 10 -> viewAllPatrons();
                case 11->viewAvailableBooks();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }

    static void seedData() {
        branch.addBook(BookFactory.createBook("Clean Code", "Robert Martin", "111", 2008));
        branch.addBook(BookFactory.createBook("Effective Java", "Joshua Bloch", "222", 2018));
        branch.addBook(BookFactory.createBook("Clean Architecture", "Robert Martin", "333", 2017));

        libraryService.addPatron(new Patron("P1", "John"));
        libraryService.addPatron(new Patron("P2", "Mary"));
    }

    static void addBook() {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Year: ");
        int year = sc.nextInt();

        branch.addBook(BookFactory.createBook(title, author, isbn, year));
        System.out.println("Book added successfully");
    }

    static void searchBook() {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.println(libraryService.searchByTitle(title, branch.getAllBooks()));
    }

    static void checkoutBook() {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        Book book = branch.getBook(isbn);

        System.out.print("Enter Patron ID: ");
        String patronId = sc.nextLine();
        Patron patron = libraryService.getPatronById(patronId);

        libraryService.checkoutBook(book, patron);
    }

    static void returnBook() {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        Book book = branch.getBook(isbn);
        libraryService.returnBook(book);
        reservationService.notifyIfAvailable(book);
    }

    static void reserveBook() {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        Book book = branch.getBook(isbn);

        System.out.print("Enter Patron ID: ");
        String patronId = sc.nextLine();
        Patron patron = libraryService.getPatronById(patronId);

        if (patron != null) {
            reservationService.reserveBook(book, patron);
            System.out.println("Book reserved successfully.");
        } else {
            System.out.println("Patron not found.");
        }
    }

    static void recommendBooks() {
        System.out.print("Enter Patron ID: ");
        String patronId = sc.nextLine();
        Patron patron = libraryService.getPatronById(patronId);

        if (patron != null) {
            System.out.println(libraryService.recommendBooks(patron, branch.getAllBooks()));
        } else {
            System.out.println("Patron not found.");
        }
    }

    static void viewAllBooks() {
        branch.getAllBooks().forEach(System.out::println);
    }

    static void addPatron() {
        System.out.print("Enter Patron ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Patron Name: ");
        String name = sc.nextLine();

        libraryService.addPatron(new Patron(id, name));
        System.out.println("Patron added successfully.");
    }

    static void removePatron() {
        System.out.print("Enter Patron ID: ");
        String id = sc.nextLine();
        libraryService.removePatron(id);
    }

    static void viewAllPatrons() {
        libraryService.getAllPatrons().forEach(System.out::println);
    }


    static void viewAvailableBooks() {
        List<Book> availableBooks = libraryService.getAvailableBooks(branch.getAllBooks());

        if (availableBooks.isEmpty()) {
            System.out.println("No books are currently available.");
        } else {
            System.out.println("Available Books:");
            availableBooks.forEach(System.out::println);
        }
    }
}