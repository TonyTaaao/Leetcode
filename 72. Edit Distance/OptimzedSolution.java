// beats 77.83%
// Time Complexity = O(mn)
// Space Complexity = O(m)
/*
Well, you may have noticed that each time when we update dp[i][j], we only need 
dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j].
In fact, we need not maintain the whole m*n matrix. Instead, maintaing one column 
is enough. The code can be optimized to O(m) or O(n) space, depending on whether you 
maintain a row or a column of the original matrix.

The optimized code is as follows.
*/
public class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        
        // cost[i][j] = cost of word1[0:i-1] to match word2[0:j-1].
        int[] cost = new int[1 + len1];
        
        // f(0, k) = f(k, 0) = k
        // f(k, 0) = k
        for (int i = 1; i <= len1; i++) {
            cost[i] = i;
        }
        
        for (int j = 1; j <= len2; j++) {
            int prev = cost[0]; // prev = cost[i - 1][j - 1]
            cost[0] = j; // f(0, k) = k
            for (int i = 1; i <= len1; i++) {
                int temp = cost[i];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost[i] = prev;
                } else {
                    int insert = cost[i]; // cost[i][j - 1]
                    int delete = cost[i - 1]; // cost[i - 1][j]
                    int replace = prev;
                    cost[i] = 1 + Math.min(Math.min(insert, delete), replace);
                }
                prev = temp;
            }
        }
        
        return cost[len1];
    }
}