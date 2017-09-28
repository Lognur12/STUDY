package ru.javacourse.book.dao;


public class BookDAOFactory {
    public static BookDAO getDao() {
        return new BookFakeDAO();
    }

    public static BookDAO getDao(String className) {
        BookDAO dao = null;
        try {
            Class classDefinition = Class.forName(className);
            dao = (BookDAO) classDefinition.newInstance();
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return dao;
    }
}
