package leetcode.math;

class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        if (c < 0) {
            return false;
        }
        if (c == 0 || c == 1) {
            return true;
        }

        int limit = (int) Math.sqrt(c);
        for (int i = 1; i <= limit; i++) {
            var multiply = i * i;
            if (isPerfectSquare(c - multiply)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
