package br.com.rsfot.bookstore.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewCouponResponse(
        String code,
        BigDecimal discountPercentage,
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        LocalDate expirationDate) {

    public NewCouponResponse(Coupon coupon) {
        this(coupon.getCode(), coupon.getDiscountPercentage(), coupon.getExpirationDate());
    }
}
