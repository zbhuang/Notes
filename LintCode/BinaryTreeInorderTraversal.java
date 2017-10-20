import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    private TreeNode root;

    public BinaryTreeInorderTraversal() {
        TreeNode node01 = new TreeNode(1);
        TreeNode node02 = new TreeNode(2);
        TreeNode node03 = new TreeNode(3);
        TreeNode node04 = new TreeNode(4);
        TreeNode node05 = new TreeNode(5);
        TreeNode node06 = new TreeNode(6);
        TreeNode node07 = new TreeNode(7);
        node02.left = node01;
        node02.right= node03;
        node06.left = node05;
        node06.right= node07;
        node04.left = node02;
        node04.right= node06;

        this.root = node04;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    //version01: Recursive
    public void inorderTraversal(TreeNode node) {
        if(node != null) {
            this.inorderTraversal(node.left);
            System.out.println(node.val);
            this.inorderTraversal(node.right);
        }
    }
    //version02: NonRecursive
    public List<Integer> inorderTraversalIterative(TreeNode node) {

        List<Integer> inorder = new ArrayList<>();
        if(node == null) {
            return inorder;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curt = node;
        stack.push(curt);
        while(curt != null || !stack.empty()) {
            while(curt != null) {
                stack.push(curt);
                curt = curt.left;
            }
            if(!stack.empty()) {
                curt = stack.pop();

                //System.out.println(curt.val);
                inorder.add(curt.val);

                curt = curt.right;
            }
        }

        return inorder;
    }

    public static void main(String[] args) {
        BinaryTreeInorderTraversal traversal = new BinaryTreeInorderTraversal();
        TreeNode root = traversal.getRoot();
//        traversal.inorderTraversal(root);

        List<Integer> inorder = traversal.inorderTraversalIterative(root);
        System.out.println(inorder);
    }
}
