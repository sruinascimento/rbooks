package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import br.com.rsfot.bookstore.validation.ExistsId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NewPurchaseItemRequest(
        @NotNull
        @ExistsId(domainClass = Book.class, fieldName = "id")
        Long bookId,
        @NotNull
        @Min(1)
        int quantity) {

    public PurchaseItem toModel(Book book) {
        return new PurchaseItem(book, quantity, book.getPrice());
    }
}
