package control;

import model.Author;
import model.QueryConvert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by d.zhuchkov on 30.03.2016.
 */
public class AuthorDAO extends DbDAO<Author> {

    @Override
    Author convertFromBD(QueryConvert query) {
        Author author = new model.Author();
            author.setAuthorId(String.valueOf(query.getValueOfQueryConvert("id")));
            author.setFirstName((String) query.getValueOfQueryConvert("firstname"));
            author.setSecondName((String) query.getValueOfQueryConvert("secondname"));
            author.setMiddleName((String) query.getValueOfQueryConvert("middlename"));
        return author;
    }

    @Override
    QueryConvert convertToBD(Author author) {
        QueryConvert query = new QueryConvert();
        query.getEntities().put("firstname", author.getFirstName());
        query.getEntities().put("secondname", author.getSecondName());
        query.getEntities().put("middlename", author.getMiddleName());
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
            String s = stringStringEntry.getValue();
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
    protected String insertSql(Author row) {
       return String.format("INSERT INTO %1$s(%2$s) values(%3$s) returning id;", Author.getTABLE(), columnsQuery(convertToBD(row)), valuesQuery(convertToBD(row)));
    }


    @Override
    protected String selectAllSql() {
        return String.format("SELECT * FROM %1$s;", Author.getTABLE());
    }

    @Override
    protected String deleteSql(Author row) {
        return String.format("DELETE FROM %1$s WHERE id=%2$s;", Author.getTABLE(), row.getAuthorId());
    }

    @Override
    protected String updateSql(Author row) {
        return String.format("UPDATE %1$s SET firstname='%2$s', secondname='%3$s', middlename='%4$s' WHERE id=%5$s;",
                Author.getTABLE(), row.getFirstName(), row.getSecondName(), row.getMiddleName(), row.getAuthorId());
    }

    @Override
    public boolean update(Author sql) {
        int id = DBConnector.getInstance().executeUpdate(updateSql(sql));
        return id > 0;
    }

    @Override
    public boolean delete(Author sql) {
        int id = DBConnector.getInstance().executeUpdate(deleteSql(sql));
        return id != 0;
    }

    @Override
    public boolean insert(Author sql) {
        int id = DBConnector.getInstance().executeInsert(insertSql(sql));
        if (id > 0) {
            sql.setAuthorId((String.valueOf(id)));
            return true;
        }
        return false;
    }

    @Override
    public Collection<Author> select() {
        Collection<QueryConvert> rows = DBConnector.getInstance().executeSelectPS(selectAllSql());
        Collection<Author> authors = new ArrayList<>();
        for (QueryConvert qc : rows) {
            authors.add(convertFromBD(qc));
        }
        return authors;

    }
}
