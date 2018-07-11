//beats 80.12% runtime
//Time Complexity = O(N*K), K = average length of previous solution
//Space Complexity = O(K) due to K characters stored in res and curRes
class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        
        StringBuilder res = new StringBuilder("11");
        while (n-- > 2) {
            int count = 1;
            StringBuilder curRes = new StringBuilder();
            for (int i = 1; i < res.length(); i++) {
                if (res.charAt(i) == res.charAt(i - 1)) count++;
                else {
                    curRes.append(count);
                    curRes.append(res.charAt(i - 1));
                    count = 1;
                }
                
                if (i == res.length() - 1) {
                    curRes.append(count);
                    curRes.append(res.charAt(i));
                }
            }
            res = curRes;
        }
        return res.toString();
    }
}



//Version 2, put line 20-23 at the end of for loop
class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        
        StringBuilder res = new StringBuilder("11");
        while (n-- > 2) {
            int count = 1;
            StringBuilder curRes = new StringBuilder();
            for (int i = 1; i < res.length(); i++) {
                if (res.charAt(i) == res.charAt(i - 1)) count++;
                else {
                    curRes.append(count);
                    curRes.append(res.charAt(i - 1));
                    count = 1;
                }
            }
            curRes.append(count).append(res.charAt(res.length() - 1));
            res = curRes;
        }
        return res.toString();
    }
}



//Version 3, same idea
class Solution {
    public String countAndSay(int n) {
        StringBuilder res = new StringBuilder("1");
        StringBuilder prev;
        int count;
        char say;
        
        while (n-- > 1) {
            prev = res;
            res = new StringBuilder();
            count = 1;
            say = prev.charAt(0);
            
            for (int i = 1, len = prev.length(); i < len; i++) {
                if (prev.charAt(i) == say) count++;
                else {
                    res.append(count).append(say);
                    say = prev.charAt(i);
                    count = 1;
                }
            }
            res.append(count).append(say);
        }
        return res.toString();
    }
}