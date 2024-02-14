package br.com.rsfot.bookstore.book;

import br.com.rsfot.bookstore.author.Author;
import br.com.rsfot.bookstore.category.Category;
import br.com.rsfot.bookstore.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewBookRequest(
        @NotBlank
        @UniqueValue(domainClass = Book.class, fieldName = "title")
        String title,
        @NotBlank
        @Size(max = 500)
        String summary,
        String tableOfContents,
        @NotNull
        @Positive
        @Min(20)
        BigDecimal price,
        @NotNull
        @Positive
        @Min(100)
        int numberOfPages,
        @NotBlank
        @UniqueValue(domainClass = Book.class, fieldName = "isbn")
        String isbn,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate publicationDate,
        @NotNull
        @Positive
        @Min(1)
        Long categoryId,
        @NotNull
        @Positive
        @Min(1)
        Long authorId) {
        public Book toModel(Category category, Author author) {
            return new Book(title, summary, tableOfContents, price, numberOfPages, isbn, publicationDate, category, author);
        }
}
