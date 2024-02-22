package br.com.rsfot.bookstore.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
    Coupon findByCode(String code);
}
