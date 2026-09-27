import java.security.DigestInputStream;

public class Philospher extends Thread{
    int id;
    int eatTimes;
    DiningPhilosphers diningTable;

    Philospher(int id, int eatTimes, DiningPhilosphers dp){
        this.id = id;
        this.eatTimes = eatTimes;
        diningTable = dp;
    }

    @Override
    public void run() {
        for(int x = 0;x < this.eatTimes;x++){
            try{
                System.out.println("Philospher: " + id + ", is eating");
                diningTable.eat(id);
                System.out.println("Philospher: " + id + ", has eaten");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
