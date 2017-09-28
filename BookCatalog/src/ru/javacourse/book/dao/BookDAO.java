package ru.javacourse.book.dao;

import ru.javacourse.book.domain.Book;
import ru.javacourse.book.exception.BookDaoException;
import ru.javacourse.book.filter.BookFilter;

import java.util.List;


public interface BookDAO {
    public Long addBook(Book book) throws BookDaoException;

    public void updateBook(Book book) throws BookDaoException;

    public void deleteBook(Long bookId)throws BookDaoException;

    public Book getBook(Long bookId) throws BookDaoException;

    public List<Book> findBooks(BookFilter filter) throws BookDaoException;
}
;