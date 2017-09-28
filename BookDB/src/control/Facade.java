package control;

/**
 * Created by d.zhuchkov on 30.03.2016.
 */
public class Facade {
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;
    private static Facade instance;

    /*public Facade() {
        this.bookDAO = new BookDAO();
        this.authorDAO = new AuthorDAO();
    }*/

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }


    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }

}
