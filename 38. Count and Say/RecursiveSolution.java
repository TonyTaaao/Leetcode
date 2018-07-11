//beats 80.12% runtime
//Time Complexity = O(N*K), K = average length of previous solution
class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        
        String prev = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= prev.length(); i++) {
            if (i == prev.length() || prev.charAt(i) != prev.charAt(i - 1)) {
                res.append(count).append(prev.charAt(i - 1));
                count = 1;
            } else {
                count++;
            }
        }
        return res.toString();
    }
}