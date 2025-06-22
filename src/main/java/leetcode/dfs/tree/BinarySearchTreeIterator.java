package leetcode.dfs.tree;

import java.util.Stack;

public class BinarySearchTreeIterator {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator bstIterator = new BSTIterator(root);
        bstIterator.next();
        bstIterator.next();
        bstIterator.hasNext();
        bstIterator.next();
        bstIterator.hasNext();
        bstIterator.next();
        bstIterator.hasNext();
        bstIterator.next();
        bstIterator.hasNext();
    }

}

class BSTIterator {

    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode pop = stack.pop();

        var temp = pop.right;
        if (temp != null) {
            stack.push(temp);

            temp = temp.left;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
        }
        return pop.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
