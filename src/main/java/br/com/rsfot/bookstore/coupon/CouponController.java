package br.com.rsfot.bookstore.coupon;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CouponController {
    private final CouponRepository couponRepository;

    public CouponController(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @PostMapping("/coupons")
    public ResponseEntity<NewCouponResponse> create(@Valid @RequestBody NewCouponRequest newCouponRequest) {
        Coupon coupon = newCouponRequest.toModel();
        couponRepository.save(coupon);

        return ResponseEntity.ok(new NewCouponResponse(coupon));
    }
}
