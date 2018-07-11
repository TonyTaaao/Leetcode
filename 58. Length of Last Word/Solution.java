//beats 99% runtime
class Solution {
    public int lengthOfLastWord(String s) {
        int last = s.length() - 1;
        int count = 0;
        //Note here: last >= 0 must be placed before s.charAt(last) to avoid OutOfBoundsException
        //Correct order: FIRST check if index is valid, THEN check value at that index
        while (last >= 0 && s.charAt(last) == ' ') last--;
        
        if (last == -1) return 0;
        for (int i = last; i >= 0 && s.charAt(i) != ' '; i--) {
            count++;
        }
        return count;
    }
}