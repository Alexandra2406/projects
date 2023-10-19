package parallelStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ParallelStreamExample {
    public static void main(String[] args) throws IOException {
        String directoryPath = "files";

        List<Path> filePaths = getFilePaths(directoryPath);

        // Sequential Approach
        long sequentialStartTime = System.currentTimeMillis();
        long totalWordCount = calculateTotalWordCount(filePaths);
        double averageWordLength = calculateAverageWordLength(filePaths, totalWordCount);
        long sequentialEndTime = System.currentTimeMillis();
        long sequentialExecutionTime = sequentialEndTime - sequentialStartTime;

        // Parallel Approach
        long parallelStartTime = System.currentTimeMillis();
        long totalWordCountParallel = calculateTotalWordCountParallel(filePaths);
        double averageWordLengthParallel = calculateAverageWordLengthParallel(filePaths, totalWordCountParallel);
        long parallelEndTime = System.currentTimeMillis();
        long parallelExecutionTime = parallelEndTime - parallelStartTime;

        System.out.println("Sequential Total Word Count: " + totalWordCount);
        System.out.println("Sequential Average Word Length: " + averageWordLength);
        System.out.println("Sequential Execution Time: " + sequentialExecutionTime + "ms");

        System.out.println("Parallel Total Word Count: " + totalWordCountParallel);
        System.out.println("Parallel Average Word Length: " + averageWordLengthParallel);
        System.out.println("Parallel Execution Time: " + parallelExecutionTime + "ms");
    }

    private static List<Path> getFilePaths(String directoryPath) throws IOException {
        List<Path> filePaths = new ArrayList<>();

        try (var paths = Files.walk(Paths.get(directoryPath))) {
            paths.filter(Files::isRegularFile).forEach(filePaths::add);
        }

        return filePaths;
    }

    private static long calculateTotalWordCount(List<Path> filePaths) {
        AtomicLong totalWordCount = new AtomicLong(0);

        for (Path filePath : filePaths) {
            WordCountTask task = new WordCountTask(filePath, totalWordCount);
            task.run();
        }

        return totalWordCount.get();
    }

    private static double calculateAverageWordLength(List<Path> filePaths, long totalWordCount) {
        AtomicLong totalWordLength = new AtomicLong(0);

        for (Path filePath : filePaths) {
            WordLengthTask task = new WordLengthTask(filePath, totalWordLength);
            task.run();
        }

        return (double) totalWordLength.get() / totalWordCount;
    }

    private static long calculateTotalWordCountParallel(List<Path> filePaths) {
        AtomicLong totalWordCount = new AtomicLong(0);

        List<Thread> threads = new ArrayList<>();

        for (Path filePath : filePaths) {
            WordCountTask task = new WordCountTask(filePath, totalWordCount);
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        // Очікуємо завершення всіх потоків
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return totalWordCount.get();
    }

    private static double calculateAverageWordLengthParallel(List<Path> filePaths, long totalWordCount) {
        AtomicLong totalWordLength = new AtomicLong(0);

        List<Thread> threads = new ArrayList<>();

        for (Path filePath : filePaths) {
            WordLengthTask task = new WordLengthTask(filePath, totalWordLength);
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        // Очікуємо завершення всіх потоків
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return (double) totalWordLength.get() / totalWordCount;
    }

    private static class WordCountTask implements Runnable {
        private Path filePath;
        private AtomicLong totalWordCount;

        public WordCountTask(Path filePath, AtomicLong totalWordCount) {
            this.filePath = filePath;
            this.totalWordCount = totalWordCount;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                long wordCount = reader.lines()
                        .flatMap(line -> Stream.of(line.split("\\s+")))
                        .count();
                totalWordCount.addAndGet(wordCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class WordLengthTask implements Runnable {
        private Path filePath;
        private AtomicLong totalWordLength;

        public WordLengthTask(Path filePath, AtomicLong totalWordLength) {
            this.filePath = filePath;
            this.totalWordLength = totalWordLength;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                long wordLength = reader.lines()
                        .flatMap(line -> Stream.of(line.split("\\s+")))
                        .mapToLong(String::length)
                        .sum();
                totalWordLength.addAndGet(wordLength);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
