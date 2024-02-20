CREATE TABLE purchase_items
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    purchase_id BIGINT         NOT NULL,
    book_id     BIGINT         NOT NULL,
    quantity    INT            NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,

    CONSTRAINT `fk_purchase_items_purchases` FOREIGN KEY (purchase_id) REFERENCES purchases (id),
    CONSTRAINT `fk_purchase_items_books` FOREIGN KEY (book_id) REFERENCES books (id)
);
