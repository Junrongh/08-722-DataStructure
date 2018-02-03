/**
 * MergeSort method
 * @Author Junrong Huang
 */
import java.util.*;

public class MergeSort {

    public static int[] merge(int[] a, int[] b) {
        // Create a new array
        int[] merged = new int[a.length + b.length];
        int indexA = 0, indexB = 0, indexM = 0;
        while (indexA < a.length && indexB < b.length) {
            if (a[indexA] < b[indexB]) {
                merged[indexM] = a[indexA];
                indexA++;
            } else {
                merged[indexM] = b[indexB];
                indexB++;
            }
            indexM++;
        }

        while (indexM < a.length + b.length) {
            if (indexA == a.length) {
                merged[indexM] = b[indexB];
                indexB++;
                indexM++;
            } else if (indexB == b.length) {
                merged[indexM] = a[indexA];
                indexA++;
                indexM++;
            }
        }
        return merged;
    }

    public static int[] mergeSort(int[] unsorted) {
        // base case
        if (unsorted.length <= 1) return unsorted;

        int mid = unsorted.length / 2;
        // create left array
        int[] left = new int[mid];
        System.arraycopy(unsorted, 0, left, 0, mid);

        // create right array
        int[] right = new int[unsorted.length - mid];
        System.arraycopy(unsorted, mid, right, 0, unsorted.length - mid);

        //call itself with the left/right half
        left = mergeSort(left);
        right = mergeSort(right);

        // merge and return the merged array
        return merge(left, right);
    }
    public static void main(String[] args) {
        int[] A = {12, 42, 63, 89};
        int[] B = {3, 27, 78, 94};
        System.out.println(Arrays.toString(merge(A, B)));
        int[] X = {12, 42, 35, 72, 89, 34, 77, 56, 98, 10, 2, 6, 8, 4, 11, 100};
        System.out.println(Arrays.toString(mergeSort(X)));
    }
}
