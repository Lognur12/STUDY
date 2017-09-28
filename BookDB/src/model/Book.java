package model;

/**
 * Created by d.zhuchkov on 31.03.2016.
 */
public class Book  {
    private String bookId;
    private String nameOfBook;
    private String author;

    public static String getTABLE() {
        return "book";
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
