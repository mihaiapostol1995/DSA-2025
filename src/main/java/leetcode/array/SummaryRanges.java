package leetcode.array;

import java.util.ArrayList;
import java.util.List;

class SummaryRanges {

    public static void main(String[] args) {
        var res = summaryRanges(new int[]{0,1,2,4,5,7});
        System.out.println(res);
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> range = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            var next = nums[i];

            if (range.isEmpty()) {
                range.add(next);
            } else {
                var last = range.get(range.size() - 1);
                if (next == last + 1) {
                    range.add(next);
                } else {
                    var string = transformRange(range);
                    result.add(string);
                    range.clear();
                    range.add(next);
                }
            }
        }
        if (!range.isEmpty()) {
            result.add(transformRange(range));
        }
        return result;
    }

    private static String transformRange(List<Integer> range) {
        if (range.size() == 1) {
            return "" + range.get(0);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(range.get(0));
        sb.append("->");
        sb.append(range.get(range.size() - 1));
        return sb.toString();
    }
}
