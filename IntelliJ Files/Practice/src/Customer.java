import java.util.Random;

public class Customer extends Thread{
    int id;
    BarberShop shop;

    Customer(int id, BarberShop bs){
        this.id = id;
        this.shop = bs;
    }

    @Override
    public void run() {
        try {
            this.shop.entry(this.id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
