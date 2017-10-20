import java.util.ArrayList;
import java.util.List;

/*
Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
Find all the keys of tree in range k1 to k2. i.e. print all x such that k1<=x<=k2 and x is a key of given BST.
Return all the keys in ascending order.

Example

If k1 = 10 and k2 = 22, then your function should return [12, 20, 22].
    20
   /  \
  8   22
 / \
4   12
 */
public class BinaryTreeSearchRangeInBinarySearchTree {
    private List<Integer> results;
    /*
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        results = new ArrayList<>();
        helper(root, k1, k2);
        return results;
    }
    private void helper(TreeNode root, int k1, int k2) {
        if(root == null) {
            return;
        }

        if(k1 < root.val) {
            helper(root.left, k1, k2);
        }
        if(k1 <= root.val && root.val <= k2) {
            results.add(root.val);
        }
        if(root.val < k2) {
            helper(root.right, k1, k2);
        }
    }
}
