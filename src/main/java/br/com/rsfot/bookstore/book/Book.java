package br.com.rsfot.bookstore.book;

import br.com.rsfot.bookstore.author.Author;
import br.com.rsfot.bookstore.category.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false, length = 500)
    private String summary;
    @Column(name = "table_of_contents", columnDefinition = "TEXT")
    private String tableOfContents;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "number_of_pages", nullable = false)
    private int numberOfPages;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(name = "publication_date", columnDefinition = "DATE")
    private LocalDate publicationDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Deprecated
    public Book() {
    }

    public Book(String title,
                String summary,
                String tableOfContents,
                BigDecimal price,
                int numberOfPages,
                String isbn,
                LocalDate publicationDate,
                Category category,
                Author author) {
        this.title = title;
        this.summary = summary;
        this.tableOfContents = tableOfContents;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.category = category;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getTableOfContents() {
        return tableOfContents;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }
}
