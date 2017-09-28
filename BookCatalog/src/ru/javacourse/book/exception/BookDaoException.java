package ru.javacourse.book.exception;


public class BookDaoException extends BookException {
    public BookDaoException() {
    }

    public BookDaoException(String message) {
        super(message);
    }

    public BookDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDaoException(Throwable cause) {
        super(cause);
    }
}
