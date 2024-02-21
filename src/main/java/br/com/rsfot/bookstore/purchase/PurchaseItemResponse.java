package br.com.rsfot.bookstore.purchase;

import java.math.BigDecimal;

public record PurchaseItemResponse(
        String title,
        int quantity,
        BigDecimal price
) {
    public PurchaseItemResponse(PurchaseItem purchaseItem) {
        this(purchaseItem.getBook().getTitle(),
                purchaseItem.getQuantity(),
                purchaseItem.getPrice());
    }
}
