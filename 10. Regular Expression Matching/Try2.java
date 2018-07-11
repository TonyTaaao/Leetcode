class Solution {
    public boolean isMatch(String s, String p) {
        return matchRegex(s, p, 0, 0);
    }
    
    // i = current index in s, j = current index in p
    private boolean matchRegex(String s, String p, int i, int j) {
        int len1 = s.length(), len2 = p.length();
        if (i == len1 && j == len2) return true;
        else if (j == len2) return false;
        
        // If s has already reached its end and p has not, the only way to find a match
        // is that the rest of p is equivalent to an empty string.
        if (i == len1) {
            if (j + 1 < len2 && p.charAt(j + 1) == '*') {
                // the only way to find a match is to count ".*" and "a*" as empty string
                j += 2;
            } else
                return false;
        }
        //
        else if (p.charAt(j) == '.') {
            if (j + 1 < len2 && p.charAt(j + 1) == '*') {
                if (j == len2 - 2) return true;
                
                for (int ii = i; ii <= len1; ii++) {
                    if (matchRegex(s, p, ii, j + 2)) return true;
                }
                return false;
            } else {
                i++;
                j++;
            }
        }
        else if (s.charAt(i) == p.charAt(j)) {
            if (j + 1 < len2 && p.charAt(j + 1) == '*') {
                char cur = s.charAt(i);
                int count1 = 0;
                while (i < len1 && s.charAt(i) == cur) {
                    count1++;
                    i++;
                }
                int count2 = 0;
                j += 2;
                while (j < len2) {
                    if (j + 1 < len2 && p.charAt(j + 1) == '*') {
                        j += 2;
                    }
                    else if (p.charAt(j) == cur) {
                        count2++;
                        j++;
                    } 
                    else break;
                }
                if (count2 > count1) return false;
            } else {
                i++;
                j++;
            }
        }
        // s.charAt(i) != p.charAt(j)
        else {
            if (j + 1 < len2 && p.charAt(j + 1) == '*') j += 2;
            else return false;
        }
        
        return matchRegex(s, p, i, j);
    }
}