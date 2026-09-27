import java.util.*;
import java.util.concurrent.Semaphore;

public class CustomBlockingQueue {
    Semaphore empty, full, mutex;
    Queue<Integer> q;

    CustomBlockingQueue(int cap){
        this.empty = new Semaphore(cap, true);
        this.full = new Semaphore(0, true);
        this.mutex = new Semaphore(1,true);
        this.q = new LinkedList<>();
    }

    void put(int val) throws Exception{
        empty.acquire();
        mutex.acquire();
        q.offer(val);

        mutex.release();
        full.release();
    }

    int take() throws Exception{
        full.acquire();
        mutex.acquire();
        int val = q.poll();
        mutex.release();
        empty.release();

        return val;
    }
}
