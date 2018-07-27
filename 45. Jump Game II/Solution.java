// In order to achive minimum number of jumps, we need to make sure that each jump is as far as we could go.
// 每次跳都要跳到最远能及的地方。从终点往回跳，每此都跳到最远点，这样跳的次数最少，跳到index＝＝0时return
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int size = nums.length;
        int[] jumps = new int[size]; // jumps[i] = minimum number of jumps required to jump from end to i
        int current = size - 1; // current index, start at last index
        int farthestJumpIndex = size - 1; // 从current index跳，能跳到的最远点
        while (current > 0) {
            for (int i = current - 1; i >= 0; i--) {
                if (nums[i] >= current - i) {
                    jumps[i] = jumps[current] + 1;
                    farthestJumpIndex = i;
                }
            }
            current = farthestJumpIndex; // 每次都跳到最远点，从最远点开始跳
        }
        return jumps[0];
    }
}



// Solution 2: BFS
/*
change this problem to a BFS problem, where nodes in level i are all the nodes that can be reached 
in the ith jump. for example. 2 3 1 1 4 , is
level 0:    2||
level 1:    3 1||
level 2:    1 4 ||

clearly, the minimum jump from start(2) to end(4) is 2 since 4 is in level 2.
*/
// beats 72.7%
class Solution {
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        
        int i = 0; // current index
        int level = 0;
        int currentFurthest = 0; // farthest position currently reachable, currently means no need to jump
        int nextFurthest = currentFurthest; // farthest position reachable with a jump
        int end = nums.length - 1;
        while (i < end) {
            // traverse current level: go through all nodes currently reachable, and update 
            // the max reach of next level.
            for (; i <= currentFurthest; i++) {
                nextFurthest = Math.max(nextFurthest, nums[i] + i);
                if (nextFurthest >= end) return level + 1; // if next level reaches the last element, # min jump = level + 1.
            }
            level++; // still not reach the end, need another jump.
            currentFurthest = nextFurthest;
        }
        return level;
    }
}

// Solution 2, another version
// beats 72.7%
class Solution {
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int curMax = 0; // to mark the last element in a level
        int level = 0, i = 0;
        while (i <= curMax) {
            level++;
            int furthest = curMax; // to mark the last element in the next level
            for (; i <= curMax; i++) {
                furthest = Math.max(furthest, nums[i] + i);
                if (furthest >= nums.length - 1) return level;
            }
            curMax = furthest;
        }
        return -1; // if i < curMax, i can't move forward anymore (the last element in the array can't be reached)
    }
}



