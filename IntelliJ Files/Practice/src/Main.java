import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {
        int fileSize = 100000;
        int chunkSize = 10;

        FileDownloader downloader = new FileDownloader(fileSize, chunkSize);

        int workers = 5;

        Worker[] arr = new Worker[workers];

        for(int i = 0; i < workers; i++){
            arr[i] = new Worker(downloader, i);
            arr[i].start();
        }

        for(int i = 0; i < workers; i++){
            arr[i].join();
        }

        System.out.println("Download complete.");
    }
}

