/**
 * Merges two sorted arrays into a new sorted array
 * Once the merge is done, return the merged array
 * @Author Junrong Huang
 */
import java.util.*;
public class merge {

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
    public static void main(String[] args) {
        int[] A = {12, 42, 63, 89};
        int[] B = {3, 27, 78, 94};
        System.out.println(Arrays.toString(merge(A, B)));
    }
}
