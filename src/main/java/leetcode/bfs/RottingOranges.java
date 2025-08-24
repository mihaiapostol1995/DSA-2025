package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

class RottingOranges {
    public static void main(String[] args) {

    }

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>(); // indexes + time
        int fresh = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int time = 0;
        while (!queue.isEmpty()) {
            var cur =  queue.poll();

            time = cur[2];
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] dir : dirs) {
                int nextX = dir[0] + cur[0];
                int nextY = dir[1] + cur[1];
                if (grid[nextX][nextY] == 1) {
                    fresh--;
                    queue.offer(new int[]{nextX, nextY, time + 1});
                }
            }
        }

        return fresh > 0 ? -1 : time;
    }
}
