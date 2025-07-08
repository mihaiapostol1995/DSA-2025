package leetcode.math;

class FindNthDigit {
    public static void main(String[] args) {

    }

    public int findNthDigit(int n) {
        if (n <= 0) {
            return -1;
        }

        long start = 1;
        long digitLength = 1;
        long count = 9;

        while (n > count * digitLength) {
            n -= count * digitLength;
            digitLength++;
            start = start * 10;
            count = count * 10;
        }

        long num = start + (n - 1) / digitLength;

        long digitIndex = (n - 1) % digitLength;

        return Character.getNumericValue(String.valueOf(num).charAt((int) digitIndex));
        // AGAIN, the same pattern. divide and modulus. like in the matrix problem
    }

}
