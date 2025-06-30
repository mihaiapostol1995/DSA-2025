package leetcode.dfs.tree;

import java.util.Stack;

class VerifyPreorderSerializationOfABinaryTree {
    public static void main(String[] args) {
        var test = new VerifyPreorderSerializationOfABinaryTree();
        test.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
    }

    public boolean isValidSerialization(String preorder) {
        // Root ->> Left -> Right
        /*
        Stack-based approach:
        - Push non-null nodes onto stack
        - When we see a null ('#'), try to collapse nodes
        - A node can be collapsed when both its children are processed
        - Valid tree ends with empty stack after processing all nodes
        */

        String[] nodes = preorder.split(",");
        Stack<String> stack = new Stack<>();

        for (String node : nodes) {
            stack.push(node);

            // Try to collapse: if top 3 elements are "number # #"
            // we can replace them with a single "#"
            while (stack.size() >= 3 &&
                    stack.get(stack.size() - 1).equals("#") &&
                    stack.get(stack.size() - 2).equals("#") &&
                    !stack.get(stack.size() - 3).equals("#")) {

                // Remove the pattern "node # #"
                stack.pop(); // remove second #
                stack.pop(); // remove first #
                stack.pop(); // remove the node

                // Replace with single #
                stack.push("#");
            }
        }

        // Valid serialization should result in exactly one "#"
        return stack.size() == 1 && stack.peek().equals("#");
    }
}
