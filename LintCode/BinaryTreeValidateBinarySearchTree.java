/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
A single node tree is a BST

An example:
  2
 / \
1   4
   / \
  3   5

The above binary tree is serialized as {2,1,4,#,#,3,5} (in level order).
*/
public class BinaryTreeValidateBinarySearchTree {

    private class ResultType {
        boolean isBST;
        int maxValue, minValue;

        ResultType(boolean isBST, int minValue, int maxValue) {
            this.isBST    = isBST;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }
    /*
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        ResultType r = validateHelper(root);
        return r.isBST;
    }
    private ResultType validateHelper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        ResultType  left = validateHelper(root.left);
        ResultType right = validateHelper(root.right);

        if(!left.isBST || !right.isBST) {
            // if isBST is false then minValue and maxValue are useless
            return new ResultType(false, 0, 0);
        }

        if(root.left  != null &&  left.maxValue >= root.val
        || root.right != null && right.minValue <= root.val)
        {
            return new ResultType(false, 0, 0);
        }

        return new ResultType(  true,
                                Math.min(root.val,  left.minValue),
                                Math.max(root.val, right.maxValue));
    }


//    public boolean isValidBST(TreeNode root) {
//        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//
//    private boolean helper(TreeNode root, long min, long max){
//        if (root == null){
//            return true;
//        }
//        if (root.val <= min || root.val >= max){
//            return false;
//        }
//        return helper(root.left,  min,                     Math.min(max, root.val))
//            && helper(root.right, Math.max(min, root.val), max);
//    }
}
