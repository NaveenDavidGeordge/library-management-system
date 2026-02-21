# Library Management System (Java - Low Level Design)

## 📌 Overview
This project is a console-based Library Management System implemented in Java.  
It demonstrates the use of:
- Object-Oriented Programming (OOP)
- SOLID Principles
- Design Patterns
- Java Collections Framework
- Clean class structure and separation of concerns

The system allows librarians to:
- Manage books
- Manage patrons
- Checkout and return books
- Reserve books
- View available books
- Generate recommendations based on borrowing history

---

## 🧠 Key Concepts Used

### ✅ Object-Oriented Programming (OOP)

1. **Encapsulation**
    - Each class hides its data using private fields and exposes behavior through public methods.
    - Example:  
      `Book` class encapsulates title, author, isbn, and availability.

2. **Abstraction**
    - Interfaces like `RecommendationStrategy`, `Observer`, and `Subject` provide abstraction.
    - The implementation details are hidden from the main application logic.

3. **Inheritance**
    - `HistoryBasedRecommendationStrategy` implements `RecommendationStrategy`.
    - `Patron` implements `Observer`.

4. **Polymorphism**
    - `RecommendationStrategy` can have multiple implementations without changing `LibraryService`.

---

## ✅ SOLID Principles Applied

### 1. Single Responsibility Principle (SRP)
Each class has only one responsibility:

| Class | Responsibility |
|------|----------------|
| Book | Represents a book entity |
| Patron | Represents a library member |
| LibraryBranch | Manages book inventory |
| LibraryService | Business logic (checkout, return, search, patrons) |
| ReservationService | Reservation & notification logic |
| BookFactory | Creates Book objects |
| Main | User interface (menu-driven console) |

---

### 2. Open/Closed Principle (OCP)
The system is open for extension but closed for modification.
- New recommendation strategies can be added without modifying existing code.
- Example: Add `GenreBasedRecommendationStrategy` easily.

---

### 3. Liskov Substitution Principle (LSP)
- `HistoryBasedRecommendationStrategy` can replace `RecommendationStrategy` without breaking functionality.

---

### 4. Interface Segregation Principle (ISP)
Small and focused interfaces:
- `Observer` → only update method
- `RecommendationStrategy` → only recommend method

---

### 5. Dependency Inversion Principle (DIP)
- `LibraryService` depends on `RecommendationStrategy` interface, not concrete implementation.

---

## 📚 Java Collections Used

| Collection | Purpose |
|------------|---------|
| `Map<String, Book>` | Store books by ISBN |
| `Map<String, Patron>` | Store patrons by Patron ID |
| `List<Loan>` | Track active loans |
| `List<Book>` | Borrowing history |
| `Queue<Patron>` | Reservation queue |
| `Collection<Book>` | Generic book listing |

These collections ensure:
- Fast lookup
- Organized storage
- Efficient filtering and searching

---

## 🏗️ Design Patterns Used

### 1. Factory Pattern
**Class:** `BookFactory`  
Used to create `Book` objects.

Purpose:
- Centralized object creation
- Loose coupling

---

### 2. Observer Pattern
**Classes:** `Observer`, `Subject`, `NotificationService`, `Patron`

Purpose:
- Notify patrons when reserved books become available

---

### 3. Strategy Pattern
**Classes:** `RecommendationStrategy`, `HistoryBasedRecommendationStrategy`

Purpose:
- Generate recommendations based on borrowing history
- Easy to replace or add new recommendation logic

---

## 🧩 Class Structure Explanation

### 📦 model package

#### 1. Book
Represents a book entity.
Fields:
- title
- author
- isbn
- publicationYear
- available

Methods:
- getters
- setAvailable()
- toString()

---

#### 2. Patron
Represents a library user.
Fields:
- id
- name
- borrowingHistory

Implements `Observer` to receive notifications.

---

#### 3. Loan
Represents a transaction between Book and Patron.
Fields:
- book
- patron
- checkoutDate

---

#### 4. LibraryBranch
Represents a library branch.
Stores inventory using:
- `Map<String, Book>`

Provides:
- addBook()
- removeBook()
- getBook()
- getAllBooks()

---

### 📦 service package

#### 5. LibraryService
Main business logic:
- addPatron()
- removePatron() (with validation)
- checkoutBook()
- returnBook()
- searchBook()
- recommendBooks()
- getAvailableBooks()

Also enforces rule:
> A patron cannot be removed if they have borrowed books.

---

#### 6. ReservationService
Handles reservation logic:
- reserveBook()
- notifyIfAvailable()

Uses Observer Pattern for notifications.

---

### 📦 factory package

#### 7. BookFactory
Creates Book objects using Factory Pattern.

---

### 📦 strategy package

#### 8. RecommendationStrategy (Interface)
Defines recommendation behavior.

#### 9. HistoryBasedRecommendationStrategy
Recommends books based on patron borrowing history (author matching).

---

### 📦 observer package

#### 10. Observer & Subject
Interfaces for Observer Pattern.

#### 11. NotificationService
Implements Subject and notifies Patrons.

---

### 📦 util package

#### 12. LibraryLogger
Uses `java.util.logging` for logging important events.

---

### 📦 Main Class

#### 13. Main
Menu-driven console UI.

Options:
- Add Book
- Search Book
- Checkout Book with Patron ID
- Return Book
- Reserve Book
- View Available Books
- Add Patron
- Remove Patron
- View All Patrons
- Recommend Books

Handles only user input and output (no business logic).

---

## 🔄 Application Flow

1. User selects menu option
2. Main calls LibraryService / ReservationService
3. Business logic executes
4. Collections update (books, patrons, loans)
5. Output displayed

---


## 📬 Contact Information

**Author:** Naveen Kumar  
**Email:** naveenkumar592.t@gmail.com  
**Phone:** 9952201603

For any feedback, queries, or suggestions regarding this project, please feel free to contact me.