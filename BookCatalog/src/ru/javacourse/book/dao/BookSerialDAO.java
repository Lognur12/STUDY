package ru.javacourse.book.dao;

import ru.javacourse.book.domain.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class BookSerialDAO extends BookFileDAO {

    private static final String BOOKS_BIN = "books.bin";

    @Override
    protected void loadBooks() {
        books = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(BOOKS_BIN);
            ObjectInputStream ois = new ObjectInputStream(fis);
            books = (List<Book>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void saveBooks() {
        try {
            FileOutputStream fos = new FileOutputStream(BOOKS_BIN);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
