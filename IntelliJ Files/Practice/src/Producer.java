import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    CustomBlockingQueue q;
    int n;
    public Producer(CustomBlockingQueue bq) {
        q = bq;
        this.n = 100;
    }

    public void run() {
        while(n-- > 0){
            try {
                System.out.println("Produce Putting: " + n);
                q.put(n);
                Thread.sleep(10);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
