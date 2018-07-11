//Time Complexity = O(m*n), Space Complexity = O(m*n)
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) grid[i][j] = 1;
                else grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }
        return grid[m-1][n-1];
    }
}

//Optimization of Space:
//Time Complexity = O(m*n), Space Complexity = O(min(m,n))
class Solution {
    public int uniquePaths(int m, int n) {
        if (m < n) return uniquePaths(n, m); //so that space complexity = O(m) for m < n
        int[] dp = new int[n]; //space complexity = O(n) for n < m
        for (int i = 0; i < n; i++) dp[i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
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