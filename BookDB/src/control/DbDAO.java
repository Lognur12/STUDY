package control;

import model.QueryConvert;

/**
 * Created by d.zhuchkov on 05.04.2016.
 */
 abstract class DbDAO<T> implements DAO<T>{

    abstract String insertSql(final T row);

    abstract String selectAllSql();

    abstract String deleteSql(final T row);

    abstract String updateSql(final T row);

    abstract T convertFromBD(final QueryConvert query);

    abstract QueryConvert convertToBD(final T t);

    abstract String columnsQuery(QueryConvert query);

    abstract String valuesQuery(QueryConvert query);

    abstract String getTableName();
}
