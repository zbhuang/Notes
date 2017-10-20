import java.util.Arrays;
import java.util.stream.IntStream;

/*

http://lintcode.com/en/problem/sort-integers-ii/

Given an integer array, sort it in ascending order.
Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

Example

Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
 */
public class SortingQuickSort {
    /*
     * @param A: an integer array
     * @return:
     */
    public void sortIntegers2(int[] A) {
        // write your code here

        int size = A.length;
        quickSort(A, 0, size-1);

        IntStream iStream = Arrays.stream(A);
        iStream.forEach(x -> System.out.println(x));
    }
    private void quickSort(int[] array, int start, int end) {
        if(start >= end) {
            return;
        }
        int pivot = partition(array, start, end);
        quickSort(array, start, pivot-1);
        quickSort(array, pivot+1, end);
    }
    private int partition(int[] array, int start, int end) {
        int sentinel = array[end];
        int i=start-1;
        for(int j=start; j<end; j++) {
            if(array[j] < sentinel) {
                swap(array, ++i, j);
            }
        }
        swap(array, i+1, end);
        return i+1;
    }
    private void swap(int[] array, int i, int j) {
        int tmp=array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int [] array = {1,9,2,8,3,7,4,6,5};
        SortingQuickSort sort = new SortingQuickSort();
        sort.sortIntegers2(array);
    }


}
