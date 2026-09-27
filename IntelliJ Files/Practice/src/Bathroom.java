import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class sort implements Comparator<Person>{
    @Override
    public int compare(Person o1, Person o2) {
        return o1.duration - o2.duration;
    }
}

public class Bathroom {
    static final int DEMOCRAT = 0;
    static final int REPUBLICAN = 1;

    int cap;
    int inside = 0;
    int party = -1;

    int waitingDemocrats = 0;
    int waitingRepublicans = 0;

    Queue<Person> waitingQueue;
    PriorityQueue<Person> demos, repubs;
    Semaphore mutex, insideMutex, waitMutex;

    Bathroom(int cap) throws InterruptedException {
        this.cap = cap;

        mutex = new Semaphore(1, true);
        insideMutex = new Semaphore(1);
        waitMutex = new Semaphore(1);
        waitingQueue = new LinkedList<>();

        demos = new PriorityQueue<>(new sort());
        repubs = new PriorityQueue<>(new sort());

        new Thread(() -> {
            try {
                scheduler();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void entry(Person p) throws InterruptedException{
        mutex.acquire();
        System.out.println("Person: " + p.id + ", came from party: " + p.party);
        waitingQueue.offer(p);
        mutex.release();

        p.keepWaiting();
    }

    void scheduler() throws InterruptedException{
        while (true){
            mutex.acquire();
            while (!waitingQueue.isEmpty()){
                Person p = waitingQueue.poll();
                if(p.party == 0) demos.offer(p);
                else repubs.offer(p);

            }

            mutex.release();

            int turn = 0;
            while (!demos.isEmpty() || !repubs.isEmpty()){
                waitMutex.acquire();
                if(turn == 0){
                    int max = Math.min(demos.size(), cap);
                    while (max-- > 0){
                        Person l = demos.poll();
                        l.doneWaiting();
                    }

                }else{
                    int max = Math.min(repubs.size(), cap);
                    while (max-- > 0){
                        Person l = repubs.poll();
                        l.doneWaiting();
                    }
                }

                turn = 1 - turn;
            }
        }
    }

    void useWashRoom(Person P) throws InterruptedException{
        insideMutex.acquire();
        inside++;
        insideMutex.release();
        System.out.println(P.name + ", is using washroom");
    }

    void exit(Person P) throws InterruptedException{
        boolean last = false;
        System.out.println(P.name + ", is leaving the washroom");
        insideMutex.acquire();
        inside--;
        if(inside == 0) last = true;
        insideMutex.release();

        if(last) waitMutex.release();
    }
}
