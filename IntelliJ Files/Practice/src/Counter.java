public class Counter{
    int counter;
    public Counter() {
        this.counter = 0;
    }

    synchronized void inc(){
        this.counter++;
    }

    int getVal(){
        return counter;
    }

    void print(String threadName){
        System.out.println(threadName + " priting val: " + this.counter);
    }
}
