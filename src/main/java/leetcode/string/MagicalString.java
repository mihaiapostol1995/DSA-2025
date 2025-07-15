package leetcode.string;

class MagicalString {
    public static void main(String[] args) {
        var m = new MagicalString();
        m.magicalString(6);
    }

    public int magicalString(int n) {
        if (n <= 3) {
            return 1;
        }

        StringBuilder sb = new StringBuilder("122");
        String nextNumber = "1";
        int index = 2;
        int count = 2;

        while (count < n) {
            char character = sb.charAt(index);
            int repeatCount = Character.getNumericValue(character);
            for (int i = 0; i < repeatCount; i++) {
                sb.append(nextNumber);
            }
            index++;
            nextNumber = "1".equals(nextNumber) ? "2" : "1";
            count++;
        }

        int countOfOnes = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == '1') {
                countOfOnes++;
            }
        }
        return countOfOnes;
    }
}
