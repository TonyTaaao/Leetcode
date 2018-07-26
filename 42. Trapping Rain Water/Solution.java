// Solution 1: Stack
// beats 14.83%
// Time = O(n), Space = O(n)
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        stack.push(0);
        i++;
        while (i < n && height[i] >= height[stack.peek()]) {
            stack.pop();
            stack.push(i); // push the peak bar into stack
            i++;
        }
        
        while (i < n) {
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
                i++;
            }
            else if (height[i] == height[stack.peek()]) {
                stack.pop();
                stack.push(i);
                i++;
            }
            else {
                // height[i] > height[stack.peek()], can trap water
                int bottom = stack.pop();
                boolean equalHeight = false;
                while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                    int lastBar = stack.pop();
                    result += (height[lastBar] - height[bottom]) * (i - 1 - lastBar);
                    //System.out.println("current: " + i + ", lastBar: " + lastBar + ", result: " + result);
                    bottom = lastBar;
                    if (height[i] == height[lastBar]) equalHeight = true;
                }
                if (!equalHeight && !stack.isEmpty()) {
                    int lastBar = stack.peek();
                    result += (height[i] - height[bottom]) * (i - 1 - lastBar);
                }
                stack.push(i);
                i++;
            }
        }
        return result;
    }
}