package threads;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Dispatcher {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        File[] files = { new File("file1.txt"), new File("file2.txt"), new File("file3.txt") };
        ExecutorService executor = Executors.newFixedThreadPool(files.length);
        TreeSet<Result> results = new TreeSet<Result>();
        
        try {
            for (File file : files) {
                Future<Integer> futureResult = executor.submit(new FileProcessor(file));
                results.add(new Result(file, futureResult));//not right error
            }
        } finally {
            executor.shutdown();
        }
        BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("file.txt"));
			for (Result result : results) {
				bw.write(result.getFile().getName() + ": " + result.getResult()+"\n");
	        }		
			System.out.println("Successfully wrote to the file.");
		}catch (IOException e) {
			System.out.println(e);
		}finally {
			try {
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}

class FileProcessor implements Callable<Integer> {
    private File file;
    
    public FileProcessor(File file) {
        this.file = file;
    }
    
    public Integer call() throws Exception {
        int count = 0;
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("");
            while (scanner.hasNext()) {
                if (scanner.next().matches("\\p{Punct}")) {
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getName());
        }
        return count;
    }
}

class Result implements Comparable<Result> {
    private File file;
    private Future<Integer> futureResult;
    public Result(File file, Future<Integer> futureResult) {
        this.file = file;
        this.futureResult = futureResult;
    }

    public File getFile() {
        return file;
    }

    public int getResult() throws InterruptedException, ExecutionException {
        return futureResult.get(); 
    }
    
    public int compareTo(Result other) {
        try {
            return Integer.compare(other.getResult(), getResult());
        } catch (Exception e) {
            return 0;
        }
    }
}
   
