package view.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

/**
 * Created by d.zhuchkov on 13.04.2016.
 */
public class SelectFrame<T> extends BaseFrame {
    private JTable table;
    private DefaultTableModel model;
    private ObjectArrayInterface<T> arrayInterface;

    public static interface ObjectArrayInterface<T> {
        public Object[] getObjects(T t);
    }
    public SelectFrame(String title, int width, int height, String [] colums, ObjectArrayInterface<T> arrayInterface) throws HeadlessException {
        super(title, width, height);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout()); // область отображения данных
        this.table = new JTable(); // таблица с данными
        this.model = new DefaultTableModel(colums, 1);
        this.table.setModel(this.model);
        JScrollPane pane = new JScrollPane(this.table); // панель скроллинга, передаем саму таблицу данных для скроллинга
        this.getContentPane().add(pane);
        this.arrayInterface = arrayInterface;
    }

    public void showData(Collection<T> collection) { // метод получения данных
        this.model.setRowCount(0); //каждый раз очищает таблицу от предыдущих данных
        this.setVisible(true);
        for (T t : collection) {
            this.model.addRow(this.arrayInterface.getObjects(t));
        }
    }
}
