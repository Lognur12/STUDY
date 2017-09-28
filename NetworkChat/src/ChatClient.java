import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class ChatClient extends JFrame implements Runnable {

    protected Socket socket;
    protected DataInputStream inStream;
    protected DataOutputStream outStream;
    protected JTextArea outTextArea;
    protected JTextField inTextField;
    protected boolean isOn;

    public ChatClient(String title, Socket s, DataInputStream dis, DataOutputStream dos) {
        super(title);
        socket = s;
        inStream = dis;
        outStream = dos;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.CENTER, outTextArea = new JTextArea());
        outTextArea.setEditable(false);
        cp.add(BorderLayout.SOUTH, inTextField = new JTextField());

        inTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    outStream.writeUTF(inTextField.getText());
                    outStream.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    isOn = false;
                }
                inTextField.setText("");
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                isOn = false;
                try {
                    outStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);
        inTextField.requestFocus();
        (new Thread(this)).start();
    }

    @Override
    public void run() {
        isOn = true;
        try {
            while (isOn) {
                String line = inStream.readUTF();
                outTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inTextField.setVisible(false);
            validate();
        }
    }

    public static void main(String[] args) throws IOException {
//        if (args.length != 2) {
//            throw new RuntimeException("Syntax: ChatClient <host> <port>");
//        }
        String args0 = "localhost";
        String args1 = "8082";
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            socket = new Socket(args0, Integer.parseInt(args1));
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            new ChatClient("Chat " + args0 + ":" + args1, socket, dis, dos);
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println("Клиенту не удалось подключиться к серверу");
            try {
                if (dos != null) dos.close();
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }
    }
}
