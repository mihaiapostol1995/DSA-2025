package leetcode.math;

class UglyNumber {
    public static void main(String[] args) {
        UglyNumber uglyNumber = new UglyNumber();
        boolean ugly = uglyNumber.isUgly(-2147483648);
        System.out.println(ugly);
    }

    public boolean isUgly(long n) {
        n = Math.abs(n);
        while (n > 1) {
            if (n % 2 == 0) {
                n /= 2;
            } else if (n % 3 == 0) {
                n /= 3;
            } else if (n % 5 == 0) {
                n /= 5;
            } else return false;
        }
        return true;
    }
}
