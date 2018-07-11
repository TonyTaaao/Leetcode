// Solution 1: Recursion
// Time Limit Exceeded
class Solution {
    public int numDistinct(String s, String t) {
        return numDistinct(s, t, 0, 0);
    }
    
    // i = current index in s, j = current index in j
    private int numDistinct(String s, String t, int i, int j) {
        if (j == t.length()) return 1;
        else if (i == s.length()) return 0;
        
        int res = 0;
        for (int idx = i; idx < s.length(); idx++) {
            if (s.charAt(idx) == t.charAt(j)) {
                res += numDistinct(s, t, idx + 1, j + 1);
            }
        }
        return res;
    }
}



// Solution 2: DP (2D array)
// beats 82.82%
// memoization array dp[i][j] = number of possible combinations for the first (i-1)
// characters of s to match the first (j-1) characters of t.
// Therefore, the result will be dp[S.length()][T.length()]
/*
the first column, dp[i][0], must be filled with 1. That's because an empty string 
is a subsequence of any string but only 1 time. So dp[i][0] = 1 for every i. 
With this we are able to return correct values if T is an empty string.

the first rows of every column except the first, dp[0][j], must be 0. This is because 
an empty string cannot contain a non-empty string as a substring -- the very first 
item of the array: dp[0][0] = 1, because an empty string contains the empty string 1 time.

So the matrix looks like this:

  S 0123....j
T +----------+
  |1111111111|
0 |0         |
1 |0         |
2 |0         |
. |0         |
. |0         |
i |0         |

From here we can easily fill the whole grid: for each (x, y), 
we check if S[x] == T[y], we add the previous item and the previous item in the previous col, otherwise we copy the previous item in the same col. The reason is simple:

if the current character in S doesn't equal to current character T, then we have the same number of distinct subsequences as we had without the new character.
if the current character in S equal to the current character T, then the distinct number of subsequences: the number we had before plus the distinct number of subsequences we had with less longer T and less longer S.
*/
// Time Complexity = O(mn)
// Space Complexity = O(mn)
class Solution {
    public int numDistinct(String s, String t) {
        int len_s = s.length(), len_t = t.length();
        if (len_s < len_t) return 0;
        
        // dp[i][j] = number of possible combinations for the first (i-1) characters 
        // of s to match the first (j-1) characters of t
        int[][] dp = new int[len_s + 1][len_t + 1];
        
        // fill the first col with 1s
        for (int i = 0; i <= len_s; i++) {
            dp[i][0] = 1;
        }

        // the first row is 0 in all but the first column

        for (int i = 1; i <= len_s; i++) {
            for (int j = 1; j <= len_t; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len_s][len_t];
    }
}



// Solution 3: DP (1D array)
// beats 82.82%
// Time Complexity = O(mn)
// Space Complexity = O(n)
class Solution {
    public int numDistinct(String s, String t) {
        int len_s = s.length(), len_t = t.length();
        if (len_s < len_t) return 0;
        int[] dp = new int[len_t + 1];
        dp[0] = 1; // fill the first col with 1
        
        for (int i = 1; i <= len_s; i++) {
            int prev = dp[0];
            for (int j = 1; j <= len_t; j++) {
                int temp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = prev + dp[j];
                } 
                /*
                else {
                    dp[j] = dp[j];
                }
                */
                prev = temp;
            }
        }
        return dp[len_t];
    }
}

// Solution 3: DP (1D array), version 2
// Since dp[j] depends on the original value of dp[j - 1], we can let dp[j]
// update before dp[j - 1] --> iterate j backwards
// beats 96.27%
class Solution {
    public int numDistinct(String s, String t) {
        int len_s = s.length(), len_t = t.length();
        if (len_s < len_t) return 0;
        int[] dp = new int[len_t + 1];
        
        for (int i = 0; i <= len_s; i++) {
            for (int j = len_t; j >= 0; j--) {
                if (j == 0) dp[j] = 1;
                else if (i == 0) dp[j] = 0;
                else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
        }
        return dp[len_t];
    }
}