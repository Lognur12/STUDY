package ru.javacourse.book.dao;

import ru.javacourse.book.domain.Book;
import ru.javacourse.book.exception.BookDaoException;
import ru.javacourse.book.filter.BookFilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class BookFakeDAO implements BookDAO {

    private List<Book> books = new ArrayList<Book>();

    @Override
    public Long addBook(Book book)throws BookDaoException {
        long bookId = 0L;
        try {
            bookId = Math.round(Math.random()*100000000);
            boolean found = true;
            while (found) {
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    bookId = Math.round(Math.random()*100000000);
                    break;
                }
            }
            book.setBookId(bookId);
            books.add(book);
            System.out.println("Add book for fake");
        } catch (Exception ex) {
            throw new BookDaoException(ex);
        }
        return bookId;
    }

    @Override
    public void updateBook(Book book) throws BookDaoException {
        for (Book b : books) {
            if (b.getBookId().equals(book.getBookId())) {
                b.update(book);
                break;
            }
        }
        System.out.println("Update book for fake");

    }

    @Override
    public void deleteBook(Long bookId) throws BookDaoException {
        for (Iterator<Book> it = books.iterator(); it.hasNext();) {
            Book b = it.next();
            if (b.getBookId().equals(bookId)) {
                it.remove();
                break;
            }
        }
        System.out.println("Delete book for fake");

    }

    @Override
    public Book getBook(Long bookId) throws BookDaoException {
        for (Book b : books) {
            if (b.getBookId().equals(bookId)) {
                System.out.println("Found book for GET");
               return b;
            }
        }
        return null;
    }

    @Override
    public List<Book> findBooks(BookFilter filter) throws BookDaoException {
        List<Book> result = new LinkedList<>();
        if (filter != null) {
            if (filter.getTitle() != null) {
                for (Book b : books) {
                    if (b.match(filter)) {
                        result.add(b);
                    }
                }
            }
        }
        System.out.println("findBook for filter");
        return result;
    }
}
