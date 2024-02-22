CREATE TABLE coupons
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    code       VARCHAR(255) UNIQUE NOT NULL,
    discount_percentage   DECIMAL(4, 2)      NOT NULL,
    expiration_date DATE                NOT NULL
);
