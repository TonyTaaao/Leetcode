// assume lower case only
// beats 96.17%
/*
The 1st IF is to check the LEFT child of S1 is scramble of LEFT child of S2 AND 
RIGHT child of S1 is also a scramble of RIGHT child of s2.
If this fails, we check if the left and right substrings are swapped.
So the 2nd IF statement checks for the swap case with LEFT child of S1 and 
RIGHT child of S2 AND RIGHT child of S1 and LEFT child of S2.
*/
// Time Complexity is O(n!) ???
// T(n) = nT(n-1) = n(n-1)*T(n-2) = n(n-1)(n-2)*T(n-3) = ... = O(n!)
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        
        int[] letters = new int[26];
        for (int i=0; i<s1.length(); i++) {
            letters[s1.charAt(i)-'a']++;
            letters[s2.charAt(i)-'a']--;
        }
        for (int i=0; i<26; i++) if (letters[i]!=0) return false;
    
        for (int i=1; i<s1.length(); i++) {
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) 
             && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0,i), s2.substring(s2.length()-i)) 
             && isScramble(s1.substring(i), s2.substring(0,s2.length()-i))) return true;
        }
        return false;
    }
}