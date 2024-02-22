package br.com.rsfot.bookstore.coupon;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false, name = "discount_percentage")
    private BigDecimal discountPercentage;
    @Column(nullable = false, name = "expiration_date")
    @Future
    private LocalDate expirationDate;

    @Deprecated
    public Coupon() {
    }

    public Coupon(String code, BigDecimal discountPercentage, LocalDate expirationDate) {
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isValid() {
        return LocalDate.now().isBefore(expirationDate);
    }
}
