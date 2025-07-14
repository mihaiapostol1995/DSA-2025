package leetcode.stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

// monotonic stack
class RemoveDuplicateLetters {
    public static void main(String[] args) {
        String res = removeDuplicateLetters("abacb");
        System.out.println(res);
    }

    public static String removeDuplicateLetters(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Stack<Character> result = new Stack<>();
        Set<Character> seen = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (!seen.contains(c)) {
                while (!result.isEmpty()
                        && c < result.peek()
                        && map.get(result.peek()) > 0) {
                    var popped = result.pop();
                    seen.remove(popped);
                }
                result.push(c);
                seen.add(c);
            }
            map.put(c, map.get(c) - 1);
        }

        StringBuilder sb = new StringBuilder();
        while (!result.isEmpty()) {
            sb.append(result.pop());
        }
        return sb.reverse().toString();
    }
}
