package leetcode.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FindDuplicateFileInSystem {

    public static void main(String[] args) {
        var f = new  FindDuplicateFileInSystem();
        List<List<String>> duplicate = f.findDuplicate(new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)",
                "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"});
        System.out.println(duplicate);
    }

    public List<List<String>> findDuplicate(String[] parts) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < parts.length; i++) {
            String file = parts[i];
            String[] split = file.split(" ");
            var dir = split[0];
            for (int j = 1; j < split.length; j++) {
                var first = split[j];

                int openParen = first.indexOf('(');
                int closeParen = first.indexOf(')');

                String fileName = first.substring(0, openParen);
                String content = first.substring(openParen + 1, closeParen);

                map.computeIfAbsent(content, k -> new ArrayList<>()).add(dir + "/" + fileName);
            }
        }

        return map.values()
                .stream()
                .filter(l -> l.size() > 1)
                .toList();
    }
}
