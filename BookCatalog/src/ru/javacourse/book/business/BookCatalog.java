package ru.javacourse.book.business;

import ru.javacourse.book.annotation.BookDaoClass;
import ru.javacourse.book.dao.BookDAO;
import ru.javacourse.book.dao.BookDAOFactory;
import ru.javacourse.book.domain.Book;
import ru.javacourse.book.exception.BookBisinessException;
import ru.javacourse.book.exception.BookDaoException;
import ru.javacourse.book.exception.BookRuntimeException;
import ru.javacourse.book.filter.BookFilter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


public class BookCatalog {

    private static BookCatalog instance;

    @BookDaoClass(name = "ru.javacourse.book.dao.BookFakeDAO")
    private BookDAO dao;

    private BookCatalog() {
        PropertyResourceBundle prb = (PropertyResourceBundle) PropertyResourceBundle.getBundle("BookCatalog");
        String daoName= prb.getString("book.dao.classname");
        if (daoName != null) {
            dao = BookDAOFactory.getDao(daoName);
            return;
        }
        try {
            Field field = BookCatalog.class.getDeclaredField("dao");
            BookDaoClass annotation = field.getAnnotation(BookDaoClass.class);
            if (annotation != null) {
                if (daoName != null || daoName.trim().isEmpty()) {
                    dao = BookDAOFactory.getDao(daoName);
                    return;
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        dao = BookDAOFactory.getDao("");
    }

    public synchronized static BookCatalog getInstance() {
        if (instance == null) {
            instance = new BookCatalog();
        }
        return instance;
    }

    public Long addBook(Book book) throws BookBisinessException {
        try {
            return dao.addBook(book);
        } catch (BookDaoException ex) {
            throw new BookBisinessException(ex);
        }
    }

    public void updateBook(Book book) {
        try {
            dao.updateBook(book);
        } catch (BookDaoException ex) {
            throw new BookRuntimeException(ex);
        }
    }

    public void deleteBook(Long bookId) {
        try {
            dao.deleteBook(bookId);
        } catch (BookDaoException e) {
            e.printStackTrace();
        }
    }

    public Book getBook(Long bookId) {
        try {
            return dao.getBook(bookId);
        } catch (BookDaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> findBooks(BookFilter filter) {
        try {
            return dao.findBooks(filter);
        } catch (BookDaoException e) {
            e.printStackTrace(System.out);
            return Collections.emptyList();
        }
    }
}
