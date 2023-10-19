package waitNotify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileProcessingSystem {
    private static final String INPUT_DIRECTORY = "input";
    private static final String OUTPUT_DIRECTORY = "output";

    public static void main(String[] args) {
        File inputDir = new File(INPUT_DIRECTORY);
        File[] files = inputDir.listFiles();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (File file : files) {
            executorService.submit(new FirstThread(file));
        }

        executorService.shutdown();
    }

    private static class FirstThread implements Runnable {
        private final File inputFile;

        public FirstThread(File inputFile) {
            this.inputFile = inputFile;
        }

        @Override
        public void run() {
            try {
                long spaceCount = countSpaces(inputFile);
                Thread secondThread = new Thread(new SecondThread(inputFile, spaceCount));
                secondThread.start();
                secondThread.join();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private long countSpaces(File file) throws IOException {
            String content = new String(Files.readAllBytes(file.toPath()));
            long spaceCount = content.chars().filter(ch -> ch == ' ').count();
            return spaceCount;
        }
    }

    private static class SecondThread implements Runnable {
        private final File inputFile;
        private final long spaceCount;

        public SecondThread(File inputFile, long spaceCount) {
            this.inputFile = inputFile;
            this.spaceCount = spaceCount;
        }

        @Override
        public void run() {
            try {
                String content = new String(Files.readAllBytes(inputFile.toPath()));
                String modifiedContent;

                if (spaceCount % 2 == 0) {
                    modifiedContent = capitalizeFirstLetters(content);
                } else {
                    modifiedContent = capitalizeLastLetters(content);
                }

                String outputFileName = inputFile.getName();
                File outputFile = new File(OUTPUT_DIRECTORY, outputFileName);
                Files.write(outputFile.toPath(), modifiedContent.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String capitalizeFirstLetters(String text) {
            StringBuilder result = new StringBuilder();
            String[] words = text.split("\\s+");

            for (String word : words) {
                if (word.length() > 0) {
                    char firstChar = Character.toUpperCase(word.charAt(0));
                    result.append(firstChar).append(word.substring(1)).append(" ");
                }
            }

            return result.toString().trim();
        }

        private String capitalizeLastLetters(String text) {
            StringBuilder result = new StringBuilder();
            String[] words = text.split("\\s+");

            for (String word : words) {
                if (word.length() > 0) {
                    char lastChar = Character.toUpperCase(word.charAt(word.length() - 1));
                    String processedWord = word.substring(0, word.length() - 1) + lastChar;
                    result.append(processedWord).append(" ");
                }
            }

            return result.toString().trim();
        }

    }
}

