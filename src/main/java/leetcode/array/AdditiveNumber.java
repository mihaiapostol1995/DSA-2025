package leetcode.array;

class AdditiveNumber {

    public static void main(String[] args) {
        AdditiveNumber additiveNumber = new AdditiveNumber();
        additiveNumber.isAdditiveNumber("112358");
    }

    public boolean isAdditiveNumber(String num) {
        int half = num.length() / 2;
        for (int i = 1; i <= half; i++) {
            // LENGTH, not POSITIONS
            for (int j = 1; j <= half; j++) {
                if (i + j >= num.length()) continue;

                String firstSubstring = num.substring(0, i);
                String secondSubstring = num.substring(i, i + j);

                if ((firstSubstring.startsWith("0") && firstSubstring.length() > 1) ||
                        (secondSubstring.startsWith("0") && secondSubstring.length() > 1)) {
                    continue;
                }

                long firstNumber = Long.parseLong(firstSubstring);
                long secondNumber = Long.parseLong(secondSubstring);

                String s = String.valueOf(firstNumber + secondNumber);
                int k = i + j;

                while (k + s.length() <= num.length()
                        && s.equals(num.substring(k, k + s.length()))) {
                    k = k + s.length();
                    if (k == num.length()) {
                        return true;
                    }
                    firstNumber = secondNumber;
                    secondNumber = Long.parseLong(s);

                    s = String.valueOf(firstNumber + secondNumber);
                }
            }
        }
        return false;
    }
}
