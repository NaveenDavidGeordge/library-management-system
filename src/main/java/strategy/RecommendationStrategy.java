package strategy;

import model.Book;
import model.Patron;
import java.util.List;

public interface RecommendationStrategy {
    List<Book> recommend(Patron patron, List<Book> books);
}