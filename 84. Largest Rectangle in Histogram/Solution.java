// Time Complexity = O(n), one pass
// Space Complexity = O(n) worst case, due to stack
// beats 34.10%
class Solution {
    public int largestRectangleArea(int[] heights) {
        int length = heights.length;
        if (length == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int maxarea = 0;
        for (int i = 0; i <= length; ) {
            int h = i == length ? 0 : heights[i];
            // if h is a peak (taller than previous one)
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int j = stack.pop();
                maxarea = Math.max(maxarea, heights[j] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
            }
        }
        return maxarea;
    }
}