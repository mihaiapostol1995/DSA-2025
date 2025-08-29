package leetcode.stack.monotonic;

import java.util.Stack;

class CountSubmatricesWithAllOnes {
    public static void main(String[] args) {

    }

    public int numSubmat(int[][] mat) {
        int ans = 0;
        int[] heights = new int[mat.length];

        for (int i = 0; i < mat.length; i++) {
            Stack<Integer> stack = new Stack<>();

            int[] count = new int[mat[0].length];
            for (int j = 0; j < mat[0].length; j++) {
                // calculate the bar of the histogram
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;

                while (!stack.isEmpty()
                        && mat[i][j] <= heights[stack.peek()]) {
                    stack.pop();
                }

                // no, too complicated, do the DP solution
                if (!stack.isEmpty()) {
                    var lastEl = stack.peek();
                    int width = j - lastEl;
                    count[j] = width * heights[lastEl];
                } else {
                    count[j] = heights[j];
                }

                ans += count[j];
                stack.push(j);
            }
        }
        return ans;
    }
}
