CREATE TABLE books
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    title             VARCHAR(255) UNIQUE NOT NULL,
    summary           TEXT                NOT NULL,
    table_of_contents TEXT(500),
    price             DECIMAL(10, 2)      NOT NULL,
    number_of_pages   INT                 NOT NULL,
    isbn              VARCHAR(255) UNIQUE NOT NULL,
    publication_date  DATE,
    author_id         BIGINT              NOT NULL,
    category_id       BIGINT              NOT NULL,

    FOREIGN KEY (author_id) REFERENCES authors (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);
