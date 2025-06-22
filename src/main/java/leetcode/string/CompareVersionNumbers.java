package leetcode.string;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class CompareVersionNumbers {
    public static void main(String[] args) {
        compareVersion("1.0", "1.0.0.0");
    }

    public static int compareVersion(String version1, String version2) {
        LinkedList<String> v1 = Arrays.stream(version1.split("\\."))
                .collect(Collectors.toCollection(LinkedList::new));
        LinkedList<String> v2 = Arrays.stream(version2.split("\\."))
                .collect(Collectors.toCollection(LinkedList::new));

        if (v1.size() > v2.size()) {
            int diff = v1.size() - v2.size();
            for (int i = 0; i < diff; i++) {
                v2.add("0");
            }
        } else if (v2.size() > v1.size()) {
            int diff = v2.size() - v1.size();
            for (int i = 0; i < diff; i++) {
                v1.add("0");
            }
        }

        while (!v1.isEmpty() && !v2.isEmpty()) {
            String v1String = v1.pop();
            String v2String = v2.pop();
            if (Integer.parseInt(v1String) < Integer.parseInt(v2String)) {
                return -1;
            } else if (Integer.parseInt(v1String) > Integer.parseInt(v2String)) {
                return 1;
            }
        }

        return 0;
    }
}
