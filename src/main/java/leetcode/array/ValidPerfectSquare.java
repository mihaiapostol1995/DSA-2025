package leetcode.array;

class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        int square = (int) Math.sqrt(num);
        return square * square == num;
    }
}
