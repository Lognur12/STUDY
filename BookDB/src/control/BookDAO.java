package control;

import model.Book;
import model.QueryConvert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by d.zhuchkov on 30.03.2016.
 */
public class BookDAO extends DbDAO<Book> {

    @Override
    public String insertSql(Book row) {
       return String.format("INSERT INTO %1$s(%2$s) values(%3$s) returning id;", Book.getTABLE(), columnsQuery(convertToBD(row)), valuesQuery(convertToBD(row)));
    }

    @Override
    public String selectAllSql() {
        return String.format("SELECT * FROM %1$s;", Book.getTABLE());
    }

    @Override
    public String deleteSql(Book row) {
        return String.format("DELETE FROM %1$s WHERE id=%2$s;", Book.getTABLE(), row.getBookId());
    }

    @Override
    public String updateSql(Book row) {
        return String.format("UPDATE %1$s SET name='%2$s', author='%3$s' WHERE id=%4$s;",
                Book.getTABLE(), row.getNameOfBook(), row.getAuthor(), row.getBookId());
    }

    @Override
    Book convertFromBD(QueryConvert query) {
        Book book = new Book();
        book.setBookId(String.valueOf(query.getValueOfQueryConvert("id")));
        book.setNameOfBook((String) query.getValueOfQueryConvert("name"));
        book.setAuthor(String.valueOf(query.getValueOfQueryConvert("author")));
        return book;
    }

    @Override
    QueryConvert convertToBD(Book book) {
        QueryConvert query = new QueryConvert();
        query.getEntities().put("name", book.getNameOfBook());
        query.getEntities().put("author", book.getAuthor());
        return query;
    }

    @Override
    String columnsQuery(QueryConvert query) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : (Iterable<Map.Entry<String, String>>) query.getEntities().entrySet()) {
            String s = stringStringEntry.getKey();
            builder.append(s);
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }

    @Override
    String valuesQuery(QueryConvert query) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : (Iterable<Map.Entry<String, String>>) query.getEntities().entrySet()) {
            String s = String.valueOf(stringStringEntry.getValue());
            builder.append("'");
            builder.append(s);
            builder.append("'");
            builder.append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        return builder.toString();
    }

    @Override
    String getTableName() {
        return null;
    }

    @Override
    public boolean update(Book sql) {
        int id = DBConnector.getInstance().executeUpdate(updateSql(sql));
        return id > 0;
    }

    @Override
    public boolean delete(Book sql) {
        int id = DBConnector.getInstance().executeUpdate(deleteSql(sql));
        return id != 0;
    }

    @Override
    public boolean insert(Book sql) {
        int id = DBConnector.getInstance().executeInsert(insertSql(sql));
        if (id > 0) {
            sql.setBookId(String.valueOf(id));
            return true;
        }
        return false;
    }

    @Override
    public Collection<Book> select() {
        Collection<QueryConvert> rows = DBConnector.getInstance().executeSelectPS(selectAllSql());
        Collection<Book> books = new ArrayList<>();
        for (QueryConvert qc : rows) {
            books.add(convertFromBD(qc));
        }
        return books;
    }
}
