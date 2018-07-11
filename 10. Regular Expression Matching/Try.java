class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0; // current index in s
        int j = 0; // current index in p
        int lens = s.length(), lenp = p.length();
        
        while (i <= lens && j < lenp) {
            // if s reaches the end
            if (i == lens) {
                if (j + 1 < lenp && p.charAt(j + 1) == '*') {
                    if (j == lenp - 2) return true;
                    else j += 2;
                } else return false;
            }
            // if p[j] == '.'
            else if (p.charAt(j) == '.') {
                if (j + 1 < lenp && p.charAt(j + 1) == '*') {
                    if (j == lenp - 2) return true;
                    else j += 2;
                } else {
                    i++;
                    j++;
                }   
            }
            // else if s[i] == p[j]
            else if (s.charAt(i) == p.charAt(j)) {
                if (j + 1 < lenp && p.charAt(j + 1) == '*') {
                    char cur = s.charAt(i);
                    int count1 = 1; // count number of consecutive elements == current char starting from index i in s
                    int count2 = 0; // count number of consecutive elements == current char starting after * in p
                    i++;
                    j += 2;
                    while (i < lens && s.charAt(i) == cur) {
                        count1++;
                        i++;
                    }
                    while (j < lenp && p.charAt(j) == cur) {
                        count2++;
                        j++;
                    }
                    if (count2 > count1) return false;
                    /*
                    else {
                        // continue on i and j
                    }
                    */
                } else {
                    i++;
                    j++;
                }
            }
            // else: s[i] != p[j]
            else {
                if (j + 1 < lenp && p.charAt(j + 1) == '*') {
                    j += 2; // skip p[j] and p[j+1](which is '*')
                } else {
                    return false;
                }
            }
        }
        
        if (i == lens && j == lenp) return true;
        else return false;
    }
}