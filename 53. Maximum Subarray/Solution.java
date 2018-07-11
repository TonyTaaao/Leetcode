//Time Complexity = O(n), Space Complexity = O(n)
class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] > 0 ? nums[i] + dp[i-1] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

//More efficient, Time Complexity = O(n), Space Complexity = O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0], maxcursum = nums[0]; //maxcursum: max value of (current element + its preceeding neighbor)
        
        for (int i = 1; i < nums.length; i++) {
            maxcursum = nums[i] + (maxcursum > 0 ? maxcursum : 0);
            max = Math.max(max, maxcursum);
        }
        return max;
    }
}