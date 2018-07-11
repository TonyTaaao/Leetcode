//Only Correct if no "0" in message
class Solution {
    public int numDecodings(String s) {
        if (s == null) return 0;
        int length = s.length();
        if (length == 0) return 0;
        if (length == 1) return 1;
        
        int[] dp = new int[length+1];
        dp[1] = 1;
        //public String substring(int beginIndex, int endIndex)
        //beginIndex - the beginning index, inclusive.
        //endIndex - the ending index, exclusive.
        dp[2] = Integer.valueOf(s.substring(0,2)) <= 26 ? 2 : 1;
        for (int i = 3; i <= length; i++) {
            dp[i] = dp[i-1] + dp[i-2] * (Integer.valueOf(s.substring(i-2,i)) <= 26 ? 1 : 0);
        }
        return dp[length];
    }

    public static void main(String[] args) {
        String s = null;
        Solution sol = new Solution();
        int res = sol.numDecodings(s);
        System.out.println(res);
    }
}

//True Correct solution
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int length = s.length();
        int[] dp = new int[length+1];
        dp[0] = 1; //only for convenience to intialize dp[2]
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        
        //public String substring(int beginIndex, int endIndex)
        //beginIndex - the beginning index, inclusive.
        //endIndex - the ending index, exclusive.
        for (int i = 2; i <= length; i++) {
            //dp[i] = dp[i-1] + dp[i-2] * (Integer.valueOf(s.substring(i-2,i)) <= 26 ? 1 : 0); --correct if no "0"
            int current = Integer.valueOf(s.substring(i-1, i)); //if current digit is zero, can't use dp[i-1]
            int two = Integer.valueOf(s.substring(i-2, i)); //if the digit before current is 0 or two > 26, can't use dp[i-2]
            if (current != 0) dp[i] += dp[i-1];
            if (two >= 10 && two <= 26) dp[i] += dp[i-2];
            
        }
        return dp[length];
    }
}