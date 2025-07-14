package leetcode.dfs.tree;

class DeleteNodeInABST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            var left = root.left;
            var right = root.right;
            if (left == null && right != null) {
                // YOU MUST PRESERVE THE STRUCTURE! but how can i get the parent for this?
                // you just return the opposite value that is not null..
                root.val = right.val;
                root.right = null;
            } else if (left != null && right == null) {
                root.val = left.val;
                root.left = null;
            } else {
                // smallest on the right branch, which is the SMALLEST bigger than this one
                var inorder = inorder(root, root.right);
                root.val = inorder.val;
            }
        }

        if (root.val > key) {
            // this is wrong, because it should be like in binary search
            root.right = deleteNode(root.right, key);
        } else if (root.val < key) {
            root.left = deleteNode(root.left, key);
        }

        return root;
    }

    TreeNode inorder(TreeNode parent, TreeNode child) {
        if (child.left == null) {
            parent.left = null;
            return child;
        }
        return inorder(child, child.left);
    }
}
