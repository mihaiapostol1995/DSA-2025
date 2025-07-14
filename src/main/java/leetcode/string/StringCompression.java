package leetcode.string;

class StringCompression {

    public int compress(char[] chars) {
        if (chars.length == 0) {
            return 0;
        }

        var cur = chars[0];
        var count = 0;
        var trackIndex = 0;
        for (var i = 0; i < chars.length; i++) {
            if (chars[i] == cur) {
                count++;
            } else {
                chars[trackIndex++] = cur;

                if (count != 1) {
                    char[] charArray = Integer.toString(count).toCharArray();
                    for (char c : charArray) {
                        chars[trackIndex++] = c;
                    }
                }
                count = 1;
                cur = chars[i];
            }
        }

        chars[trackIndex++] = cur;
        if (count != 1) {
            char[] charArray = Integer.toString(count).toCharArray();
            for (char c : charArray) {
                chars[trackIndex++] = c;
            }
        }

        for (int i = trackIndex; i < chars.length; i++) {
            chars[i] = ' ';
        }

        return trackIndex;
    }

    public static void main(String[] args) {
        var s = new StringCompression();
//        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
//        char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        char[] chars = {'a', 'b', 'c'};
        s.compress(chars);
        System.out.println(chars);
    }
}
