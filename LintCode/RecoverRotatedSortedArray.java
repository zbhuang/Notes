import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
http://lintcode.com/en/problem/recover-rotated-sorted-array/

Given a rotated sorted array, recover it to sorted array in-place.

Clarification

What is rotated array?

For example, the orginal array is [1,2,3,4],
The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]

[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
*/
public class RecoverRotatedSortedArray {
    /**
     * @param nums: The rotated sorted array
     * @return: The recovered sorted array
     */
    private void reverse(List<Integer> nums, int start, int end) {
        for(int i=start, j=end; i<j; i++,j--) {
            int tmp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, tmp);
        }
    }
    public void recoverRotatedSortedArray(List<Integer> nums) {
        for (int index = 0; index < nums.size() - 1; index++) {
            if (nums.get(index) > nums.get(index + 1)) {
                reverse(nums, 0, index);
                reverse(nums, index + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            }
        }
    }

    public static void main(String[] args) {
        List nums = Arrays.asList(4, 5, 1, 2, 3);
        System.out.println(nums);

        RecoverRotatedSortedArray solution = new RecoverRotatedSortedArray();
        solution.recoverRotatedSortedArray(nums);

        System.out.println("\n"+nums);
    }
}
