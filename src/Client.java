import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by XZL on 2017/6/21.
 */
public class Client extends JFrame{
    private JTextField jtf=new JTextField();
    private JTextArea jta=new JTextArea();
    private DataOutputStream toServer;
    private DataInputStream fromServer;
    public static void main(String[] args) throws IOException {
        new Client();
    }
    public Client() throws IOException {
        JPanel p=new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel("Enter radius"),BorderLayout.WEST);
        p.add(jtf,BorderLayout.CENTER);
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        setLayout(new BorderLayout());
        add(p,BorderLayout.NORTH);
        add(new JScrollPane(jta),BorderLayout.CENTER);
        jtf.addActionListener(new TextFieldListener());
        setTitle("Client");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Socket socket=new Socket("10.18.5.132",8001);
        fromServer=new DataInputStream(socket.getInputStream());
        toServer=new DataOutputStream(socket.getOutputStream());
    }
    private class TextFieldListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                /*double radius=Double.parseDouble(jtf.getText().trim());
                toServer.writeDouble(radius);*/
                String string=jtf.getText().trim();
                toServer.writeUTF(string);
                toServer.flush();
                //double area=fromServer.readDouble();
                String area=fromServer.readUTF();
                jta.append("Statement is:\n"+string+"\n");
                jta.append("Words received from the server are:\n"+area+"\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
