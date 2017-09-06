import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by XZL on 2017/6/20.
 */
public class Example2906 {
    public static void main(String[] args){
        ExecutorService executor= Executors.newFixedThreadPool(3);
        executor.execute(new PrintChar('a',100));
        executor.execute(new PrintChar('b',100));
        executor.execute(new PrintNum(100));
        executor.execute(new PrintChar('d',100));
        executor.shutdown();
    }
}
