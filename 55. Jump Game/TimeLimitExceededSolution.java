//Correct algorithm, exceed runtime limit
class Solution {
    public boolean canJump(int[] nums) {
        return reachable(nums, 0);
    }
    
    //start = start index
    private boolean reachable(int[] nums, int start) {
        if (start == nums.length - 1) return true;
        if (start >= nums.length || nums[start] == 0) return false;
        
        int maxJump = nums[start];
        for (int i = 1; i <= maxJump; i++) {
            if (reachable(nums, start + i)) return true;
        }
        return false;
    }
}