package view.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by d.zhuchkov on 13.04.2016.
 */
public class BaseFrame extends JFrame {
    public BaseFrame(String title, int width, int height) throws HeadlessException {
        this.setTitle(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(width, height);
    }
}
