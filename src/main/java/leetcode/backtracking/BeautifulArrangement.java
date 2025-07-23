package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

class BeautifulArrangement {

    public static void main(String[] args) {
        var b = new BeautifulArrangement();
        int i = b.countArrangement(2);
        System.out.println(i);
    }

    public int countArrangement(int n) {
        List<Integer> nums = new ArrayList<>();
        nums.add(0);

        boolean[] visited = new boolean[n + 1];
        return dfs(visited, nums);
    }

    private int dfs(boolean[] visited, List<Integer> nums) {
        if (nums.size() == visited.length) {
            return checkValidity(nums);
        }

        int count = 0;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                nums.add(i);

                count += dfs(visited, nums);

                visited[i] = false;
                nums.removeLast();
            }
        }
        return count;
    }

    private int checkValidity(List<Integer> nums) {
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) % i == 0 || i % nums.get(i) == 0) {
                continue;
            } else {
                return 0;
            }
        }
        return 1;
    }
}
