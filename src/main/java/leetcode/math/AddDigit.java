package leetcode.math;

class AddDigit {
    public static void main(String[] args) {
        AddDigit addDigit = new AddDigit();
        System.out.println(addDigit.addDigits(38));
    }

    public int addDigits(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum < 10 ? sum : addDigits(sum);
    }
}
