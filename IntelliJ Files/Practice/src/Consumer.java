import javax.swing.*;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    CustomBlockingQueue q;
    String name;
    public Consumer(CustomBlockingQueue bq, String name) {
        q = bq;
        this.name = name;
    }

    public void run() {
        while(true){
            try {
                System.out.println("Consumer " + name + " Consuming: " + q.take());
                Thread.sleep(25);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
