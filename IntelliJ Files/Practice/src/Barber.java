public class Barber extends Thread{
    BarberShop shop;

    Barber(BarberShop bs){
        this.shop = bs;
    }

    @Override
    public void run() {
        try {
            this.shop.doWork();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
