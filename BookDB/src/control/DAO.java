package control;

import java.util.Collection;

/**
 * Created by d.zhuchkov on 31.03.2016.
 */
public interface DAO <T> {

   public boolean update(T sql);

    public boolean delete(T sql);

    public boolean insert(T sql);

    public Collection <T> select();
}
