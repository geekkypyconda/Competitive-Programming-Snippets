import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DiningPhilosphers {
    Semaphore[] fork;
    int n;
    DiningPhilosphers(int n){
        fork = new Semaphore[n];
        for(int x = 0;x < n;x++) fork[x] = new Semaphore(1,true);
        this.n = n;
    }

    private void pick(int i) throws InterruptedException {
        if(i == n - 1){
            fork[(i + 1) % n].acquire();
            fork[i].acquire();
        }else{
            fork[i].acquire();
            fork[(i + 1) % n].acquire();
        }

    }

    private void put(int i) throws InterruptedException {
        fork[i].release();
        fork[(i + 1) % n].release();

    }

    synchronized void eat(int i) throws InterruptedException{
        pick(i);

        System.out.println("Philospher : " + i + ", eating now");

        put(i);
    }
}
