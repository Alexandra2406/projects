package org.example;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        long start1 = System.nanoTime();

        BigInteger totalSum = BigInteger.ZERO;
        List<Integer> data = new ArrayList<>();

        int bestStartIndexAscending = 0, bestStartIndexDescending = 0;
        int currentStartIndexAscending = 0, currentStartIndexDescending = 0;
        int bestLengthAscending = 1, bestLengthDescending = 1;
        int currentLengthAscending = 1, currentLengthDescending = 1;
        int numberOfElements = 0, previousValue;

        try (Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\projects\\java\\BecomeADeveloper2024\\src\\10m.txt"))) {
            previousValue = scanner.nextInt();
            data.add(previousValue);
            while (scanner.hasNextInt()) {
                int currentValue = scanner.nextInt();
                data.add(currentValue);
                //calculate sum for arithmetic mean
                totalSum = totalSum.add(BigInteger.valueOf(currentValue));

                if (currentValue > previousValue) {
                    currentLengthAscending++;
                    if (currentLengthAscending > bestLengthAscending) {
                        bestStartIndexAscending = currentStartIndexAscending;
                        bestLengthAscending = currentLengthAscending;
                    }
                } else {
                    currentStartIndexAscending = numberOfElements + 1;
                    currentLengthAscending = 1;
                }
                if (currentValue < previousValue) {
                    currentLengthDescending++;
                    if (currentLengthDescending > bestLengthDescending) {
                        bestStartIndexDescending = currentStartIndexDescending;
                        bestLengthDescending = currentLengthDescending;
                    }
                } else {
                    currentStartIndexDescending = numberOfElements + 1;
                    currentLengthDescending = 1;
                }
                numberOfElements++;
                previousValue = currentValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Largest ascending sequence: ");
        for (int i = bestStartIndexAscending; i < bestStartIndexAscending + bestLengthAscending; i++) {
            System.out.print(data.get(i) + " ");
        }
        System.out.println("\n");

        System.out.println("Largest descending sequence: ");
        for (int i = bestStartIndexDescending; i < bestStartIndexDescending + bestLengthDescending; i++) {
            System.out.print(data.get(i) + " ");
        }
        System.out.println("\n");

        int[] sortedData = data.stream().mapToInt(i -> i).toArray();
        int dataLength = sortedData.length;
        QuickSort.quickSort(sortedData, 0, dataLength - 1);

        double middleValue;
        if (dataLength % 2 == 0) {
            middleValue = (sortedData[dataLength / 2] * 0.5 + sortedData[dataLength / 2 - 1] * 0.5);
        } else {
            middleValue = sortedData[dataLength / 2];
        }

        System.out.println("Median: " + middleValue);
        System.out.println("Maximum number: " + sortedData[dataLength - 1]);
        System.out.println("Minimum number: " + sortedData[0]);
        System.out.println("Arithmetic mean: " + totalSum.divide(BigInteger.valueOf(data.size())));

        long end1 = System.nanoTime();
        System.out.println();
        System.out.println("Elapsed Time in seconds: "+ TimeUnit.SECONDS.convert(end1 - start1, TimeUnit.NANOSECONDS));
    }
}