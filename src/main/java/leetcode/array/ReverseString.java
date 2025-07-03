package leetcode.array;

class ReverseString {
    public static void main(String[] args) {
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        ReverseString rs = new ReverseString();
        rs.reverseString(chars);
    }

    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            var temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
