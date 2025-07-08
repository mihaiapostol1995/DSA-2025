package leetcode.string;

import java.util.Stack;

class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        LongestAbsoluteFilePath l = new LongestAbsoluteFilePath();
        int i = l.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
        System.out.println(i);
    }

    public int lengthLongestPath(String input) {
        String[] paths = input.split("\n");
        int maxLen = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < paths.length; i++) {
            var line = paths[i];
            int depth = 0;
            while (depth < line.length() && line.charAt(depth) == '\t') {
                depth++;
            }
            String name = line.substring(depth);

            var stackCount = stack.size();
            while (stackCount > depth) {
                stack.pop();
                stackCount = stack.size();
            }
            // pop BEFORE doing the addition
            int currLen = (stack.isEmpty() ? 0 : stack.peek()) + name.length() + 1; // +1 for '/'
            if (line.contains(".")) {
                maxLen = Math.max(currLen, maxLen);
                continue;
            }
            stack.push(currLen) ;
        }
        return maxLen;
    }
}
