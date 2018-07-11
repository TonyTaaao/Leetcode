// This problem uses the same approach as LC 84. Largest rectangle in histogram
// Time Complexity = O(mn)
// we iterate through each row (m rows in total), and for each row, 
// resetHeight() and largestInLine() each takes O(n) time.
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length; // length
        if (m == 0) return 0;
        int n = matrix[0].length; // width
        if (n == 0) return 0;
        
        int[] height = new int[n];
        int maxarea = 0;
        for (int i = 0; i < m; i++) {
            resetHeight(matrix, height, i);
            maxarea = Math.max(maxarea, largestInLine(height));
        }
        return maxarea;
    }
    
    private void resetHeight(char[][] matrix, int[] height, int row) {
        int n = height.length;
        for (int i = 0; i < n; i++) {
            if (matrix[row][i] == '1') height[i]++;
            else height[i] = 0; // Don't forget this line
        }
    }
    
    private int largestInLine(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int maxarea = 0;
        int length = height.length;
        for (int i = 0; i <= length; ) {
            int h = i == length ? 0 : height[i];
            // Note: h >= height[stack.peek()], don't forget =
            if (stack.isEmpty() || h >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int j = stack.pop();
                maxarea = Math.max(maxarea, height[j] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
            }
        }
        return maxarea;
    }
}