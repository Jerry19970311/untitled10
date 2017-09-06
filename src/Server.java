import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by XZL on 2017/6/21.
 */
public class Server extends JFrame{
    private JTextArea jta=new JTextArea();
    //Socket socket= null;
    //DataInputStream inputFromClient=null;
    //DataOutputStream outputToClient=null;
    private ServerSocket serverSocket;
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        new Server();
    }
    public Server() throws IOException, SQLException, ClassNotFoundException {
        try {
            Test0622.init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        add(new JScrollPane(jta),BorderLayout.CENTER);
        setTitle("Server");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        serverSocket=new ServerSocket(8001);
        ExecutorService executorService= Executors.newFixedThreadPool(3000);
        while (true){
            Socket socket = serverSocket.accept();
            executorService.execute(new Partword(socket));
            /*double radius=inputFromClient.readDouble();
            double area=radius*radius*Math.PI;
            outputToClient.writeDouble(area);
            jta.append("Radius received from client:"+radius+"\n");
            jta.append("Area found:"+area+"\n");*/
        }
    }
    public class Partword implements Runnable{
        private DataInputStream inputFromClient;
        private DataOutputStream outputToClient;
        public Partword(Socket socket) throws IOException {
            inputFromClient=new DataInputStream(socket.getInputStream());
            outputToClient=new DataOutputStream(socket.getOutputStream());
        }
        @Override
        public void run() {
            while (true) {
                String statement = null;
                try {
                    statement = inputFromClient.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<String> area = null;
                try {
                    area = Test0622.maximumMatching(statement);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Iterator<String> iterator = area.iterator();
                System.out.println(area);
                StringBuffer stringBuffer = new StringBuffer();
                while (iterator.hasNext()) {
                    stringBuffer.append(iterator.next() + " ");
                }
                try {
                    outputToClient.writeUTF(stringBuffer.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jta.append(statement+"\n");
                jta.append(stringBuffer.toString()+"\n");
            }
        }
    }
}
