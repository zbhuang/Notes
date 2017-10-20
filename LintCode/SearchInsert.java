
/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume NO duplicates in the array.

Have you met this question in a real interview? Yes

Example

[1,3,5,6], 5 -> 2
[1,3,5,6], 2 -> 1
[1,3,5,6], 7 -> 4
[1,3,5,6], 0 -> 0
 */
public class SearchInsert {
    /*
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        if(A.length == 0) {
            return 0;
        }

        int start, end, mid;
        start=0;
        end=A.length-1;
        while(start + 1 < end) {
            mid = start + (end - start) / 2;
            if(A[mid] == target) {
                return mid;
            } else if(A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(A[start] >= target) {
            return start;
        } else if(A[end] >= target) {
            return end;
        } else {
            return end+1;
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        SearchInsert solution = new SearchInsert();
        int index = solution.searchInsert(nums, 5);
        System.out.println("Index="+index);
    }

}
