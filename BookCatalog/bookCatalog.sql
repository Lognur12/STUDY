CREATE TABLE BOOK (
  book_id SERIAL,
  title VARCHAR (255) NOT NULL,
  price DOUBLE PRECISION NOT NULL,
  isbn VARCHAR (255) NOT NULL,
PRIMARY KEY (book_id)
);