import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

class ServerSomthing extends Thread {

    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private BufferedReader in;
    private BufferedWriter out;
    public static String nickName;


    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Server.story.printStory(out);
        start();
    }
    @Override
    public void run() {
        String word;
        try {
            // первое сообщение отправленное сюда - это никнейм
            word = in.readLine();
            nickName = word.substring(17);
            Server.mapOnline.put(nickName, socket);
            UIServer.OnlineUpdate();
            try {
                out.write(word + "\n");
                out.flush();
            } catch (IOException ignored) {}
            try {
                while (true) {
                    word = in.readLine();
                    if(word==null) {
                        this.downService();// харакири
                        Server.mapOnline.put(Client.getNickName(),null);
                        UIServer.OnlineUpdate();
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                    System.out.println("Echoing: " + word);
                    Server.story.addStoryEl(word);
                    for (ServerSomthing vr : Server.serverList)
                        vr.send(word); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
                }
            } catch (NullPointerException ignored) {}
        } catch (IOException e) {
            this.downService();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}

    }

    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerSomthing vr : Server.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}

class Story {
    private LinkedList<String> story = new LinkedList<>();
    public void addStoryEl(String el) {
        if (story.size() >= 10) {
            story.removeFirst();
            story.add(el);
        } else {
            story.add(el);
        }
    }

    /**
     * отсылаем последовательно каждое сообщение из списка
     * в поток вывода данному клиенту (новому подключению)
     * @param writer
     */

    public void printStory(BufferedWriter writer) {
        if(story.size() > 0) {
            try {
                writer.write("History messages" + "\n");
                for (String vr : story) {
                    writer.write(vr + "\n");
                }
                writer.write("/...." + "\n");
                writer.flush();
            } catch (IOException ignored) {}

        }

    }
}

public class Server {
    public static ArrayList<String> online =new ArrayList<>();
    public static HashMap<String,Socket> mapOnline = new HashMap<>();
    public static final int PORT = 8080;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>(); // список всех нитей - экземпляров
    public static Story story;
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        story = new Story();
        System.out.println("Server Started");
        UIServer server1= new UIServer();
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerSomthing(socket));

                    // добавить новое соединенние в список
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}