package ru.javacourse.book.dao;

import ru.javacourse.book.domain.Book;
import ru.javacourse.book.exception.BookDaoException;
import ru.javacourse.book.filter.BookFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BookCsvDAO extends BookFileDAO {

    public static final String BOOKS_CSV = "books.xml";

    protected void saveBooks() {
        if (books != null)
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(BOOKS_CSV), "cp1251"));
                try {
                    for (Book book : books) {
                        String csv = book.getBookId() + ";" + book.getIsbn() + ";" + book.getTitle() + ";" + book.getPrice();
                        out.write(csv);
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    protected void loadBooks() {
        books = new ArrayList<Book>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(BOOKS_CSV), "cp1251"));
            try {
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] ba = line.split(";");
                    Book book = new Book(Long.parseLong(ba[0]),  ba[1], Double.parseDouble(ba[3]), ba[2]);
                    books.add(book);
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
