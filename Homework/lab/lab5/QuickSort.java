/**
 * QuickSort method
 * @Author Junrong Huang
 */
import java.util.*;

public class QuickSort {
    /**
     * Main function for testing args
     */

    public static void main(String args[]) {
        QuickSort sorter = new QuickSort();
        int[] X = {12, 42, 35, 72, 89, 34, 77, 56, 98, 14, 2, 6, 8, 4, 11, 10};
        System.out.println(Arrays.toString(X));
        sorter.quickSort(X);
        System.out.println(Arrays.toString(X));
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        int leftPointer = left - 1;
        int rightPointer = right;
        while (true) {
            while (arr[++leftPointer] < pivot);
                // leftPointer++;
            while (rightPointer > 0 && arr[--rightPointer] > pivot);
                // rightPointer--;
            if (leftPointer >= rightPointer) {
                break;
            } else {
                swap(arr, leftPointer, rightPointer);
            }
        }
        swap(arr, leftPointer, right);
        return leftPointer;
    }


    /**
     * support function for swap index in an array
     */
    private void swap(int[] arr, int indx1, int indx2) {
        int num = arr[indx1];
        arr[indx1] = arr[indx2];
        arr[indx2] = num;
    }

    // recursive helper method

    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] unsorted, int left, int right) {
        // base case
        if (right <= left) {
            return;
        }
        // last value is the pivot value
        int pivot = unsorted[right];
        int partition = partition(unsorted, left, right, pivot);
        quickSort(unsorted, left, partition - 1);
        quickSort(unsorted, partition + 1, right);
    }
}

