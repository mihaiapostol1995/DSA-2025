package leetcode.dfs;

class NumberOfProvinces {
    public static void main(String[] args) {
        var n = new NumberOfProvinces();
        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int circleNum = n.findCircleNum(isConnected);
        System.out.println(circleNum);
    }

    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                count++;
                dfs(isConnected, visited, i);
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        if (i == isConnected.length) {
            return;
        }

        for (int j = 0; j < isConnected.length; j++) {
            if (!visited[j] && isConnected[i][j] == 1) {
                visited[j] = true;
                // simply mark as visited
                dfs(isConnected, visited, j);
            }
        }
    }
}
