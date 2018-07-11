// Solution 1: Recursion (Brute Force)
// Time Limit Exceeded
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        else if (s.isEmpty()) return p.charAt(0) == '*' && isMatch(s, p.substring(1));
        
        // if s and p are both not empty -->
        if (p.charAt(0) == '*') {
            // count as an empty space
            return isMatch(s, p.substring(1)) ||
                // count as one or more matches
                isMatch(s.substring(1), p);
        } else {
            boolean first_match = p.charAt(0) == '?' || s.charAt(0) == p.charAt(0);
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
}



// Solution 2: DP
// Top-Down Approach
// beats 9%
// Time Complexity = O(SP), s = number of char in s, P = number of char in p
// Space Complexity = O(SP)
enum Result { // enum can be placed inside or outside class Solution
    TRUE, FALSE
}
    
class Solution {
    // memoization array, memo[i][j] stores whether s[i:] matches p[j:],
    // memo[0][0] stores whether s[0:] matches p[0:], aka whether s matches p.
    Result[][] memo;
    
    public boolean isMatch(String s, String p) {
        memo = new Result[s.length() + 1][p.length() + 1];
        return wildMatch(0, 0, s, p);
    }
    
    private boolean wildMatch(int i, int j, String s, String p) {
        if (memo[i][j] != null) return memo[i][j] == Result.TRUE;
        else if (j == p.length()) {
            memo[i][j] = i == s.length() ? Result.TRUE : Result.FALSE;
            return memo[i][j] == Result.TRUE;
        }
        
        boolean match;
        if (j == p.length()) {
            match = i == s.length();
        } else if (p.charAt(j) == '*') {
            match = wildMatch(i, j+1, s, p) || 
                i + 1 <= s.length() && wildMatch(i+1, j, s, p);
        } else {
            match = i < s.length() && wildMatch(i+1, j+1, s, p) &&
                (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j));
        }
        memo[i][j] = match ? Result.TRUE : Result.FALSE;
        return match;
    } 
}



// Solution 2: DP
// Bottom-Up Approach
// beats 26.04%
class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        // dp[i][j] stores whether s[i:] matches p[j:]
        dp[slen][plen] = true;
        
        for (int i = slen; i >= 0; i--) {
            for (int j = plen - 1; j >= 0; j--) {
                // if p[j] is '*', p[j] can count as an empty space (-->i stays, j+1) OR
                // count as one or more matches (i + 1, j stays)
                // 1. count as empty space --> s[i:] matches p[j:] if s[i:] matches p[j+1:]
                // 2. count as a match --> s[i:] matches p[j:] if s[i+1:] matches p[j:]
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i][j + 1] || i < slen && dp[i + 1][j];
                } 
                // if p[j] is not '*', s[i:] matches p[j:] if 
                // s[i] matches p[j] and s[i+1:] matches p[j+1:]
                else {
                    dp[i][j] = i < slen && dp[i + 1][j + 1] && 
                        (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j));
                }
            }
        }
        return dp[0][0];
    } 
}



// Solution 2: DP
// Top-Down Approach, version 2
// beats 33.86%
// Time Complexity = O(SP), s = s.length(), P = p.length()
// Space Complexity = O(SP)
class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        // dp[i][j] stores whether s[0:i-1] matches p[0:j-1].
        // Therefore, if s[0:i] matches p[0:j], it should be stored in dp[i+1][j+1]
        dp[0][0] = true;
        for (int j = 0; j < plen; j++) {
            if (dp[0][j] && p.charAt(j) == '*')
                dp[0][j + 1] = true;
            else
                break;
        }
        
        // In this iteration, we start at dp[1][1], so the first row dp[0][j]
        // does not get processed.
        // Therefore, we need to initialize the value of the first row first.
        // We do this in line 111-116.
        for (int i = 0; i < slen; i++) {
            for (int j = 0; j < plen; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }
        return dp[slen][plen];
    }
}



// Solution 2: DP
// Bottom-Up Approach, version 2
// beats 33.86%
class Solution {
    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[slen][plen] = true;
        for(int j = plen - 1; j >= 0; j--){
            if(p.charAt(j) != '*')
                break;
            else
                dp[slen][j] = true;
        }
        
        for (int i = slen - 1; i >= 0; i--) {
            for (int j = plen - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i][j + 1] || dp[i + 1][j];
                }
                else if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}