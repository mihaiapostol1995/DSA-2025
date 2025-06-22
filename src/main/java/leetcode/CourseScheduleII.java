package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class CourseScheduleII {

    public static void main(String[] args) {
        canFinish(4, new int[][]{
                {0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}});

//        canFinish(3, new int[][]{
//                {0, 1}, {1, 2}});
    }

    public static int[] canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            indegree[i] = 0;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int prereq = prerequisite[1];
            List<Integer> courses = graph.get(prereq);
            int course = prerequisite[0];
            courses.add(course);
            indegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int[] result = new int[numCourses];
        int processed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[processed++] = course;

            List<Integer> courses = graph.get(course);
            for (Integer i : courses) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }
        }

        return processed == numCourses ? result : new int[0];
    }
}
