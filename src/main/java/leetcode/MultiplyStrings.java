package leetcode;

class MultiplyStrings {

    public static void main(String[] args) {
//        System.out.println(multiply("999", "999"));
        System.out.println(multiplyGood("999", "99"));
    }

    public static String multiply(String num1, String num2) {
        if (num2.length() > num1.length()) {
            var temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int result = 0;
        int loop = 1;
        for (int i = num2.length() - 1; i >= 0; i-- ) {
            int temp = 0;
            int carry = 0;
            int multipy = 1;
            for (int j = num1.length() - 1; j >= 0; j--) {
                var firstNubmer = num1.charAt(j) - '0';
                var secondNumber = num2.charAt(i) - '0';
                int addition = firstNubmer * secondNumber % 10;

                temp = temp + multipy * addition + carry * multipy;
                carry = firstNubmer * secondNumber / 10;
                multipy = multipy * 10;
            }
            result = result + temp * loop + carry * multipy;
            loop = loop * 10;
        }

        return String.valueOf(result);
    }

    // this is hard problem
    public static String multiplyGood(String num1, String num2) {
        // Handle edge cases
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] result = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int firstNum = num1.charAt(i) - '0';
                int secondNum = num2.charAt(j) - '0';

                int sum = firstNum * secondNum + result[i + j + 1];

                int digit = sum % 10;
                int carry = sum / 10;

                result[i + j + 1] = digit;
                result[i + j] += carry;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            if (i != 0 || result[i] != 0) {
                sb.append(result[i]);
            }
        }

        return sb.toString();
    }
}
