package leetcode.dfs.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class FindDuplicateSubtrees {

    public static void main(String[] args) {
        Integer[] values = {1,2,3,4,null,2,4,null,null,4};
        TreeNode root = buildTree(values);

        var f = new FindDuplicateSubtrees();
        f.findDuplicateSubtrees(root);
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        findDuplicateSubtrees(root, map);
        return map.values()
                .stream()
                .filter(treeNodes -> treeNodes.size() > 1)
                .map(List::getFirst)
                .toList();
    }

    public void findDuplicateSubtrees(TreeNode root, Map<String, List<TreeNode>> map) {
        if (root == null) {
            return;
        }
        findDuplicateSubtrees(root.left, map);
        var path = exploreTree(root, new StringBuilder());
        map.computeIfAbsent(path.toString(), k -> new ArrayList<>()).add(root);
        findDuplicateSubtrees(root.right, map);
    }

    private StringBuilder exploreTree(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return sb;
        }

        exploreTree(root.left, sb.append("l"));
        sb.append(root.val);
        exploreTree(root.right, sb.append("r"));

        return sb;
    }


    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < values.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.offer(current.left);
            }
            i++;

            // Right child
            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }
}
