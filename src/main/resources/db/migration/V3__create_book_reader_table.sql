CREATE TABLE reader_book
(
    reader_id     BIGINT REFERENCES reader (id),
    book_id       BIGINT REFERENCES book (id),
    borrowed_date DATE NOT NULL DEFAULT now(),
    PRIMARY KEY (reader_id, book_id),
    CHECK (borrowed_date < current_timestamp)
);

INSERT INTO reader_book (reader_id, book_id, borrowed_date)
VALUES (1, 1, '2023-02-13'),
       (1, 2, '2023-02-03'),
       (1, 3, '2023-02-02'),
       (1, 4, '2023-01-31'),
       (3, 5, '2023-01-20'),
       (4, 6, '2023-02-10'),
       (5, 7, '2023-01-31'),
       (5, 8, '2023-02-01'),
       (9, 9, '2023-02-10'),
       (5, 10, '2023-02-06'),
       (7, 11, '2023-02-11'),
       (7, 12, '2023-01-31');