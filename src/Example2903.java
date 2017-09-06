import javax.swing.*;

/**
 * Created by XZL on 2017/6/20.
 */
public class Example2903 extends JApplet{
    public Example2903(){
        add(new JLabel("Hi,it runs from an event dispatch thread"));
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new JFrame("EventDispatcherThreadDemo");
                frame.add(new Example2903());
                frame.setSize(200,200);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
