
/*
Given a list of numbers, return all possible permutations.

Notice

You can assume that there is no duplicate numbers in the list.

Have you met this question in a real interview? Yes
Example
For nums = [1,2,3], the permutations are:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

import java.util.*;

public class Permutation {
    /*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> results = new ArrayList<>();
        if(nums == null) {
            return results;
        }

        if(nums.length == 0) {
            results.add(new ArrayList<>());
            return results;
        }

        //Arrays.sort(nums);
        List<Integer> permutation = new ArrayList<Integer>();
        Set<Integer> sets = new HashSet<>();
        permuteHelper(results, permutation, nums, sets);

        return results;
    }

    private void permuteHelper(List<List<Integer>> result,
                               List<Integer> perm,
                               int[] nums,
                               Set<Integer> sets) {
        if(perm.size() == nums.length) {
            result.add(new ArrayList<Integer>(perm));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if (sets.contains(nums[i])) {
                continue;
            }

            perm.add(nums[i]);
            sets.add(nums[i]);

            permuteHelper(result, perm, nums, sets);

            sets.remove(nums[i]);
            perm.remove(perm.size()-1);
        }
    }

    public static void main(String[] args) {
        Permutation solution = new Permutation();
        int [] nums = {1, 2, 3};

        List<List<Integer>> results = solution.permute(nums);

        System.out.println(results);

    }
}
