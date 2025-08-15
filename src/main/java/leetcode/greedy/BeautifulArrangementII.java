package leetcode.greedy;

import java.util.Arrays;

class BeautifulArrangementII {

    public static void main(String[] args) {
        var b  = new BeautifulArrangementII();
        int[] ints = b.constructArray(10, 4);
        System.out.println(Arrays.toString(ints));
    }

    public int[] constructArray(int n, int k) {
        int left = 1, right = k + 1; // + 1 because 1 is the beginning
        int[] res = new int[n];

        for (int i = 0; i <= k; i++) {
            res[i] = i % 2 == 0 ? left++ : right--;
        }
        // start from the end above
        for (int i = k + 1; i < n; i++) {
            res[i] = i + 1;
        }
        return res;
    }
}
