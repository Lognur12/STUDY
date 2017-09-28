package view.ui;

import control.Facade;
import model.Author;
import model.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by d.zhuchkov on 13.04.2016.
 */

public class App {

    public static void main(String[] args) {
        BaseFrame baseFrame = new BaseFrame("Электронная библиотека.", 200, 300);
        baseFrame.setVisible(true);
        JButton buttonAllAuthor = new JButton("Все авторы");
        buttonAllAuthor.setVisible(true);
        buttonAllAuthor.setLocation(12, 12);
        buttonAllAuthor.setSize(160, 30);
        buttonAllAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectFrame<Author> selectFrame =
                        new SelectFrame<Author>("Авторы", 500, 400, new String[]{"ID", "Фамилия", "Имя", "Отчество"}, new SelectFrame.ObjectArrayInterface<Author>() {
                            @Override
                            public Object[] getObjects(Author author) {
                                return new Object[]{author.getAuthorId(), author.getFirstName(), author.getSecondName(), author.getMiddleName()};
                            }
                        }
                        );
                selectFrame.showData(Facade.getInstance().getAuthorDAO().select());
            }
        });
        baseFrame.getContentPane().add(buttonAllAuthor);
        baseFrame.getContentPane().add(new JLabel());

        JButton buttonInsertAuthor = new JButton("Добавить автора");
        buttonInsertAuthor.setVisible(true);
        buttonInsertAuthor.setLocation(12, 52);
        buttonInsertAuthor.setSize(160, 30);
        buttonInsertAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertFrame.InsertInterface<Author> insertInterface = new InsertFrame.InsertInterface<Author>() {
                    @Override
                    public boolean insert(Author author) {
                        return Facade.getInstance().getAuthorDAO().insert(author);
                    }
                    @Override
                    public Author create(Object[] objects) {
                        Author author = new Author();
                        author.setFirstName((String) objects[0]);
                        author.setSecondName((String) objects[1]);
                        author.setMiddleName((String) objects[2]);
                        return author;
                    }
                };
                Collection<InsertFrame.TextField> collection = new ArrayList<InsertFrame.TextField>();
                collection.add(new InsertFrame.TextField() {
                    @Override
                    public String getName() {
                        return "Фамилия";
                    }
                    @Override
                    public Object getObject(String s) throws IllegalArgumentException {
                        if (s == null || s.length() == 0)
                            throw new IllegalArgumentException("Фамилия пустая");
                        return s;
                    }
                });
                collection.add(new InsertFrame.TextField() {
                    @Override
                    public String getName() {
                        return "Имя";
                    }
                    @Override
                    public Object getObject(String s) throws IllegalArgumentException {
                        if (s == null || s.length() == 0)
                            throw new IllegalArgumentException("Фамилия имя");
                        return s;
                    }
                });
                collection.add(new InsertFrame.TextField() {
                    @Override
                    public String getName() {
                        return "Отчество";
                    }
                    @Override
                    public Object getObject(String s) throws IllegalArgumentException {
                        if (s == null || s.length() == 0)
                            throw new IllegalArgumentException("Отчество пустая");
                        return s;
                    }
                });
                InsertFrame<Author> insertFrame = new InsertFrame<Author>("Добавление автора", 160, 220, insertInterface, collection, "Добавить автора");
                insertFrame.setVisible(true);
            }
        });
        baseFrame.getContentPane().add(buttonInsertAuthor);
        baseFrame.getContentPane().add(new JLabel());





        JButton buttonDeleteAuthor = new JButton("Удалить автора");
        buttonDeleteAuthor.setVisible(true);
        buttonDeleteAuthor.setLocation(12, 172);
        buttonDeleteAuthor.setSize(160, 30);
        buttonDeleteAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteFrame.DeleteInterface<Author> deleteInterface = new DeleteFrame.DeleteInterface<Author>() {
                    @Override
                    public boolean delete(Author author) {
                        return Facade.getInstance().getAuthorDAO().delete(author);
                    }
                    @Override
                    public Author create(Object[] objects) {
                        Author author = new Author();
                        author.setAuthorId((String) objects[0]);
                        return author;
                    }
                };
                Collection<DeleteFrame.TextField> collection = new ArrayList<>();
                collection.add(new DeleteFrame.TextField() {
                    @Override
                    public String getName() {
                        return "ID Автора";
                    }
                    @Override
                    public Object getObject(String s) throws IllegalArgumentException {
                        if (s == null || s.length() == 0)
                            throw new IllegalArgumentException("ID отсутствует");
                        return s;
                    }
                });
                DeleteFrame<Author> deleteFrame = new DeleteFrame<>("Удаление автора", 150, 130, deleteInterface, collection, "Удалить автора");
                deleteFrame.setVisible(true);
            }
        });
        baseFrame.getContentPane().add(buttonDeleteAuthor);
        baseFrame.getContentPane().add(new JLabel());


        JButton buttonDeleteBook = new JButton("Удалить книгу");
        buttonDeleteBook.setVisible(true);
        buttonDeleteBook.setLocation(12, 212);
        buttonDeleteBook.setSize(160, 30);
        buttonDeleteBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteFrame.DeleteInterface<Book> deleteInterface = new DeleteFrame.DeleteInterface<Book>() {
                    @Override
                    public boolean delete(Book book) {
                        return Facade.getInstance().getBookDAO().delete(book);
                    }
                    @Override
                    public Book create(Object[] objects) {
                        Book book = new Book();
                        book.setBookId((String) objects[0]);
                        return book;
                    }
                };
                Collection<DeleteFrame.TextField> collection = new ArrayList<>();
                collection.add(new DeleteFrame.TextField() {
                    @Override
                    public String getName() {
                        return "ID книги";
                    }
                    @Override
                    public Object getObject(String s) throws IllegalArgumentException {
                        if (s == null || s.length() == 0)
                            throw new IllegalArgumentException("ID отсутствует");
                        return s;
                    }
                });
                DeleteFrame<Book> deleteFrame = new DeleteFrame<>("Удаление книги", 150, 130, deleteInterface, collection, "Удалить книгу");
                deleteFrame.setVisible(true);
            }
        });
        baseFrame.getContentPane().add(buttonDeleteBook);
        baseFrame.getContentPane().add(new JLabel());


        JButton buttonAllBook = new JButton("Все книги");
        buttonAllBook.setVisible(true);
        buttonAllBook.setLocation(12, 92);
        buttonAllBook.setSize(160, 30);
        buttonAllBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectFrame<Book> selectFrame =
                        new SelectFrame<>("Авторы", 600, 400, new String[]{"ID", "Название книги", "Автор"}, new SelectFrame.ObjectArrayInterface<Book>() {
                            @Override
                            public Object[] getObjects(Book book) {
                                return new Object[]{book.getBookId(), book.getNameOfBook(), book.getAuthor()};
                            }
                        }
                        );
                selectFrame.showData(Facade.getInstance().getBookDAO().select());
            }
        });
        baseFrame.getContentPane().add(buttonAllBook);
        baseFrame.getContentPane().add(new JLabel());


        JButton buttonInsertBook = new JButton("Добавить книгу");
        buttonInsertBook.setVisible(true);
        buttonInsertBook.setLocation(12, 132);
        buttonInsertBook.setSize(160, 30);
        buttonInsertBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertFrame.InsertInterface<Book> insertInterface = new InsertFrame.InsertInterface<Book>() {
                    @Override
                    public boolean insert(Book book) {
                        return Facade.getInstance().getBookDAO().insert(book);
                    }
                    @Override
                    public Book create(Object[] objects) {
                        Book book = new Book();
                        book.setNameOfBook((String) objects[0]);
                        book.setAuthor((String) objects[1]);
                        return book;
                    }
                };
                Collection<InsertFrame.TextField> collection = new ArrayList<InsertFrame.TextField>();
                collection.add(new InsertFrame.TextField() {
                    @Override
                    public String getName() {
                        return "Название книги";
                    }
                    @Override
                    public Object getObject(String s) throws IllegalArgumentException {
                        if (s == null || s.length() == 0)
                            throw new IllegalArgumentException("Название отсутствует");
                        return s;
                    }
                });
                collection.add(new InsertFrame.TextField() {
                    @Override
                    public String getName() {
                        return "Имя автора книги";
                    }
                    @Override
                    public Object getObject(String s) throws IllegalArgumentException {
                        if (s == null || s.length() == 0)
                            throw new IllegalArgumentException("Автор отсутствует");
                        return s;
                    }
                });
                InsertFrame<Book> insertFrame = new InsertFrame<>("Добавление книги", 170, 180, insertInterface, collection, "Добавить книгу");
                insertFrame.setVisible(true);
            }
        });
        baseFrame.getContentPane().add(buttonInsertBook);
        baseFrame.getContentPane().add(new JLabel());
        baseFrame.setVisible(true);
    }

}
