//Two Pointers Approach, Time = O(N)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int sum;
        while (lo < hi) {
            sum = nums[lo] + nums[hi];
            if (sum == target) break;
            else if (sum > target) hi--;
            else lo++;
        }
        return new int[]{lo+1, hi+1};
    }
}