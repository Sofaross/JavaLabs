import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UIClient extends JFrame{
    JFrame frame;
    String nickName ;
    JTextArea textArea ;
    JTextPane textPanel ;
    JButton button;
    String text=" heeelwq";

    UIClient(){
        InitAll();
    }

    public  void InitAll(){
        nickName=ClientSomthing.nickname;
        InitFrame();
        InitJMenu();
        message();
        writeMessage();
        buttonWrite();
        frame.setVisible(true);
    }

    private  void InitFrame() {
        frame=new JFrame("Client");
        frame.setSize(314,393);
        frame.setLayout(null);
    }
    private  void InitJMenu(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(userName());
        menuBar.setPreferredSize(new Dimension(100,30));
        frame.setJMenuBar(menuBar);
    }
    public  JMenu userName(){
        JMenu jItem = new JMenu(nickName);
        jItem.setEnabled(false);
        return jItem;
    }
    public  void message(){

        textArea=new JTextArea();
        textArea.setBounds(0,0,300,300);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setVisible(true);
        textArea.setBackground(Color.CYAN);
        textArea.setText("");
        //final JScrollPane scrollPane = new JScrollPane(textArea);
        //textArea.add(scrollPane, BorderLayout.CENTER);
        //textArea.add(scrollPane);
        frame.add(textArea);
    }
    public void updateMessage(String str){
        textArea.append(str);
    }

    public void writeMessage(){
        textPanel= new JTextPane();
        textPanel.setBounds(0,300,200,25);
        //textPanel.setEditable(false);
        textPanel.setFocusable(true);
        textPanel.setVisible(true);
        textPanel.setBackground(Color.WHITE);
        frame.add(textPanel);
    }
    public void buttonWrite(){
        button=new JButton("Write");
        button.setBounds(200,300,100,25);
        button.setVisible(true);
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                text=textPanel.getText();
                textPanel.setText("");
                ClientSomthing.writeOrNot(true);
            }
        });
        frame.add(button);
    }
    public void frameClose(){
        frame.dispose();
    }

}
