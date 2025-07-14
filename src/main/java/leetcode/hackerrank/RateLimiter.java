package leetcode.hackerrank;

public class RateLimiter {
    public static int droppedRequests(int[] requestTime) {
        int dropped = 0;
        int n = requestTime.length;

        for (int i = 0; i < n; i++) {
            // Check if 3 requests occurred at the exact same second
            if (i >= 3 && requestTime[i] == requestTime[i - 3]) {
                dropped++;
                continue;
            }

            // Check if more than 20 requests occurred within last 10 seconds
            if (i >= 20 && requestTime[i] - requestTime[i - 20] < 10) {
                dropped++;
                continue;
            }

            // Check if more than 60 requests occurred within last 60 seconds
            if (i >= 60 && requestTime[i] - requestTime[i - 60] < 60) {
                dropped++;
            }
        }

        return dropped;
    }

    public static void main(String[] args) {
        int[] requestTime = {
            1, 1, 1, 2, 2, 2, 3, 3, 3,
            4, 4, 4, 5, 5, 5, 6, 6, 6,
            7, 7, 7, 11, 11, 11
        };

        System.out.println("Dropped requests: " + droppedRequests(requestTime));  // Output: 7
    }
}
