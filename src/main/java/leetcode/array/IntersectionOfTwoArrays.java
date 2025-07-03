package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class IntersectionOfTwoArrays {
    public static void main(String[] args) {

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2)
                .boxed()
                .collect(Collectors.toSet());
        set.retainAll(set2);
        return set.stream().mapToInt(Integer::intValue).toArray();
    }
}
