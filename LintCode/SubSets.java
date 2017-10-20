import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a set of distinct integers, return all possible subsets.

Notice

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
Have you met this question in a real interview? Yes
Example
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */
public class SubSets {

    /*
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets_nonrecursive(int[] nums) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        // 1 << n is 2^n
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // ..
        // 7 -> 111 -> [1,2,3]
        int total = (1<<n);
        for (int i=0; i<total; i++) {

            List<Integer> subset = new ArrayList<Integer>();

            for (int j=0; j<n; j++) {

                // check whether the jth digit in i's binary representation is 1
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            System.out.println(subset);
            results.add(subset);
        }

        return results;
    }

    public List<List<Integer>> subsets_recursive(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();
        if(nums == null) {
            return results;
        }

        if(nums.length == 0) {
            results.add(new ArrayList<>());
            return results;
        }

        Arrays.sort(nums);
        subsetHelper(results, new ArrayList<>(), nums, 0);

        return results;
    }

    private void subsetHelper(List<List<Integer>> result,
                              ArrayList<Integer>  subset,
                              int[] nums,
                              int pos) {
        result.add(new ArrayList<>(subset));

        for(int i=pos; i<nums.length; i++) {
            subset.add(nums[i]);
            subsetHelper(result, subset, nums, i+1);
            subset.remove(subset.size()-1);
        }
    }

    public static void main(String[] args) {
        SubSets solution = new SubSets();
        int [] nums = {1, 2, 3};

        //List<List<Integer>> results = solution.subsets_recursive(nums);
        List<List<Integer>> results = solution.subsets_nonrecursive(nums);

        System.out.println(results);
    }
}
