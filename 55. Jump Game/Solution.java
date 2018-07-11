//Solution1
//Time Complexity = O(N), constant space
class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0; //farthest index that previous indices can reach
        for (int i = 0; i < nums.length; i++) {
            if (farthest < i) return false;
            farthest = Math.max(farthest, i + nums[i]);
        }
        return true;
    }
}



//Solution2: Idea is to work backwards from the last index. Keep track of the 
//smallest index that can "jump" to the last index. Check whether the current 
//index can jump to this smallest index.
//Time Complexity = O(N), constant space
class Solution {
    public boolean canJump(int[] nums) {
        int last = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= last) last = i;
        }
        return last == 0;
    }
}