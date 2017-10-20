
/*
Find any position of a target number in a sorted array. Return -1 if target does not exist.

Have you met this question in a real interview? Yes
Example

Given [1, 2, 2, 4, 5, 5].
For target = 2, return 1 or 2.
For target = 5, return 4 or 5.
For target = 6, return -1.
 */

public class BinarySearch {
    /*
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */

    //for target=2, return 2
    //for target=5, return 4
//    public int findPosition(int[] nums, int target) {
//        int size = nums.length;
//        if(size == 0) {
//            return -1;
//        }
//
//        int index = -1;
//        int start=0, end=size-1;
//        while(start < end) {
//            int mid = start + (end-start)/2;
//            if(nums[mid] == target) {
//                index = mid;
//                break;
//            } else if(nums[mid] < target) {
//                start = mid+1;
//            } else {
//                end = mid-1;
//            }
//        }
//        return index;
//    }

    //for target=2, return 1
    //for target=5, return 4
    /**
     * This solution is better because you can identify the first occurrence position
     * no, no, what happens if there are 3 duplicates...
     */
    public int findPosition(int[] nums, int target) {
        int size = nums.length;
        if(size == 0) {
            return -1;
        }

        int start=0, end=size-1, mid;
        while(start + 1 < end) {
            mid = start + (end-start)/2;
            if(nums[mid] == target) {
                end = mid;
            } else if(nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(nums[start] == target) {
            return start;
        }
        if(nums[end] == target) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,2,4,5,5};
        BinarySearch solution = new BinarySearch();
        int index = solution.findPosition(nums, 2);
        System.out.println("Index="+index);
    }
}
/*
If this question is asking the index of the last occurrence of the target,
Then two changes
1)
            if(nums[mid] == target) {
                start = mid;
2)
change the order of checking [start] and [end]
        if(nums[end] == target) {
            return end;
        }
        if(nums[start] == target) {
            return start;
        }

 */
