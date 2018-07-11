// Solution 1: Recursion
// beats 8%
class Solution {
    public boolean isMatch(String string, String pattern) {
        if (pattern.isEmpty()) return string.isEmpty();
        
        // first_match checks whether string[0] matches pattern[0].
        boolean first_match = !string.isEmpty() && 
                                (pattern.charAt(0) == '.' || pattern.charAt(0) == string.charAt(0));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return isMatch(string, pattern.substring(2)) ||
                (first_match && isMatch(string.substring(1), pattern));
        } else {
            return first_match && isMatch(string.substring(1), pattern.substring(1));
        }
    }
}



// Solution 2: DP
// Top-Down Approrach
// beats 42.41%
// Time Complexity: Let T,P be the lengths of the text and the pattern respectively. 
// The work for every call to dp(i, j) for i=0,...,T; j=0,...,P is done once, and 
// it is O(1) work. Hence, the time complexity is O(TP).

// Space Complexity: The only memory we use is the O(TP) boolean entries in our cache. 
// Hence, the space complexity is O(TP).
enum Result {
    TRUE, FALSE
}

class Solution {
    Result[][] memo;

    public boolean isMatch(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                                   (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                       first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }
}



// Solution 3: DP
// Bottom-Up Approrach
// beats 49.08%
class Solution {
    public boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}