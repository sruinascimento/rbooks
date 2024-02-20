package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import br.com.rsfot.bookstore.book.BookRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record NewProductsPurchaseRequest(
        @Positive
        @Min(1)
        @NotNull
        BigDecimal amount,
        @NotNull
        @Size(min = 1)
        List<@Valid NewPurchaseItemRequest> items) {

    public List<Long> getBookIds() {
        return items.stream().map(NewPurchaseItemRequest::bookId).toList();
    }

    public List<Integer> getQuantities() {
        return items.stream().map(NewPurchaseItemRequest::quantity).toList();
    }

    public Set<PurchaseItem> toModel(List<Book> books) {
        List<Integer> quantities = getQuantities();
        List<Long> bookIds = getBookIds();
        return books.stream()
               .map(it -> new PurchaseItem(it, quantities.get(bookIds.indexOf(it.getId())), it.getPrice()))
                .collect(Collectors.toSet());

    }
}