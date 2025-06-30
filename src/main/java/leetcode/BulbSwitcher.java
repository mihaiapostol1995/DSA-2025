package leetcode;

class BulbSwitcher {
    public static void main(String[] args) {
        BulbSwitcher bulbSwitcher = new BulbSwitcher();
        int i = bulbSwitcher.bulbSwitch(3);
        System.out.println(i);
    }

    public int bulbSwitch(int n) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (isPerfectSquare(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
