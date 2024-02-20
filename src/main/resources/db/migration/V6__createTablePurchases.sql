CREATE TABLE purchases
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    document   VARCHAR(20)  NOT NULL,
    phone      VARCHAR(20),
    address    VARCHAR(255) NOT NULL,
    complement VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    state_id   BIGINT       NOT NULL,
    country_id BIGINT       NOT NULL,
    cep        VARCHAR(20)  NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT `fk_purchases_state` FOREIGN KEY (state_id) REFERENCES states (id),
    CONSTRAINT fk_purchases_country FOREIGN KEY (country_id) REFERENCES countries (id)
);
