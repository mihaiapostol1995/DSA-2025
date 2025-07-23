package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BrickWall {
    public static void main(String[] args) {
        var b = new  BrickWall();
        List<List<Integer>> wall = Arrays.asList(
                Arrays.asList(1, 2, 2, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 4),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 1, 1)
        );
//        List<List<Integer>> wall = new ArrayList<>();
//        wall.add(Arrays.asList(1));
//        wall.add(Arrays.asList(1));
//        wall.add(Arrays.asList(1));
        b.leastBricks(wall);
    }

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < wall.size(); i++) {
            var firstList =  wall.get(i);
            int sum = 0;
            for (int j = 0; j < firstList.size() - 1; j++) {
                sum += firstList.get(j);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        if (map.isEmpty()) {
            return wall.size();
        }
        var max = Collections.max(map.values());
        return wall.size() - max;
    }
}
