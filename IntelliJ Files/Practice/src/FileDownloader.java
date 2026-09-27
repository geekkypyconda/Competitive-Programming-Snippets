import java.util.concurrent.Semaphore;

public class FileDownloader {
    int size,chunkSize;
    int numChunks, completedChunks;

    Semaphore one;

    FileDownloader(int fileSize, int chunkSize){
        this.size = fileSize;
        this.chunkSize = chunkSize;
        this.numChunks = size / chunkSize;
        this.completedChunks = 0;

        one = new Semaphore(1);
    }

    int getNextChunk() throws InterruptedException{
        one.acquire();

        int val = this.completedChunks++;
        if(val > numChunks) val = -1;

        one.release();
        return val;
    }

    void markComplete(int chunkId){
        System.out.println(chunkId + " -> Download complete");
    }

}
