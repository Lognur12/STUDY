package view.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

/**
 * Created by d.zhuchkov on 22.04.2016.
 */
public class DeleteFrame<T> extends BaseFrame {

    public static interface DeleteInterface<T> {
        public boolean delete(T t);
        public T create(Object[] objects);
    }

    public abstract static class TextField {
        private JTextField textField;
        public Object getObject() {
            return getObject(this.textField.getText());
        }
        public abstract String getName();
        public abstract Object getObject(String s) throws IllegalArgumentException;
    }


    public DeleteFrame(String title, int width, int height, DeleteInterface<T> deleteInterface, Collection<TextField> textFields, String deleteButtonName) throws HeadlessException {
        super(title, width, height);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        Box box = Box.createVerticalBox();
        for (TextField text : textFields) {
            JLabel label = new JLabel(text.getName());
            JTextField textField = new JTextField();
            text.textField = textField;
            textField.setMaximumSize(new Dimension(300,30));
            box.add(label);
            box.add(textField);
        }
        JButton button = new JButton(deleteButtonName);
        JLabel result = new JLabel();
        box.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] objects = new Object[textFields.size()];
                try {
                    int i = 0;
                    for (TextField field : textFields) {
                        objects[i] = field.getObject();
                        i++;
                    }
                    T t = deleteInterface.create(objects);
                    if (deleteInterface.delete(t)) {
                        result.setText("Удален");
                    } else {
                        result.setText("Не удален");
                    }
                } catch (Exception e1) {
                    result.setText(e1.getMessage());
                }
            }
        });
        box.add(result);
        this.setContentPane(box);
    }
}
