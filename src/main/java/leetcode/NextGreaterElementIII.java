package leetcode;

class NextGreaterElementIII {
    public static void main(String[] args) {
        var n = new NextGreaterElementIII();
        n.nextGreaterElement(2147483486);
    }

    public int nextGreaterElement(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int mod = n % 10;
            sb.append(mod);
            n = n / 10;
        }
        StringBuilder reverse = sb.reverse();

        int[] ar = new int[reverse.length()];
        for (int i = 0; i < reverse.length(); i++) {
            ar[i] = reverse.charAt(i) - '0';
        }

        // 1 find pivot
        int pivot = ar.length - 2;
        while (pivot >= 0
                && ar[pivot] > ar[pivot + 1]) {
            pivot--;
        }
        if (pivot < 0) {
            return -1;
        }

        // find first smallest number, bigger than pivot
        int min = Integer.MAX_VALUE;
        int firstBiggest = -1;
        for (int i = pivot; i < ar.length; i++) {
            if (ar[i] > ar[pivot]
                    && ar[i] <= min) { // OR equal! furthest min
                min = ar[i];
                firstBiggest = i;
            }
        }

        swap(pivot, firstBiggest, ar);

        // reverse
        reverse(ar, pivot + 1, ar.length - 1);

        long number = getNumber(ar);
        return number > Integer.MAX_VALUE ? -1 : (int) number;
    }

    private long getNumber(int[] ar) {
        long i = 0;
        for (int j = 0; j < ar.length; j++) {
            i = i * 10 + ar[j];
        }
        return i;
    }

    private static void reverse(int[] nums, int pivot, int last) {
        int first = pivot;
        while (first < last) {
            int temp = nums[first];
            nums[first] = nums[last];
            nums[last] = temp;
            first++;
            last--;
        }
    }

    static void swap(int i1, int i2, int[] nums) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}
