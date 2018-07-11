// Solution 1: Recursion
// Time Limit Exceeded
// Time Complexity = O(2*(m+n))
// T(n) = 2*T(n-1) --> T(n) = 2^n, n = number of char in s3 = m + n
// Space Complexity = O(m+n). The size of stack for recursive calls can go up to m+n. 
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        if (s1.isEmpty()) return s2.equals(s3);
        else if (s2.isEmpty()) return s1.equals(s3);
        
        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);
        char c3 = s3.charAt(0);
        if (c1 == c3 && c2 == c3) {
            return isInterleave(s1.substring(1), s2, s3.substring(1)) ||
                isInterleave(s1, s2.substring(1), s3.substring(1));
        } else if (c1 == c3) {
            return isInterleave(s1.substring(1), s2, s3.substring(1));
        } else if (c2 == c3) {
            return isInterleave(s1, s2.substring(1), s3.substring(1));
        } else return false;
    }
}

// Solution 1 version 2
// Time Limit Exceeded
// Time Complexity = O(2*(m+n))
// Space Complexity = O(m+n). The size of stack for recursive calls can go up to m+n. 
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return helper(s1, s2, 0, 0, s3);
    }
    
    // i = current index in s1, j = current index in s2
    // current index in s3 is (i+j)
    private boolean helper(String s1, String s2, int i, int j, String s3) {
        if (i + j == s3.length()) return true;
        
        boolean match1 = i < s1.length() && s1.charAt(i) == s3.charAt(i + j);
        boolean match2 = j < s2.length() && s2.charAt(j) == s3.charAt(i + j);
        if (match1 && match2) {
            return helper(s1, s2, i + 1, j, s3) || helper(s1, s2, i, j + 1, s3);
        } else if (match1) {
            return helper(s1, s2, i + 1, j, s3);
        } else if (match2) {
            return helper(s1, s2, i, j + 1, s3);
        } else return false;
    }
}



// Solution 2: DP (2D array)
// beats 26.42%
// Time Complexity = O(mn)
// Space Complexity = O(n)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        // dp[i][j] = s3 matches up to the (i-1)th character of s1 and (j-1)th character of s2
        int len1 = s1.length(), len2 = s2.length();
        boolean[][] dp = new boolean[1 + len1][1 + len2];
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                boolean match1 = i < len1 && s1.charAt(i) == s3.charAt(i + j);
                boolean match2 = j < len2 && s2.charAt(j) == s3.charAt(i + j);
                if (match1 && match2) {
                    if (!dp[i + 1][j]) dp[i + 1][j] = dp[i][j];
                    if (!dp[i][j + 1]) dp[i][j + 1] = dp[i][j];
                } else if (match1) {
                    if (!dp[i + 1][j]) dp[i + 1][j] = dp[i][j];
                } else if (match2) {
                    if (!dp[i][j + 1]) dp[i][j + 1] = dp[i][j];
                }
            }
        }
        return dp[len1][len2];
    }
}

// Solution 2, same version
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        // dp[i][j] = s3 matches up to the (i-1)th character of s1 and (j-1)th character of s2
        int len1 = s1.length(), len2 = s2.length();
        boolean[][] dp = new boolean[1 + len1][1 + len2];
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                boolean match1 = i < len1 && s1.charAt(i) == s3.charAt(i + j);
                boolean match2 = j < len2 && s2.charAt(j) == s3.charAt(i + j);
                if (match1 && match2) {
                    dp[i + 1][j] = dp[i][j] || dp[i + 1][j];
                    dp[i][j + 1] = dp[i][j] || dp[i][j + 1];
                } else if (match1) {
                    dp[i + 1][j] = dp[i][j] || dp[i + 1][j];
                } else if (match2) {
                    dp[i][j + 1] = dp[i][j] || dp[i][j + 1];;
                }
            }
        }
        return dp[len1][len2];
    }
}

// Solution 2, version 2
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        // dp[i][j] = s3 matches up to the (i-1)th character of s1 and (j-1)th character of s2
        int len1 = s1.length(), len2 = s2.length();
        boolean[][] dp = new boolean[1 + len1][1 + len2];
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                boolean match1 = i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                boolean match2 = j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                if (match1 && match2) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (match1) {
                    dp[i][j] = dp[i - 1][j];
                } else if (match2) {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }
}

// Solution 2, version 3
// beats 70.93%
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        // dp[i][j] = s3 matches up to the (i-1)th character of s1 and (j-1)th character of s2
        int len1 = s1.length(), len2 = s2.length();
        boolean[][] dp = new boolean[1 + len1][1 + len2];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[len1][len2];
    }
}



// Solution 3: DP (1D array)
// beats 56.69%
// Time Complexity = O(mn)
// Space Complexity = O(n)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        // dp[i][j] = s3 matches up to the (i-1)th character of s1 and (j-1)th character of s2
        int len1 = s1.length(), len2 = s2.length();
        boolean[] dp = new boolean[1 + len2];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[len2];
    }
}



// Solution 4: Recursion with memoization
// beats 87%
// Time Complexity = O(2*(m+n))
// Space Complexity = O(mn). memoization array size = m*n.
public class Solution {
    public boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
    }
}