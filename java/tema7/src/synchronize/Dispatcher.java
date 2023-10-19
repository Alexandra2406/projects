package synchronize;

import java.io.File;

public class Dispatcher {

	public static void main(String[] args) {
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");
        File file3 = new File("file3.txt");
        Sum sum = new Sum();
        Thread t1 = new Thread(new Counter(file1, sum));
        Thread t2 = new Thread(new Counter(file2, sum));
        Thread t3 = new Thread(new Counter(file3, sum));
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
        System.out.println("Total sum: " + sum.getSum());
    }

}
