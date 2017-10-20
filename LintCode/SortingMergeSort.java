import java.util.Arrays;
import java.util.stream.IntStream;

/*
http://lintcode.com/en/problem/sort-integers-ii/

Given an integer array, sort it in ascending order.
Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

Example

Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
 */
public class SortingMergeSort {
    /*
     * @param A: an integer array
     * @return:
     */
    private void merge(int[] array, int start, int mid, int end) {
        int[] tmpArray = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (array[i] < array[j]) {
                tmpArray[k++] = array[i++];
            } else {
                tmpArray[k++] = array[j++];
            }
        }
        while (i <= mid) {
            tmpArray[k++] = array[i++];
        }
        while (j <= end) {
            tmpArray[k++] = array[j++];
        }

        for (i = 0; i < k; i++) {
            array[start + i] = tmpArray[i];
        }
    }
    private void mergeSort(int [] array, int start, int end) {

        //note: here (start >= end), NOT (start > end)
        //      otherwise, it will go into infinite loop then stack overflow ...
        if(start >= end) {
            return;
        }

        int mid = start + (end-start)/2;

        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);

        merge(array, start, mid, end);
    }
    public void sortIntegers(int[] A) {
        // write your code here

        int size = A.length;
        mergeSort(A, 0, size-1);

        IntStream iStream = Arrays.stream(A);
        iStream.forEach(x -> System.out.println(x));
    }


    public static void main(String[] args) {
        int [] array = {1,9,2,8,3,7,4,6,5};
        SortingMergeSort sort = new SortingMergeSort();
        sort.sortIntegers(array);
    }


}
