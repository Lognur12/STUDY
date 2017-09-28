package ru.javacourse.book.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.javacourse.book.domain.Book;
import ru.javacourse.book.exception.BookDaoException;
import ru.javacourse.book.filter.BookFilter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BookXmlDAO implements BookDAO {

    public static final String BOOKS_XML = "books.xml";
    private List<Book> books = null;

    @Override
    public Long addBook(Book book) throws BookDaoException {
        if (books == null) {
            books = loadBooks();
        }
        long bookId = 0L;
        try {
            bookId = Math.round(Math.random() * 100000000);
            boolean found = true;
            while (found) {
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    bookId = Math.round(Math.random() * 100000000);
                    break;
                }
            }
            book.setBookId(bookId);
            books.add(book);
            saveBooks();
        } catch (Exception ex) {
            throw new BookDaoException(ex);
        }
        return bookId;
    }

    @Override
    public void updateBook(Book book) throws BookDaoException {
        if (books == null) {
            loadBooks();
        }
        for (Book b : books) {
            if (b.getBookId().equals(book.getBookId())) {
                b.update(book);
                saveBooks();
                break;
            }
        }
    }

    @Override
    public void deleteBook(Long bookId) throws BookDaoException {
        if (books == null) {
            books = loadBooks();
        }
        for (Iterator<Book> it = books.iterator(); it.hasNext(); ) {
            Book b = it.next();
            if (b.getBookId().equals(bookId)) {
                it.remove();
                saveBooks();
                break;
            }
        }
    }

    @Override
    public Book getBook(Long bookId) throws BookDaoException {
        if (bookId == null) {
            books = loadBooks();
        }
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
        books = loadBooks();
        return books;
    }

    private List<Book> loadBooks() {
        List<Book> books = new ArrayList<Book>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(BOOKS_XML);
            Node root = document.getFirstChild();

            NodeList children = root.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element)child;
                    Book book = new Book();
                    book.setBookId(Long.parseLong(el.getAttribute("bookId")));
                    book.setTitle(el.getAttribute("title"));
                    book.setPrice(Double.parseDouble(el.getAttribute("price")));
                    book.setIsbn(el.getAttribute("isbn"));
                    books.add(book);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return books;
    }

    private void saveBooks() {
        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(BOOKS_XML));
            writer.writeStartDocument();
            writer.writeStartElement("books");
            for (Book b : books) {
                writer.writeEmptyElement("book");
                writer.writeAttribute("bookId", "" + b.getBookId());
                writer.writeAttribute("title", b.getTitle());
                writer.writeAttribute("price", "" + b.getPrice());
                writer.writeAttribute("isbn", b.getIsbn());
            }
            writer.writeEndElement();
            writer.flush();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
