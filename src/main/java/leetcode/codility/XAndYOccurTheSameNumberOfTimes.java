package leetcode.codility;

class XAndYOccurTheSameNumberOfTimes {

    public static void main(String[] args) {
        int toyxmy = solution("abcxyabcx");
        System.out.println(toyxmy);
    }

    public static int solution(String S) {
        if (S == null || S.length() < 3) {
            return 0;
        }
        if (S.contains("x") && !S.contains("y")) {
            return 0;
        }
        if (S.contains("y") && !S.contains("x")) {
            return 0;
        }
        if (!S.contains("x") && !S.contains("y")) {
            return S.length() - 1;
        }

        int totalYCount = 0, totalXCount = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'y') {
                totalYCount++;
            } else if (S.charAt(i) == 'x') {
                totalXCount++;
            }
        }

        int xCountSeen = 0, yCountSeen = 0;
        int count = 0;

        for (int i = 0; i < S.length() - 1; i++) {
            if (S.charAt(i) == 'x') {
                xCountSeen++;
            } else if (S.charAt(i) == 'y') {
                yCountSeen++;
            }

            if (yCountSeen == xCountSeen
                    || totalXCount - xCountSeen == totalYCount - yCountSeen) {
                count++;
            }
        }
        return count;
    }
}
