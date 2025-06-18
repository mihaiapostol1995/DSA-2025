package leetcode.codility;

class LargestSumOfTwoDigitFragments {

    public static void main(String[] args) {
        solution("00331");
    }

    public static int solution(String S) {
        if (S.length() < 4) return 0;

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < S.length(); i++) {
            if (i + 2 > S.length()) {
                break;
            }
            var firstTwoDigits = S.substring(i, i + 2);
            var firstNumber = parseFirsNumber(S, firstTwoDigits, i);

            for (int j = i + 2; j < S.length(); j++) {
                if (j + 2 > S.length()) {
                    break;
                }
                var secondNumber = parseFirsNumber(S, S.substring(j, j + 2), j);
                max = Math.max(max, firstNumber + secondNumber);
            }
        }
        return max;
    }

    private static int parseFirsNumber(String S, String firstTwoDigits, int i) {
        int firstNumber;
        if (firstTwoDigits.equals("00")) {
            firstNumber = 0;
        } else if (firstTwoDigits.startsWith("0")) {
            firstNumber = Integer.parseInt(S.substring(i, i + 1));
        } else {
            firstNumber = Integer.parseInt(firstTwoDigits);
        }
        return firstNumber;
    }
}
