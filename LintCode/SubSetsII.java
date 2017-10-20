import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a list of numbers that may has duplicate numbers, return all possible subsets

Notice

Each element in a subset must be in non-descending order.
The ordering between two subsets is free.
The solution set must not contain duplicate subsets.
Have you met this question in a real interview? Yes
Example
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */
public class SubSetsII {

    /*
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // write your code here

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
                              ArrayList<Integer> subset,
                              int[] nums,
                              int pos) {
        result.add(new ArrayList<Integer>(subset));

        for(int i=pos; i<nums.length; i++) {

            if(i!=pos && nums[i]==nums[i-1]) {
                continue;
            }

            subset.add(nums[i]);
            subsetHelper(result, subset, nums, i+1);
            subset.remove(subset.size()-1);
        }
    }

    public static void main(String[] args) {
        SubSetsII solution = new SubSetsII();
        int [] nums = {1, 2, 2};

        List<List<Integer>> results = solution.subsetsWithDup(nums);

        System.out.println(results);
    }
}
