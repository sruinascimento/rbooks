package br.com.rsfot.bookstore.book;

import br.com.rsfot.bookstore.author.AuthorDetailsResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record BookDetailsResponse(
        String title,
        String summary,
        String tableOfContents,
        AuthorDetailsResponse author,
        String isbn,
        int numberOfPages,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate publicationDate) {
    public static BookDetailsResponse from(Book book) {
        return new BookDetailsResponse(book.getTitle(),
                book.getSummary(),
                book.getTableOfContents(),
                AuthorDetailsResponse.from(book.getAuthor()),
                book.getIsbn(),
                book.getNumberOfPages(),
                book.getPublicationDate());
    }
}
