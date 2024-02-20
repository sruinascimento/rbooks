package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;
    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public PurchaseItem() {
    }

    public PurchaseItem(Book book, int quantity, BigDecimal price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }



    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
