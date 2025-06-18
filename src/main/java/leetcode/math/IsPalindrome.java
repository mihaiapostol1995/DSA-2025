package leetcode.math;

class IsPalindrome {
    public static void main(String[] args) {
        isPalindrome(1214);
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        if (x % 10 == 0) {
            return false;
        }

        int copyX = x;
        int newNum = 0;
        while (copyX != 0) {
            int digit = copyX % 10;
            newNum = newNum * 10 + digit;
            copyX = (copyX - digit) / 10;
        }

        return newNum == x;
    }
}
