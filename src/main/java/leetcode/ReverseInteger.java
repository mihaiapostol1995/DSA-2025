package leetcode;

class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverse2(-123));
    }

    public static int reverse(int x) {
        String s = String.valueOf(x);
        char[] chars = s.toCharArray();
        char[] reversed = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            reversed[i] = chars[chars.length - 1 - i];
        }
        var res = new String(reversed);
        if (res.contains("-")) {
            res = "-" + res.substring(0, res.length() - 1);
        }
        return Integer.parseInt(res);
    }

    public static int reverse2(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x = x / 10;

            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE /10 && digit > 7)) {
                return 0;
            } else if (result < Integer.MIN_VALUE / 10
                    || (result == Integer.MIN_VALUE /10 && digit < -8)) {
                return 0;
            }


            result = result * 10 + digit;
        }
        return result;
    }
}
