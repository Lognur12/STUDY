import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ChatServer extends Thread {
    private static ServerSocket serverSocket = null;
    private int i=0;
    private static Socket socket = null;
    private static List<Socket> socketList = new LinkedList<Socket>();

    public static void shutdown() {
        try {
            if (serverSocket != null) {
                GUI.setOutTextArea("Сервер закрывает порт:" + " " + serverSocket.getLocalPort()+"\n");
                serverSocket.close();
                GUI.setOutTextArea("Закрыт ли порт:" + serverSocket.isClosed()+"\n");
                if (!socketList.isEmpty()) {
                    GUI.setOutTextArea("Сервер закрывает потоки."+"\n");
                    for (Socket s : socketList) {
                        s.close();
                    }
                    GUI.setOutTextArea("Закрытыли потоки?" + socket.isClosed()+"\n");
                } else {
                    GUI.setOutTextArea("Нет подключенных клиентов."+"\n");
                }
            } else {
                GUI.setOutTextArea("Нет открытых портов."+"\n");
            }
        } catch (IOException e) {
            GUI.setOutTextArea("Не получилось закрыть сокеты, наверное уже закрыты."+"\n");
        }
    }

    @Override
    public void run() {
        try {
             serverSocket = new ServerSocket(8082);
        } catch (IOException e) {
            GUI.setOutTextArea("Порт уже используется"+"\n");
        }

        try {
            while (!Thread.interrupted()) {
                if (serverSocket != null) {
                    i++;
                    GUI.setOutTextArea("Сервер ожидает подключение клиента №."+i+"\n");
                    socket = serverSocket.accept();
                    GUI.setOutTextArea("Подключился клиент с адресса: "+" " + socket.getInetAddress()+"\n");
                    socketList.add(socket);
                }
                    ChatHandler handler = new ChatHandler(socket);
                    handler.start();
            }
        } catch (IOException ex) {
            GUI.setOutTextArea("Закрыт порт:"+" "+serverSocket.getLocalPort()+"\n");
        }/* finally {
            try {
                if (serverSocket != null) {
                    System.out.println("Сервер закрывает порт:"+" "+serverSocket.getLocalPort());
                    serverSocket.close();
                    System.out.println("Закрыт ли порт:"+serverSocket.isClosed());
                    System.out.println("Сервер закрывает потоки.");
                   socket.close();
                    System.out.println("Закрытыли потоки?"+socket.isClosed());
                }
            } catch (IOException e) {
               // e.printStackTrace();
                System.out.println("Нет соединений");
            }
        }*/
    }

    /*public static void main(String[] args)  {
        new ChatServer();
    }*/
}
