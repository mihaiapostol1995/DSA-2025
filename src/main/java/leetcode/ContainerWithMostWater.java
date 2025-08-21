package leetcode;

class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] ints = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] ints2 = {1, 100, 1, 100, 1};
        System.out.println(maxAreaFaster(ints2));
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
                if (j == height.length - 1 && height[i] > height[j]) {
                    break;
                }
            }
        }
        return maxArea;
    }

    public static int maxAreaFaster(int[] height) {
        int maxArea = 0;
        int left = 0;

//        while (left < right) {
//            int area = Math.min(height[left], height[right]) * (right - left);
//            maxArea = Math.max(maxArea, area);
//
//            if (height[left] < height[right]) {
//                left++;
//            } else {
//                right--;
//            }
//        }

        for (int right = 0; right < height.length;) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right++;
            }
        }

        return maxArea;
    }
}
