package leetcode.easy;

class ReverseWordsInAStringIII {
    public static void main(String[] args) {
        var r = new ReverseWordsInAStringIII();
        String s = r.reverseWords("Let's take LeetCode contest");
        System.out.println(s);
    }

    public String reverseWords(String s) {
        var words = s.trim().split(" ");
        StringBuilder res = new StringBuilder();

        for (String word : words) {
            res.append(new StringBuilder(word).reverse());
            res.append(' ');
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
