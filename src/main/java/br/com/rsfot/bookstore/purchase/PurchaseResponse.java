package br.com.rsfot.bookstore.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

public record PurchaseResponse(
        Long id,
        String name,
        String email,
        String document,
        String phone,
        String address,
        String complement,
        String city,
        String state,
        String country,
        String cep,
        List<PurchaseItemResponse> products,
        BigDecimal amount,
        String couponCode,
        BigDecimal priceWithDiscount,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = STRING)
        LocalDateTime createdAt) {
    public PurchaseResponse(Purchase purchase) {
        this(purchase.getId(),
                purchase.getName(),
                purchase.getEmail(),
                purchase.getDocument(),
                purchase.getPhone(),
                purchase.getAddress().getAddress(),
                purchase.getAddress().getComplement(),
                purchase.getAddress().getCity(),
                purchase.getAddress().getState().getName(),
                purchase.getAddress().getCountry().getName(),
                purchase.getAddress().getCep(),
                purchase.getProducts().stream().map(PurchaseItemResponse::new).toList(),
                purchase.getAmount(),
                purchase.getCouponCode(),
                purchase.getPriceWithDiscount(),
                purchase.getCreatedAt());
    }
}
