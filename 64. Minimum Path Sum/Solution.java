//Time Complexity = O(m*n), m = #rows, n = #cols, no extra space
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j != 0) grid[i][j] += grid[i][j-1];
                else if (j == 0 && i != 0) grid[i][j] += grid[i-1][j];
                else if (i > 0 && j > 0) grid[i][j] += Math.min(grid[i][j-1], grid[i-1][j]);
                //if i==0 && j==0, no need for sum-->do nothing, so exclude this case
            }
        }
        return grid[rows-1][cols-1];
    }
}

//SKip the checking-->faster runtime:
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        for (int j = 1; j < cols; j++) grid[0][j] += grid[0][j-1]; //initialize row 0
        for (int i = 1; i < rows; i++) grid[i][0] += grid[i-1][0]; //initialize col 0
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                grid[i][j] += Math.min(grid[i][j-1], grid[i-1][j]);
            }
        }
        return grid[rows-1][cols-1];
    }
}