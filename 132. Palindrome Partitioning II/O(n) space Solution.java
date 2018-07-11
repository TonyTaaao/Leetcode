// beats 99%
// Time Complexity = O(n^2)
// Space Complexity = O(n)
/*
Explanation:

We can have an array of int[] f, where f[i] means the minumum number of cuts to partition s[0:i]. 
So we can just return f[len-1].
We can assume the maximum cuts at first, so the base cases are f[i] = i.
(Inspring by LC 5. Longest Palindromic Substring), we can expand from the center i to leftmost and
rightmost of current palindrome whose center is at i. 
Starting from center (left = center, right center), we expand left and right to the 
leftmost/rightmost of current palindrome substring. If s.charAt(left) == s.charAt(right), 
it means the s[left:right] is palindrome, 
then f[right] = f[left - 1] + 1, add 1 because partitioning between s[0:left-1] and s[left:right]
needs 1 cut. And if left == 0, that means s.substring(0, right + 1) is palindrom, so no cut is 
needed, f[right] = 0. 
We need to consider both even and odd length of the string so we expand from 
both [i, i] and [i, i+1].
*/
class Solution {
    public int minCut(String s) {
        int len = s.length();
        int[] f = new int[len];
        for (int i = 0; i < len; i++) {
            f[i] = i;
        }

        // for each index i in s, search for the longest palindrome whose center is at index i
        for (int i = 0; i < len; i++) {
            search(s, f, i, i); // if length of s is odd, the center is one character, eg. s="aabcc", center = "b"
            search(s, f, i, i + 1); // if length of s is even, the center is two char, eg. s="abcd", center = "bc"
        }

        return f[len - 1];
    }

    // this method searches for the longest palindrome with its center at s[left:right]
    private void search(String s, int[] f, int left, int right) {
        while (left >= 0 && right < s.length()
               && s.charAt(left) == s.charAt(right)) {
            if (left == 0) {
                // left == 0, substring(0, right+1) is palindrom, no cut needed
                f[right] = 0;
            } else {
                f[right] = Math.min(f[right], f[left - 1] + 1);
            }
            left--;
            right++;
        }
    }
}
