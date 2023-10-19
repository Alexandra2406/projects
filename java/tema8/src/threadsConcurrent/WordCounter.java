package threadsConcurrent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class WordCounter implements Runnable {
    private File file;
    private Map<String, Integer> wordCount;

    public WordCounter(File file, Map<String, Integer> wordCount) {
        this.file = file;
        this.wordCount = wordCount;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("[.,?!\\-\\s]+");
                for (String word : words) {
                    if (word.length() > 1 && word.charAt(0) == word.charAt(word.length() - 1)) {
                        wordCount.compute(word, (key, value) -> (value == null) ? 1 : value + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

