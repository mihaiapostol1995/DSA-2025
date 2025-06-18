package leetcode.greedy;

class GasStation {

    public static void main(String[] args) {
        var result = canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
//        var result = canCompleteCircuit(new int[]{7,1,0,11,4}, new int[]{5,9,1,2,5});
        System.out.println(result);
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int gasSum = 0;
        int costSum = 0;
        int start = 0;
        int currentTank = 0;

        for (int i = 0; i < gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];

            currentTank = currentTank + gas[i] - cost[i];
            if (currentTank < 0) {
                start = i + 1;
                currentTank = 0;
            }
        }

        return gasSum < costSum ? -1 : start;
    }
}
