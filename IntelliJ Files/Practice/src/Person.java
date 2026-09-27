import java.util.concurrent.Semaphore;

public class Person extends Thread{
    String name;
    int party, duration, id;
    Bathroom br;
    Semaphore isWaiting;

    Person(int id, int party, Bathroom br, int duration){
        this.id = id;
        this.party = party;
        this.br = br;
        this.duration = duration;
        isWaiting = new Semaphore(0);
    }

    @Override
    public void run() {
        int i = 0;
        while (i++ <= 5){
            try {
                br.entry(this);
                br.useWashRoom(this);
                Thread.sleep(100);
                br.exit(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    void keepWaiting()throws InterruptedException{
        this.isWaiting.acquire();
    }

    void doneWaiting()throws InterruptedException{
        this.isWaiting.release();
    }
}
