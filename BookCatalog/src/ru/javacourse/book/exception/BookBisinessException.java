package ru.javacourse.book.exception;


public class BookBisinessException extends BookException {
    public BookBisinessException() {
    }

    public BookBisinessException(String message) {
        super(message);
    }

    public BookBisinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookBisinessException(Throwable cause) {
        super(cause);
    }
}
