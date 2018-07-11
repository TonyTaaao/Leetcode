//Time Complexity = O(m*n), Space Complexity = O(m*n)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else if (i == 0 && j == 0) dp[i][j] = 1; //dp[0][0] = 1, starting point
                else if (i == 0) dp[i][j] = dp[i][j-1]; //For grid on row 0, if its left is obstable, then current grid is 0, else 1, meaning its path all depends on its left
                else if (j == 0) dp[i][j] = dp[i-1][j];//For grid on col 0, if its upper cell is obstable, then current grid is 0, else 1, meaning its path all depends on its upper cell
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[row-1][col-1];
    }
}

//Optimization of Space:
//Time Complexity = O(m*n), Space Complexity = O(n)
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1) dp[j] = 0;
                else if (j > 0) dp[j] += dp[j-1];
            }
        }
        return dp[width - 1];
    }
}
/***
dp[j] += dp[j - 1];
is
dp[j] = dp[j] + dp[j - 1];
which is new dp[j] = old dp[j] + dp[j-1]
which is current cell = top cell + left cell

Hope this helps.
*/

//In place (no extra space):
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
                else if (i == 0 && j == 0) obstacleGrid[i][j] = 1;
                else if (i == 0) obstacleGrid[i][j] = obstacleGrid[i][j-1]; // For row 0, if there is no path to left cell, then its 0,else 1
                else if (j == 0) obstacleGrid[i][j] = obstacleGrid[i-1][j]; // For col 0, if there is no path to upper cell, then its 0,else 1
                else obstacleGrid[i][j] = obstacleGrid[i][j-1] + obstacleGrid[i-1][j];
            }
        }
        return obstacleGrid[row-1][col-1];
    }
}