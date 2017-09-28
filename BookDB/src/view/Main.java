package view;

import model.Author;
import model.Book;
import control.Facade;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by d.zhuchkov on 29.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вас приветствует электронная библиотека. Для выхода из программы введите exit.");

        while (true) {
            System.out.println("Выберете опцию:");
            System.out.println("1)добавить автора");
            System.out.println("2)обновить автора");
            System.out.println("3)удалить автора");
            System.out.println("4)посмотреть всех авторов");
            System.out.println("5)добавить книгу");
            System.out.println("6)обновить книгу");
            System.out.println("7)удалить книгу");
            System.out.println("8)посмотреть все книги");
            //System.out.println("9)просмотреть все книги и соответствующих авторов");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    insertAuthor(scanner);
                    break;
                case 2:
                    updateAuthor(scanner);
                    break;
                case 3:
                    deleteAuthor(scanner);
                    break;
                case 4:
                    selectAuthors();
                    break;
                case 5:
                    insertBook(scanner);
                    break;
                case 6:
                    updateBook(scanner);
                    break;
                case 7:
                    deleteBook(scanner);
                    break;
                case 8:
                    selectBooks();
                    break;
                /*case 9:
                    selectBooksWithAuthors();
                    break;*/
                default:
                    throw new NumberFormatException();
            }
        }
    }

    private static void selectAuthors() {
        Collection<Author> authors = Facade.getInstance().getAuthorDAO().select();
        System.out.println("id" +" "+ "Имя"+" "+ "Фамилия"+" "+ "Отчество");
        for (Author author : authors) {
            System.out.println(author.getAuthorId()+" * "+author.getFirstName()+" * "+author.getSecondName()+" * "+author.getMiddleName());
        }
    }

    private static void selectBooks() {
        Collection<Book> books = Facade.getInstance().getBookDAO().select();
        System.out.println("id" +" "+ "Название книги"+" "+ "Автор");
        for (Book book : books) {
            System.out.println(book.getBookId()+" * "+book.getNameOfBook()+" * "+book.getAuthor());
        }
    }

    private static void insertAuthor(Scanner scanner) {
        Author author = new Author();
        System.out.println("Введите имя:");
        author.setFirstName(scanner.nextLine());
        System.out.println("Введите фамилию:");
        author.setSecondName(scanner.nextLine());
        System.out.println("Введите отчество:");
        author.setMiddleName(scanner.nextLine());
        if (Facade.getInstance().getAuthorDAO().insert(author)) {
            System.out.println(String.format("Сохранен, идентификатор = %s", author.getAuthorId()));
            return;
        }
        System.out.println("Не сохранен");
    }

    private static void insertBook(final Scanner scanner) {
        Book book = new Book();
        System.out.println("Введите название:");
        book.setNameOfBook(scanner.nextLine());
        System.out.println("Введите автора:");
        while (true) {
            try {
                book.setAuthor(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Должно быть числом");
            }
        }
        if (Facade.getInstance().getBookDAO().insert(book)) {
            System.out.println(String.format("Сохранена, идентификатор = %s", book.getBookId()));
            return;
        }
        System.out.println("Не сохранена");
    }

    private static void deleteAuthor(final Scanner scanner) {
        selectAuthors();
        System.out.println("Введите идентификатор автора:");
        String idAuthor;
        while (true) {
            try {
                idAuthor = scanner.nextLine();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            }
        }
        System.out.println("Вы уверены что хотите удалить автора?(Y / N)");
        Boolean decision;
        while (true) {
            String s = scanner.nextLine();
            if ("YN".contains(s.toUpperCase())) {
                if ("Y".equalsIgnoreCase(s)) {
                    decision = Boolean.TRUE;
                    break;
                } else if ("N".equalsIgnoreCase(s)) {
                    decision = Boolean.FALSE;
                    break;
                }
            }
            System.out.println("Попробуйте еще раз");
        }
        if (decision) {
            Author tempAuthor = new Author();
            tempAuthor.setAuthorId(idAuthor);
            Facade.getInstance().getAuthorDAO().delete(tempAuthor);
            System.out.println("Удален");
        } else {
            System.out.println("Не удален");
        }
    }
    private static void deleteBook(final Scanner scanner) {
        selectBooks();
        System.out.println("Введите идентификатор книги:");
        String book;
        while (true) {
            try {
                book = scanner.nextLine();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            }
        }
        System.out.println("Вы уверены что хотите удалить книгу?(Y / N)");
        Boolean decision;
        while (true) {
            String s = scanner.nextLine();
            if ("YN".contains(s.toUpperCase())) {
                if ("Y".equalsIgnoreCase(s)) {
                    decision = Boolean.TRUE;
                    break;
                } else if ("N".equalsIgnoreCase(s)) {
                    decision = Boolean.FALSE;
                    break;
                }
            }
            System.out.println("Попробуйте еще раз");
        }
        if (decision) {
            Book tempBook = new Book();
            tempBook.setBookId(book);
            Facade.getInstance().getBookDAO().delete(tempBook);
            System.out.println("Удалена");
        } else {
            System.out.println("Не удалена");
        }
    }

    private static void updateAuthor(final Scanner scanner) {
        selectAuthors();
        System.out.println("Введите идентификатор автора:");
        String author;
        while (true) {
            try {
                author = scanner.nextLine();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            }
        }
        Author tempAuthor = new Author();
        tempAuthor.setAuthorId(author);
        System.out.println("Введите новое имя:");
        tempAuthor.setFirstName(scanner.nextLine());
        System.out.println("Введите новую фамилию:");
        tempAuthor.setSecondName(scanner.nextLine());
        System.out.println("Введите новое отчество:");
        tempAuthor.setMiddleName(scanner.nextLine());
        System.out.println("Вы уверены что хотите обновить автора?(Y / N)");
        Boolean decision;
        while (true) {
            String s = scanner.nextLine();
            if ("YN".contains(s.toUpperCase())) {
                if ("Y".equalsIgnoreCase(s)) {
                    decision = Boolean.TRUE;
                    break;
                } else if ("N".equalsIgnoreCase(s)) {
                    decision = Boolean.FALSE;
                    break;
                }
            }
            System.out.println("Попробуйте еще раз");
        }
        if (decision) {
            Facade.getInstance().getAuthorDAO().update(tempAuthor);
            System.out.println("Сохранен");
        } else {
            System.out.println("Не сохранен");
        }
    }

    private static void updateBook(final Scanner scanner) {
        selectAuthors();
        System.out.println("Введите идентификатор книги:");
        String bookId;
        while (true) {
            try {
                bookId = scanner.nextLine();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            }
        }
        Book tempBook = new Book();
        tempBook.setBookId(bookId);
        System.out.println("Введите новое название:");
        tempBook.setNameOfBook(scanner.nextLine());
        System.out.println("Введите нового автора:");
        while (true) {
            try {
                tempBook.setAuthor(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Должно быть числом");
            }
        }
        System.out.println("Вы уверены что хотите обновить книгу?(Y / N)");
        Boolean decision;
        while (true) {
            String s = scanner.nextLine();
            if ("YN".contains(s.toUpperCase())) {
                if ("Y".equalsIgnoreCase(s)) {
                    decision = Boolean.TRUE;
                    break;
                } else if ("N".equalsIgnoreCase(s)) {
                    decision = Boolean.FALSE;
                    break;
                }
            }
            System.out.println("Попробуйте еще раз");
        }
        if (decision) {
            Facade.getInstance().getBookDAO().update(tempBook);
            System.out.println("Сохранена");
        } else {
            System.out.println("Не сохранена");
        }
    }
}
