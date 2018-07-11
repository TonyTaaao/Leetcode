// Solution 2: DP
// beats 90.89%
// Time Complexity = O(n), one iteration through the string to fill dp array.
// Space Complexity = O(n), dp array of size n is used.
public class Solution {
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        int length = s.length();
        int[] dp = new int[length]; // dp[i] stores longest valid substring that ends at index i
        
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = ((i - 2 >= 0) ? dp[i - 2] : 0) + 2;
                } 
                else if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxlen = Math.max(maxlen, dp[i]);
            }
        }
        return maxlen;
    }
}

// Solution 2: DP -- more concise version
// The 2 cases in for loop: s[i-1] == '(', and s[i - 1 - dp[i - 1]] == '(',
// are ONE generalized condition, so we can combine them
public class Solution {
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        int length = s.length();
        int[] dp = new int[length];
        
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == ')') {
                if (i - 1 - dp[i - 1] >= 0 && s.charAt(i - 1 - dp[i - 1]) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxlen = Math.max(maxlen, dp[i]);
            }
        }
        return maxlen;
    }
}



// Solution 3: Using Stack
// beats 47.62%
// Time complexity : (n). n is the length of the given string..
// Space complexity : O(n). The size of stack can go up to n. 
public class Solution {
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        int length = s.length();
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // -1 is used to concatenate consecutive ()'s, 
        // e.g. ()()(), will count as 6 instead of 2 (only the last one)
        
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } 
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                else {
                    maxlen = Math.max(maxlen, i - stack.peek());
                }
            }
        }
        return maxlen;
    }
}



// Solution 4: Without extra space
// Time complexity : O(2n) ~ O(n). Two traversals of the string.
// Space complexity : O(1). Only two extra variables, left and right, are needed.
// beats 90.89%
public class Solution {
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        int length = s.length();
        int left = 0, right = 0;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (right == left) {
                maxlen = Math.max(maxlen, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        
        left = 0;
        right = 0;
        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            
            if (left == right) {
                maxlen = Math.max(maxlen, 2 * right);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlen;
    }
}