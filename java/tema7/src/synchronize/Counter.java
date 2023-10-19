package synchronize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Counter implements Runnable {
    private final File file;
    private final Sum sum;

    public Counter(File file, Sum sum) {
        this.file = file;
        this.sum = sum;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                for (String token : tokens) {
                    try {
                        double number = Double.parseDouble(token);
                        synchronized (sum) {
                            sum.add(number);
                        }
                    } catch (NumberFormatException e) {
                        // Ignore non-numeric 
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
class Sum {
	private double sum;
	public synchronized void add(double number) {
		sum += number;
	}
	public synchronized String getSum() {
		DecimalFormat df = new DecimalFormat("#.######");
		return df.format(sum);
	}
	}

