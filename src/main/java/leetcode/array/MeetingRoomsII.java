package leetcode.array;

import java.util.Arrays;

class MeetingRoomsII {

    public static void main(String[] args) {
        var meetingRoomsII = new MeetingRoomsII();
        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };
        int i = meetingRoomsII.meetingRooms(intervals);
        System.out.println(i);
    }

    int meetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int firstTime = 0;
        int lastTime = 0;
        int count = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (start[firstTime] < end[lastTime]) {
                count++;
                firstTime++;
            } else {
                lastTime++;
            }
        }
        return count;
    }
}
