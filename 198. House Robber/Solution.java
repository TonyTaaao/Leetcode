//Solution 1: DP, keep DP array of size n
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0; //empty street, no house
        if (nums.length == 1) return nums[0];
        int[] total = new int[nums.length];
        total[0] = nums[0];
        total[1] = Math.max(nums[0], nums[1]);
        int max = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            total[i] = Math.max(total[i-1], total[i-2] + nums[i]); //cannot rob two adjacent houses, can rob non adjacent houses
            max = Math.max(max, total[i]);
        }
        return max;
    }
}

//Solution 2: Siimilar to Solution 1, but no need to constantly check and update max(overall max)
//-->can save more time
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0; //empty street, no house
        if (nums.length == 1) return nums[0];
        int[] total = new int[nums.length];
        total[0] = nums[0];
        total[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            total[i] = Math.max(total[i-1], total[i-2] + nums[i]); //cannot rob two adjacent houses, can rob non adjacent houses
        }
        return Math.max(total[nums.length - 1], total[nums.length - 2]);
    }
}

//Solution 3: No need to keep DP array of size n, Space Complexity = O(1) 
//-->Best solution out of 3
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0; //empty street, no house
        if (nums.length == 1) return nums[0];
        int rob = 0; //rob means non-adjacent, can be robbed with current (nums[i])
        int notrob = 0; //notrob means adjacent, cannot be robbed with current (nums[i])
        for (int i = 0; i < nums.length; i++) {
            int temp = rob;
            rob = Math.max(rob, notrob);
            notrob = temp + nums[i];
        }
        return Math.max(rob, notrob);
    }
}