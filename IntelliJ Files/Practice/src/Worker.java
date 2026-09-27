import java.util.Random;

class Worker extends Thread {

    FileDownloader downloader;
    int id;

    Worker(FileDownloader downloader, int id){
        this.downloader = downloader;
        this.id = id;
    }

    public void run(){
        try{
            while(true){
                int chunk = downloader.getNextChunk();

                if(chunk == -1){
                    System.out.println("Worker " + id + " finished work.");
                    break;
                }

                System.out.println("Worker " + id + " downloading chunk " + chunk);

                // simulate download time
                Random rand = new Random();
                int time = rand.nextInt(500) + 200;
                Thread.sleep(time);

                downloader.markComplete(chunk);

                System.out.println("Worker " + id + " completed chunk " + chunk);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
