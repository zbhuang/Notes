/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.

Notice
You may assume no duplicate exists in the array.
Given [4, 5, 6, 7, 0, 1, 2] return 0
 */

public class FindMinimumInRotatedSortedArray {
    /*
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int start=0, end=nums.length-1;
        while(start+1 < end) {
            int mid = start+(end-start)/2;
            if(nums[mid] >= nums[end]) {
                start=mid;
            } else {
                end=mid;
            }
        }
        if(nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}
