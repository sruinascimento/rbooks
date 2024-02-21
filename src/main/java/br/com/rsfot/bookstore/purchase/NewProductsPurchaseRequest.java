package br.com.rsfot.bookstore.purchase;

import br.com.rsfot.bookstore.book.Book;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Map<Long, Integer> bookIdToQuantity = IntStream.range(0, items.size())
                .boxed()
                .collect(Collectors.toMap(i -> items.get(i).bookId(), i -> items.get(i).quantity()));

        return books.stream()
                .map(it -> new PurchaseItem(it, bookIdToQuantity.get(it.getId()), it.getPrice()))
                .collect(Collectors.toSet());
    }
}