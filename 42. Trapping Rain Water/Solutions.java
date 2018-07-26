// Solution 1: Brute Force
// Time = O(n^2), Space = O(1)
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i - 1; j >= 0; j--)
                max_left = Math.max(max_left, height[j]);
            for (int j = i + 1; j < size; j++)
                max_right = Math.max(max_right, height[j]);
            
            int waterTrappedAtCurrentPos = Math.min(max_left, max_right) - height[i];
            if (waterTrappedAtCurrentPos > 0) ans += waterTrappedAtCurrentPos;
        }
        return ans;
    }
}



// Solution 2: DP
// Time = O(n), Space = O(n)
// beats 19.22%
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int ans = 0;
        int size = height.length;
        int[] max_left = new int[size];
        int[] max_right = new int[size];
        
        max_left[0] = height[0];
        for (int i = 1; i < size; i++)
            max_left[i] = Math.max(height[i], max_left[i - 1]);
        max_right[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--)
            max_right[i] = Math.max(height[i], max_right[i + 1]);
        
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(max_left[i], max_right[i]) - height[i];
        }
        return ans;
    }
}



// Solution 3: Stack
// Time complexity: O(n). Single iteration of size-n array in which each bar can be 
// touched at most twice (due to insertion and deletion from stack). 
// Insertion and deletion from stack takes O(1) time.
// Space complexity: O(n). Stack can take up to O(n) space in case of stairs-like or 
// flat structure. 

// beats 10.86%
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < size) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int boundHeight = Math.min(height[current], height[stack.peek()]) - height[top];
                int boundArea = distance * boundHeight;
                ans += boundArea;
            }
            stack.push(current++);
        }
        return ans;
    }
}



// Solution 4: Two Pointers
// Time = O(n), Space = O(1)
// beats 96.4%
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        int left = 0, right = size - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                // the amount of trapped water depends on height[left]
                if (height[left] >= leftMax) leftMax = height[left];
                else ans += leftMax - height[left];
                left++;
            } else {
                // the amount of trapped water depends on height[right]
                if (height[right] >= rightMax) rightMax = height[right];
                else ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}