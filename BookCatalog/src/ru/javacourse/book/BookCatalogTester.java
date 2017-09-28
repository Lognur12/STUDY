package ru.javacourse.book;

import ru.javacourse.book.business.BookCatalog;
import ru.javacourse.book.domain.Book;
import ru.javacourse.book.exception.BookBisinessException;
import ru.javacourse.book.filter.BookFilter;

import java.util.List;

public class BookCatalogTester {
    public static void main(String[] args) throws BookBisinessException {
        BookCatalog bc = BookCatalog.getInstance();


        Book b1 = new Book(null, "OldTitle", 99.9, "12345");
        long bookId = bc.addBook(b1);

        Book b2 = bc.getBook(bookId);

        b2.setTitle("New title");
        b2.setPrice(111.1);
        bc.updateBook(b2);

        BookFilter filter = new BookFilter();
        filter.setTitle("New");

        List<Book> books = bc.findBooks(filter);
        for (Book b : books) {
            System.out.println(b);
        }
        if (books.size() == 0) {
            System.out.println("Error");
        }
        bc.deleteBook(b2.getBookId());
    }
}
