import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.HashMap;


public class UIServer extends JFrame{

    static JFrame frame;
    static JTextArea textPane =new JTextArea();
    UIServer(){
        InitAll();
    }

    public  void InitAll(){
        InitFrame();
        InitPanel();
        frame.setVisible(true);

    }

    private  void InitFrame() {
        frame=new JFrame("Server");
        frame.setSize(300,400);
        frame.setLayout(null);
    }
    private static void InitPanel(){
        textPane.setLayout(null);
        textPane.setBounds(200,0,100,400);
        textPane.setEditable(false);
        textPane.setFocusable(false);
        textPane.setVisible(true);
        textPane.setBackground(Color.CYAN);
        textPane.setText("Online: ");
        frame.add(textPane);
    }
    public static void OnlineUpdate(){
        textPane.setText("Online: "+"\n");
        for (HashMap.Entry<String, Socket> entry : Server.mapOnline.entrySet())
            if (entry.getValue() != null)
                textPane.append(entry.getKey() + "\n");
        frame.add(textPane);
    }
}
