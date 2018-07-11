class Solution {
    public int numTrees(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i/2; j++) {
                dp[i] += dp[i-j]*dp[j-1]*2;
            }
            if (i%2 == 1) {
                int middle = (i+1)/2;
                dp[i] += dp[i-middle]*dp[middle-1];
            }
        }
        return dp[n];
    }
}