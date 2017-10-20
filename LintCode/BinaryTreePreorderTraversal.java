import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example

Given:

    1
   / \
  2   3
 / \
4   5

return [1,2,4,5,3].
 */
class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
public class BinaryTreePreorderTraversal {
    /*
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    //version01: traverse recursively
    public List<Integer> preorderTraversalIterative(TreeNode root) {
        // write your code here
        List<Integer> preorder = new ArrayList<>();
        traverse(root, preorder);
        return preorder;
    }
    private void traverse(TreeNode root, List<Integer> order) {
        if(root == null) {
            return;
        }
        order.add(root.val);
        traverse(root.left,  order);
        traverse(root.right, order);
    }

    //version02: Divide & Conquer
    public List<Integer> preorderTraversalDivideConquer(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if(root == null) {
            return preorder;
        }

        //divide
        List<Integer> left = preorderTraversalDivideConquer(root.left);
        List<Integer> right= preorderTraversalDivideConquer(root.right);

        //conquer
        preorder.add(root.val);
        preorder.addAll(left);
        preorder.addAll(right);

        return preorder;
    }

    //version03: NonRecursive
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if(root == null) {
            return preorder;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if(node.right != null) {
                stack.push(node.right);
            }
            if(node.left != null) {
                stack.push(node.left);
            }
        }

        return preorder;
    }

    public static void main(String[] args) {
        TreeNode node01 = new TreeNode(1);
        TreeNode node02 = new TreeNode(2);
        TreeNode node03 = new TreeNode(3);
        TreeNode node04 = new TreeNode(4);
        TreeNode node05 = new TreeNode(5);
        node01.left = node02;
        node01.right= node03;
        node02.left = node04;
        node02.right= node05;

        BinaryTreePreorderTraversal solution = new BinaryTreePreorderTraversal();

        //List<Integer> order = solution.preorderTraversalIterative(node01);
        List<Integer> order = solution.preorderTraversalDivideConquer(node01);

        System.out.println("Preorder:"+order);
    }
}
