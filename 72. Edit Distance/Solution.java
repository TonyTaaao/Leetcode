// DP Solution
// beats 42%
public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] cost = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    cost[i + 1][j + 1]++;
                }
            }
        }
        return cost[m][n];
    }
}



// Same Approach, My version
// Time Complexity = O(nm)
// Space Complexity = O(nm)
// beats 84%
public class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        
        // matches word1[0: i-1] with word2[0: j-1]
        // cost[i][j] = cost of word1[0:i-1] to match word2[0:j-1].
        int[][] cost = new int[1 + len1][1 + len2];
        
        // f(0, k) = f(k, 0) = k
        // f(k, 0) = k
        for (int i = 1; i <= len1; i++) {
            cost[i][0] = i;
        }
        // f(0, k) = k
        for (int j = 1; j <= len2; j++) {
            cost[0][j] = j;
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // remeber that cost[i][j] = cost of word1[0:i-1] to match word2[0:j-1]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    cost[i][j] = cost[i - 1][j - 1];
                } else {
                    int insert = cost[i][j - 1];
                    int delete = cost[i - 1][j];
                    int replace = cost[i - 1][j - 1];
                    cost[i][j] = 1 + Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        return cost[len1][len2];
    }
}