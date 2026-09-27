import java.sql.Time;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class CustomLock{
    int lock;

    CustomLock(){
        lock = 1;
    }

    synchronized void acquire() throws InterruptedException{
        while (lock <= 0){
            wait();
        }

        lock--;
    }

    synchronized void release(){
        lock++;
        notify();
    }
}

public class BarberShop {
   private CustomLock barber, customer, seat;
   int freeSeats;

   BarberShop(int n){
       this.freeSeats = n;
//       barber = new Semaphore(0, true);
//       customer = new Semaphore(0, true);
//       seat = new Semaphore(1, true);
   }

   void entry(int customerID) throws InterruptedException {
       seat.acquire();
       if(freeSeats > 0){
           freeSeats--;
           seat.release();

           customer.release();
           barber.acquire();
           System.out.println("Customer: " + customerID + " is now getting a hair cut");

       }else {
           System.out.println("Customer: " + customerID + " Does not got a free seat so he left!");
           seat.release();
       }
   }

   void doWork() throws InterruptedException {
       while (true){
           customer.acquire();

           seat.acquire();
           freeSeats++;
           seat.release();

           barber.release();
           cutHair();
       }
   }

   void cutHair() throws InterruptedException{
       int min = 300; int max = 1000;
       Random random = new Random();
       int timeForCutting = random.nextInt((max - min) + 1) + min;
       Thread.sleep(timeForCutting);
   }

}
