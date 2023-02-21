import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static String getNickName() {
        return ClientSomthing.nickname;
    }

    public static void main(String[] args) {


        new ClientSomthing(ipAddr, port);
    }
}
class ClientSomthing {

    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private BufferedReader inputUser; // поток чтения с консоли
    private String addr; // ip адрес клиента
    private int port; // порт соединения
    public static String nickname; // имя
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;
    private UIClient uu;
    public static boolean bb=false;

    public ClientSomthing(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname(); // перед началом необходимо спросит имя
            uu= new UIClient();
            new ReadMsg().start(); // нить читающая сообщения из сокета в бесконечном цикле
            new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле

            //uu.InitAll();

        } catch (IOException e) {
            // Сокет должен быть закрыт при любой
            // ошибке, кроме ошибки конструктора сокета:
            ClientSomthing.this.downService();
        }
        // В противном случае сокет будет закрыт
        // в методе run() нити.
    }

    public static String getNickName() {
        return nickname;
    }
    public static boolean writeOrNot(boolean bb1) {
        bb=bb1;
        return bb;
    }

    private void pressNickname() {
        System.out.print("Press your nick: ");
        try {
            nickname = inputUser.readLine();
            out.write("На сервер зашёл: " + nickname + "\n");
            out.flush();
        } catch (IOException ignored) {
        }

    }


        /**
         * закрытие сокета
         */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }

    // нить чтения сообщений с сервера
    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine(); // ждем сообщения с сервера
                    if (str.equals("stop")) {
                        ClientSomthing.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    }
                    System.out.println(str); // пишем сообщение с сервера на консоль
                }
            } catch (IOException e) {
                ClientSomthing.this.downService();
            }
        }
    }

    // нить отправляющая сообщения приходящие с консоли на сервер
    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    if (bb) {
                        time = new Date(); // текущая дата
                        dt1 = new SimpleDateFormat("HH:mm:ss"); // берем только время до секунд
                        dtime = dt1.format(time); // время
                        userWord = uu.text; // сообщения с консоли
                        if (userWord.equals("stop")) {
                            uu.frameClose();
                            out.write(nickname);
                            //Server.mapOnline.computeIfPresent(ServerSomthing.nickName,null);
                            out.write("stop" + "\n");
                            ClientSomthing.this.downService();
                            break;
                        } else {
                            uu.updateMessage("(" + dtime + ") " + nickname + ": " + userWord + "\n");
                            out.write("(" + dtime + ") " + nickname + ": " + userWord + "\n");
                        }
                        bb=false;
                        out.flush();
                    }
                } catch (IOException e) {
                    ClientSomthing.this.downService();
                }
            }
        }
    }
}
