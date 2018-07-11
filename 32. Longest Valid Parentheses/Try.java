class Solution {
    public int longestValidParentheses(String s) {
        return 2 * helper(0, 0, 0, s);
    }
    
    // start = number of "("'s
    // end = number of ")"'s
    // i = current position in string
    private int helper(int start, int close, int i, String s) {
        // end case
        if (i == s.length()) return close;
        
        if (s.charAt(i) == '(') start++;
        else if (start > close) close++;
        // start == close and s[i] is close
        else return Math.max(close, helper(0, 0, i + 1, s));
        
        return helper(start, close, i + 1, s);
    }
}