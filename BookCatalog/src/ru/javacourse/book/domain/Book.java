package ru.javacourse.book.domain;

import ru.javacourse.book.filter.BookFilter;

import java.io.Serializable;


public class Book implements Serializable{
    private Long bookId;
    private String title;
    private Double price;
    private String isbn;

    public Book() {
    }


    public Book(Long bookId, String title, Double price, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void update(Book book) {
        setIsbn(book.getIsbn());
        setPrice(book.getPrice());
        setTitle(book.getTitle());
    }

    public boolean match(BookFilter filter) {
        if (filter != null) {
            if (filter.getPriceMin() != null)
                if (getPrice() < filter.getPriceMin()) {
                    return false;
                }
            if (filter.getPriceMax() != null) {
                if (getPrice() > filter.getPriceMax()) {
                    return false;
                }
            }
            if (filter.getTitle() != null) {
                if (!getTitle().contains(filter.getTitle())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title +
                ", price=" + price +
                ", isbn='" + isbn +
                '}';
    }
}
