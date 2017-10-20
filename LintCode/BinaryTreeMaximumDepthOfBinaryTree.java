/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path
from the root node down to the farthest leaf node.

Example

Given a binary tree as follow:

  1
 / \
2   3
   / \
  4   5
The maximum depth is 3.

*/

public class BinaryTreeMaximumDepthOfBinaryTree {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int  leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        return Math.max(leftMaxDepth, rightMaxDepth)+1;
    }
}
