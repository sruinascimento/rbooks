package br.com.rsfot.bookstore.coupon;

import br.com.rsfot.bookstore.validation.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NewCouponRequest(
        @NotBlank
        @UniqueValue(domainClass = Coupon.class, fieldName = "code")
        String code,
        @NotNull
        @Range(min = 1, max = 30)
        BigDecimal discountPercentage,
        @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
        @Future
        @NotNull
        LocalDate expirationDate) {

    public Coupon toModel() {
        return new Coupon(code, discountPercentage, expirationDate);
    }
}
