CREATE TABLE states
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(255) UNIQUE NOT NULL,
    country_id BIGINT              NOT NULL,
    FOREIGN KEY (country_id) REFERENCES countries (id)
);
