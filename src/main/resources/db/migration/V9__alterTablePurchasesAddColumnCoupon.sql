ALTER TABLE purchases
    ADD COLUMN coupon_id           BIGINT,
    ADD COLUMN price_with_discount DECIMAL(10, 2),
    ADD CONSTRAINT `fk_purchases_coupons` FOREIGN KEY (coupon_id) REFERENCES coupons (id);