package parallelStreamExample15;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelStreamExample {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Послідовне виконання
        long startTimeSequential = System.nanoTime();
        Map<Integer, Integer> resultSequential = findEvenNumbersSequential(numbers);
        long endTimeSequential = System.nanoTime();
        long sequentialTime = endTimeSequential - startTimeSequential;

        // Паралельне виконання
        long startTimeParallel = System.nanoTime();
        Map<Integer, Integer> resultParallel = findEvenNumbersParallel(numbers);
        long endTimeParallel = System.nanoTime();
        long parallelTime = endTimeParallel - startTimeParallel;

        System.out.println("Sequential time: " + sequentialTime + " nanoseconds");
        System.out.println("Parallel time: " + parallelTime + " nanoseconds");
        System.out.println("Sequential execution result: " + resultSequential);
        System.out.println("Parallel execution result: " + resultParallel);
 }

    private static Map<Integer, Integer> findEvenNumbersSequential(int[] numbers) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int num : numbers) {
            if (num % 2 == 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        return map;
    }

    private static Map<Integer, Integer> findEvenNumbersParallel(int[] numbers) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ParallelTask task = new ParallelTask(numbers, 0, numbers.length);
        forkJoinPool.invoke(task);
        return task.getResult();
    }

    static class ParallelTask extends RecursiveAction {
        private static final int THRESHOLD = 1000;
        private int[] numbers;
        private int start;
        private int end;
        private Map<Integer, Integer> result;

        public ParallelTask(int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
            this.result = new ConcurrentHashMap<>();
        }

        public Map<Integer, Integer> getResult() {
            return result;
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    if (numbers[i] % 2 == 0) {
                        result.put(numbers[i], result.getOrDefault(numbers[i], 0) + 1);
                    }
                }
                } else {
                int mid = start + (end - start) / 2;
                ParallelTask leftTask = new ParallelTask(numbers, start, mid);
                ParallelTask rightTask = new ParallelTask(numbers, mid, end);
                invokeAll(leftTask, rightTask);
                mergeResults(leftTask.getResult(), rightTask.getResult());
                }
                }
        private void mergeResults(Map<Integer, Integer> leftResult, Map<Integer, Integer> rightResult) {
            for (Map.Entry<Integer, Integer> entry : leftResult.entrySet()) {
                result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
            for (Map.Entry<Integer, Integer> entry : rightResult.entrySet()) {
                result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }
    }
}
