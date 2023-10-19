package threadsConcurrent;

import java.io.File;
import java.util.Map;
import java.util.concurrent.*;

public class Dispatcher {
    public static void main(String[] args) {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File file3 = new File("file3.txt");

        Map<String, Integer> wordCount = new ConcurrentHashMap<>();

        Thread t1 = new Thread(new WordCounter(file1, wordCount));
        Thread t2 = new Thread(new WordCounter(file2, wordCount));
        Thread t3 = new Thread(new WordCounter(file3, wordCount));

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(wordCount);
    }
}


