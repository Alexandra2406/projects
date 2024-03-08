package org.example;

public class QuickSort {

    static void exchange(int[] data, int leftIndex, int rightIndex) {
        int temp = data[leftIndex];
        data[leftIndex] = data[rightIndex];
        data[rightIndex] = temp;
    }

    static int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (data[j] <= pivot) {
                i++;
                exchange(data, i, j);
            }
        }

        exchange(data, i + 1, high);
        return i + 1;
    }

    static void quickSort(int[] data, int low, int high) {
       if (low < high) {
            int pi = partition(data, low, high);
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }

}
