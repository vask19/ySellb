CREATE TABLE book
(
    id                  INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    image               VARCHAR(255),
    description         TEXT,
    name                VARCHAR(255),
    author              VARCHAR(255),
    publishing_house    VARCHAR(255),
    language            VARCHAR(255),
    year_of_publication VARCHAR(255),
    number_of_pages     VARCHAR(255),
    users_id            INTEGER,
    CONSTRAINT pk_book PRIMARY KEY (id)
);

CREATE TABLE bucket
(
    id       INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    users_id INTEGER,
    CONSTRAINT pk_bucket PRIMARY KEY (id)
);

CREATE TABLE buckets_books
(
    book_id   INTEGER NOT NULL,
    bucket_id INTEGER NOT NULL
);

CREATE TABLE users
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name      VARCHAR(255),
    username  VARCHAR(255),
    password  VARCHAR(255),
    is_active BOOLEAN                                  NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE book
    ADD CONSTRAINT FK_BOOK_ON_USERS FOREIGN KEY (users_id) REFERENCES users (id);

ALTER TABLE bucket
    ADD CONSTRAINT FK_BUCKET_ON_USERS FOREIGN KEY (users_id) REFERENCES users (id);

ALTER TABLE buckets_books
    ADD CONSTRAINT fk_bucboo_on_book FOREIGN KEY (book_id) REFERENCES book (id);

ALTER TABLE buckets_books
    ADD CONSTRAINT fk_bucboo_on_bucket FOREIGN KEY (bucket_id) REFERENCES bucket (id);
