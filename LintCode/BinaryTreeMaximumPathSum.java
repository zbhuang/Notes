/*
http://lintcode.com/en/problem/binary-tree-maximum-path-sum/

Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

Example

Given the below binary tree:
  1
 / \
2   3

return 6.
 */
public class BinaryTreeMaximumPathSum {
    /*
     * @param root: The root of binary tree.
     * @return: An integer
     */
    private class ResultType {
        // singlePath: 从root往下走到任意点的最大路径，这条路径可以不包含任何点
        //    maxPath: 从树中任意到任意点的最大路径，这条路径至少包含一个点
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root) {
        if(root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }

        //divide
        ResultType  left = helper(root.left);
        ResultType right = helper(root.right);

        //conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);

        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }

    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
}
