package br.com.rsfot.bookstore.book;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewBookResponse(
        Long id,
        String title,
        String summary,
        String tableOfContents,
        BigDecimal price,
        int numberOfPages,
        String isbn,
        LocalDate publicationDate,
        String category,
        String author
) {
    public NewBookResponse(Book book) {
        this(book.getId(),
                book.getTitle(),
                book.getSummary(),
                book.getTableOfContents(),
                book.getPrice(),
                book.getNumberOfPages(),
                book.getIsbn(),
                book.getPublicationDate(),
                book.getCategory().getName(),
                book.getAuthor().getName());
    }
}
