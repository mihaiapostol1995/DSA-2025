package leetcode;


import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class MiniParser {

    public static void main(String[] args) {
        MiniParser parser = new MiniParser();
        NestedInteger deserialize = parser.deserialize("[123,456,[788,799,833],[[]],10,[]]");
        System.out.println(deserialize);
    }


    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            // Single integer
            return new NestedInteger(Integer.parseInt(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger current = null; // keep treck to add
        int start = 0;

        // idea: always push the current up to focus on a new current
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                // [12, 22, [34, 43], 21]
                // only for the initial processing
                if (current != null) {
                    stack.push(current);
                }
                current = new NestedInteger();
                start = i + 1;
            } else if (s.charAt(i) == ']') {
                String num = s.substring(start, i);
                if (!num.isEmpty()) {
                    current.add(new NestedInteger(Integer.parseInt(num)));
                }
                if (!stack.isEmpty()) {
                    var parentOfCurrent = stack.pop();
                    parentOfCurrent.add(current);
                    current = parentOfCurrent;
                }

                start = i + 1;
            } else if (s.charAt(i) == ',') {
                String num = s.substring(start, i);
                if (!num.isEmpty()) {
                    current.add(new NestedInteger(Integer.parseInt(num)));
                }
                start = i + 1;
            }
        }
        return current;
    }
}
