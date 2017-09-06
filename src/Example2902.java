import javax.swing.*;

/**
 * Created by XZL on 2017/6/8.
 */
public class Example2902 extends JApplet implements Runnable{
    private JLabel j1bText=new JLabel("Welcome",JLabel.CENTER);
    public Example2902(){
        add(j1bText);
        new Thread(this).start();
    }
    @Override
    public void run() {
        try {
            while (true){
                if(j1bText.getText()==null){
                    j1bText.setText("Welcome");
                }else{
                    j1bText.setText(null);
                }
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
