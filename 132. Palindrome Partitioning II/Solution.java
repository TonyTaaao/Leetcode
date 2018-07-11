// Solution 1: Recursion (Brute Force)
// Time Limit Exceeded
class Solution {
    public int minCut(String s) {
        if (isPalindrome(s)) return 0; // if s is palindrome, no need to partition
        int length = s.length();
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < length; i++) {
            String before = s.substring(0, i);
            String after = s.substring(i, length);
            int count = 1 + minCut(before) + minCut(after);
            min = Math.min(min, count);
        }
        return min;
    }
    
    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) { // stop when i and j cross
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}



// Solution 2: Memoization
// Correct Algorithm, Time Limit Exceeded
// Time Complexity = O(n^3), two for loops, isPalindrome() takes O(n) time within the loop
// Space Complexity = O(n), size of memoization array dp[]
class Solution {
    public int minCut(String s) {
        int length = s.length();
        if (isPalindrome(s, 0, length - 1)) return 0; // if s is palindrome, no need to partition
        // dp[i] = minimum number of cuts to partition the first (i-1) characters of s
        int[] dp = new int[length + 1];
        dp[0] = 0; // empty string is palindrome itself, no need for additional cut
        for (int i = 0; i < length; i++) {
            if (isPalindrome(s, 0, i)) dp[i + 1] = 0;
            else {
                int min = Integer.MAX_VALUE;
                for (int j = 1; j <= i; j++) {
                    // you need to AT LEAST cut once between substring(0, j) and substring(j, i)
                    // and if substring(j, i) is not palindrome, you need to cut partition into 1-char sequence, which
                    // takes (i-j) cuts
                    min = Math.min(min, dp[j] + 1 + (isPalindrome(s, j, i) ? 0 : i - j));
                }
                dp[i + 1] = min;
            }
        }
        return dp[length];
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        //int i = 0, j = s.length() - 1;
        while (i < j) { // stop when i and j cross
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}



// Solution 3: DP
// beats 48%
// Time Complexity = O(n^2)
// Space Complexity = O(n)
public class Solution {
    public int minCut(String s) {
        int length = s.length();
        
        // palindrome[i][j] = whether s[i:j] is a palindrome
        // we're going to build palindrome[][] using DP
        boolean[][] palindrome = new boolean[length][length];
        // cuts[i] = the minimum number of cuts to partition s[0:i]
        int[] cuts = new int[length];
        
        for (int i = 0; i < length; i++) {
            // in the worst case, dp[0:i] has to be partitioned into a sequence of substrings of length 1, and this takes i cuts.
            cuts[i] = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i)) {
                    // if j+1 >= i-1 --> i-j <= 2, no need to check palindrome[j + 1][i - 1]
                    if (i - j <= 2 || palindrome[j + 1][i - 1]) {
                        palindrome[j][i] = true;
                        if (j == 0) {
                            cuts[i] = 0;
                            // s[0: i] is a palindrome, so the minimum number of cuts till index i is zero
                            // there is no need to checks[j:1] for the rest of j -->we can break here
                            break;
                        }
                        else cuts[i] = Math.min(cuts[i], 1 + cuts[j - 1]);
                    }
                }
            }
        }
        return cuts[length - 1];
    }
}


// version 2
public class Solution {
    public int minCut(String s) {
        int n = s.length();
        
        // palindrome[i][j] = whether s[i:j] is a palindrome
        // we're going to build palindrome[][] through DP
        boolean[][] palindrome = new boolean[n][n];
        // cuts[i] = the minimum number of cuts to partition s[0:i]
        int[] cuts = new int[n];
        
        for (int i = 0; i < n; i++) {
            // in the worst case, dp[0:i] has to be partitioned into a sequence of substrings of length 1, and this takes i cuts.
            int min = i;
            for (int j = 0; j <= i; j++) {
                // if j+1 >= i-1 --> i-j <= 2, no need to check palindrome[j + 1][i - 1]
                if (s.charAt(j) == s.charAt(i) &&(i - j <= 2 || palindrome[j + 1][i - 1])) {
                    palindrome[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, 1 + cuts[j - 1]);
                }
            }
            cuts[i] = min;
        }
        return cuts[n - 1];
    }
}


// version 3
// convert String to char array will make runtime faster
// beats 71.90%
public class Solution {
    public int minCut(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;  
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }
}