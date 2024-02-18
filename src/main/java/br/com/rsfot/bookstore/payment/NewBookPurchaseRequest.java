package br.com.rsfot.bookstore.payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record NewBookPurchaseRequest(
        @Positive
        @Min(1)
        @NotNull BigDecimal amount,
        @NotNull
        @Valid
        List<NewBookPurchaseItemsRequest> items) {

        public List<Long> getBookIds() {
            return items.stream().map(NewBookPurchaseItemsRequest::bookId).toList();
        }

        public List<Integer> getQuantities() {
            return items.stream().map(NewBookPurchaseItemsRequest::quantity).toList();
        }
}
