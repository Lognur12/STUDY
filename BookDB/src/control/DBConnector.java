package control;

import model.QueryConvert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by d.zhuchkov on 29.03.2016.
 */
public class DBConnector {
    private static DBConnector instance;
    private final static String URL_CONNECTION = "jdbc:postgresql://79.135.239.250:5432/HameleoTest"; //79.135.239.250 192.168.1.12
    private final static String login = "postgres";
    private final static String pass = "123456";
    private Connection connection;

    private DBConnector() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            this.connection = DriverManager.getConnection(URL_CONNECTION, login, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    public Integer executeUpdate(final String sql) {
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(sql);
            int resultSet=statement.executeUpdate();
            if (resultSet > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                   e.printStackTrace();
                }
        }
        return 0;
    }


    public Integer executeInsert(final String sql) {
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            if (rs.next()) {
                return  rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return 0;
    }

    private String[] getColums(ResultSetMetaData metaData) { //метод получения имен колонок из БД в массив строк
        try {
            String[] res = new String[metaData.getColumnCount()];
            for (int i = 0; i < res.length; i++) {
                res[i] = metaData.getColumnName(i + 1);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<QueryConvert> executeSelectPS(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(); //выполнение запроса с возратом данных из БД
            ResultSetMetaData metaData = resultSet.getMetaData(); //получение каких-то данных из БД
            String[] columns = getColums(metaData); // формирование названий колонок из полученых данных в виде строк из БД
            Collection<QueryConvert> result = new ArrayList<QueryConvert>(); //коллекция объектов пар данных (имя_колонки/значение_колонки)
            while (resultSet.next())
            {
                QueryConvert queryConvert = new QueryConvert(); //создаем объект запроса, в который записываем пары (имя_колонки/значение_колонки)
                for (String column : columns) { //пробегаемся по именам колонок
                    //QueryConvert queryConvert = new QueryConvert(); //создаем объект запроса, в который записываем пары (имя_колонки/значение_колонки)
                    /*System.out.println(column.toString());
                    System.out.println(String.valueOf(resultSet.getObject(column)));*/
                    queryConvert.getEntities().put(column, String.valueOf(resultSet.getObject(column))); //добовляем в объект пары (имя_колонки/значение_колонки)
                }
                result.add(queryConvert); //добовляем объекты в коллекцию
            }
           /* for (QueryConvert q : result) {
                System.out.println(q.getEntities().toString());
            }*/
            return result; // возвращаем коллекцию
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList(); //или возвращаем пустой лист
    }
}
