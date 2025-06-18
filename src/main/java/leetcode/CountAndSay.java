package leetcode;

class CountAndSay {

    public static void main(String[] args) {
        countAndSay(5);
    }

    static String countAndSay(int n) {
        String initial = "11";
        for (int i = 2; i < n; i++) {
            String second = "";
            int j = 0;

            while (j < initial.length()) {
                int count = 0;
                int k = j;
                while (k < initial.length()
                        && initial.charAt(k) == initial.charAt(j)) {
                    count++;
                    k++;
                }
                second = second + count + initial.charAt(j);

                j = k;
            }

            initial = second;
        }

        return initial;
    }
}
