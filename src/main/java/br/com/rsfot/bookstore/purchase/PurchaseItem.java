package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import jakarta.persistence.*;

import java.math.BigDecimal;

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
    @Deprecated
    public PurchaseItem() {
    }

    public PurchaseItem(Book book, int quantity, BigDecimal price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }

    public PurchaseItem(Book book, int quantity, BigDecimal price, Purchase purchase) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.purchase = purchase;
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

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
