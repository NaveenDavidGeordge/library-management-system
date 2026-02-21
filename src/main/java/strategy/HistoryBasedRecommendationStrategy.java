package strategy;

import model.*;
import java.util.*;

public class HistoryBasedRecommendationStrategy implements RecommendationStrategy {

    @Override
    public List<Book> recommend(Patron patron, List<Book> books) {
        Set<String> authors = new HashSet<>();
        for (Book b : patron.getBorrowingHistory()) {
            authors.add(b.getAuthor());
        }

        List<Book> recommendations = new ArrayList<>();
        for (Book book : books) {
            if (authors.contains(book.getAuthor()) && book.isAvailable()) {
                recommendations.add(book);
            }
        }
        return recommendations;
    }
}