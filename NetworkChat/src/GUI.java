import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by d.zhuchkov on 03.06.2016.
 */
public class GUI extends JFrame {
    public static void setOutTextArea(String s) {
        outTextArea.append(s);
    }
    private static JTextArea outTextArea=new JTextArea();
    public static void main(String[] args) {
        new GUI();
    }

    public GUI() throws HeadlessException {
        setTitle("Сервер чата");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350,350);
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        final JButton buttonOn = new JButton("Включение сервера");
        cp.add(BorderLayout.NORTH, buttonOn);
        outTextArea.setEditable(false);
        cp.add(BorderLayout.CENTER, outTextArea);
        buttonOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonText = buttonOn.getText();
                if (buttonText.equals("Включение сервера")) {
                    buttonOn.setText("Выключение сервера");
                    Thread thread = new Thread(new ChatServer());
                    thread.start();
                    setOutTextArea("Сервер запущен."+"\n");
                } else if (buttonText.equals("Выключение сервера")){
                    buttonOn.setText("Включение сервера");
                    outTextArea.append("Выключение сервера."+"\n");
                    ChatServer.shutdown();
                    Thread.currentThread().interrupt();
                }
            }
        });
        setVisible(true);
    }
}
