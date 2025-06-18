package leetcode;

class Zigzag {
    public static void main(String[] args) {
        System.out.println(convert2("ABCD", 2));
    }

    // failing
    public static String convert1(String s, int numRows) {
        char [][] matrix = new char[numRows][s.length()];
        int j = 0;
        int characterCount = 0;
        boolean up = true;
        for (int i = 0; i < s.length(); i++) {
            if (up) {
                for (; j < numRows; j++) {
                    if (characterCount == s.length()) {
                        break;
                    }
                    matrix[j][i] = s.charAt(characterCount);
                    characterCount++;
                }
                up = false;
                j--;
            } else {
                if (characterCount == s.length()) {
                    break;
                }
                j--;
                matrix[j][i] = s.charAt(characterCount);
                characterCount++;
                if (j <= 1) {
                    up = true;
                    j = 0;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (j = 0; j < s.length(); j++) {
                if (matrix[i][j] != '\u0000') {
                    sb.append(matrix[i][j]);
                }
            }
        }
        return sb.toString();
    }

    public static String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }

        boolean goingUp = true;
        int count = 0;
        for (var c : s.toCharArray()) {
            if (!goingUp) {
                var builder = builders[count];
                builder.append(c);
                count--;
                if (count < 0) {
                    goingUp = true;
                    count = 1;
                }
            } else {
                var builder = builders[count];
                builder.append(c);
                count++;
                if (count == numRows) {
                    goingUp = false;
                    count = numRows - 2;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var c : builders) {
            sb.append(c);
        }
        return sb.toString();
    }
}
