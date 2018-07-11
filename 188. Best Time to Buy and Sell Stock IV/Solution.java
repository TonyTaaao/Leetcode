// Solution 1: DP (2D array to store temporary answers)
// Time = O(kn^2), Space = O(kn)
// Memory Limit Exceeded
// Time Limit Exceeded
// DP equation: dp[k][j] = max(dp[k][j - 1], max(price[j]-price[i]+dp[k-1][i]) for i = 0~j-1)
class Solution {
    public int maxProfit(int K, int[] prices) {
        int length = prices.length;
        if (length == 0) return 0;
        
        // dp[k][j] stores max profit we can get until index j, using at most k transactions
        // Note that max profit UNTIL index j does not mean the last transaction has to end AT j, it can be anywhere from 1 to j
        int[][] dp = new int[K + 1][length];
        
        for (int k = 1; k <= K; k++) {
            for (int j = 1; j < length; j++) {
                int max = 0;
                for (int i = 0; i < j; i++) {
                    max = Math.max(max, dp[k - 1][i] + (prices[j] > prices[i] ? prices[j] - prices[i] : 0));
                }
                dp[k][j] = Math.max(dp[k][j - 1], max);
            }
        }
        return dp[K][length - 1];
    }
}



// Solution 1 Optimization: DP array is 1D
// Time = O(kn^2), Space = O(n)
// Time Limit Exceeded
class Solution {
    public int maxProfit(int K, int[] prices) {
        int length = prices.length;
        if (length == 0) return 0;
        
        // dp[k][j] stores max profit we can get until index j, using at most k transactions
        // Note that max profit UNTIL index j does not mean the last transaction has to end AT j, it can be anywhere from 1 to j
        int[] dp = new int[length];
        int[] temp = new int[length];
        
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < length; i++)
                temp[i] = dp[i];
            
            for (int j = 1; j < length; j++) {
                int max = 0;
                for (int i = 0; i < j; i++) {
                    max = Math.max(max, temp[i] + (prices[j] > prices[i] ? prices[j] - prices[i] : 0));
                }
                dp[j] = Math.max(dp[j - 1], max);
            }
        }
        return dp[length - 1];
    }
}




// Solution 1 Optimzation: Remove the loop for i
// DP equation: dp[k][j] = max(dp[k][j - 1], max(price[j]-price[i]+dp[k-1][i]) for i = 0~j-1)
//                       = max(dp[k][j - 1], price[j] + max(dp[k-1][i]-price[i]) for i = 0~j-1)
// Time = O(kn), Space = O(kn)
// Memory Limit Exceeded
class Solution {
    public int maxProfit(int K, int[] prices) {
        int length = prices.length;
        if (length == 0) return 0;
        
        // dp[k][j] stores max profit we can get until index j, using at most k transactions
        // Note that max profit UNTIL index j does not mean the last transaction has to end AT j, it can be anywhere from 1 to j
        int[][] dp = new int[K + 1][length];
        
        for (int k = 1; k <= K; k++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j < length; j++) {
                max = Math.max(max, dp[k - 1][j - 1] - prices[j - 1]);
                dp[k][j] = Math.max(dp[k][j - 1], prices[j] + max);
            }
        }
        return dp[K][length - 1];
    }
}



// Solution 1 Optimzation: Remove the loop for i AND reduce dp array to 1D
// DP equation: dp[k][j] = max(dp[k][j - 1], max(price[j]-price[i]+dp[k-1][i]) for i = 0~j-1)
//                       = max(dp[k][j - 1], price[j] + max(dp[k-1][i]-price[i]) for i = 0~j-1)
// Time = O(kn), Space = O(n)
// Time Limit Exceeded
class Solution {
    public int maxProfit(int K, int[] prices) {
        int length = prices.length;
        if (length == 0) return 0;
        
        // dp[k][j] stores max profit we can get until index j, using at most k transactions
        // Note that max profit UNTIL index j does not mean the last transaction has to end AT j, it can be anywhere from 1 to j
        int[] dp = new int[length];
        
        for (int k = 1; k <= K; k++) {
            int max = Integer.MIN_VALUE;
            int prev = dp[0];
            for (int j = 1; j < length; j++) {
                int temp = dp[j];
                max = Math.max(max, prev - prices[j - 1]);
                dp[j] = Math.max(dp[j - 1], prices[j] + max);
                prev = temp;
            }
        }
        return dp[length - 1];
    }
}

// more concise version
class Solution {
    public int maxProfit(int K, int[] prices) {
        int length = prices.length;
        if (length == 0) return 0;
        int[] dp = new int[length];
        
        for (int k = 1; k <= K; k++) {
            int max = -prices[0];
            for (int j = 1; j < length; j++) {
                int temp = dp[j];
                dp[j] = Math.max(dp[j - 1], prices[j] + max);
                max = Math.max(max, temp - prices[j]);
            }
        }
        return dp[length - 1];
    }
}



// Solution 1 Optimization: based on solution 1, we add a scenario if k >= len/2, and if so,
// we can quickly solve the problem by adding all risings.
// Time = O(n) best case (quick solve), O(kn) otherwise
// Space = O(1) best case (quick solve), O(kn) otherwise
// beats 99.96%
class Solution {
    public int maxProfit(int K, int[] prices) {
        int length = prices.length;
        if (length == 0) return 0;
        if (K >= length / 2) return quickSolve(prices);
        
        // dp(k,j) is the max profit for up to k transactions by time j (0<=k<=K, 0<=j<=length)
        int[][] dp = new int[K + 1][length];
        
        for (int k = 1; k <= K; k++) {
            int max = dp[k - 1][0] - prices[0];
            for (int j = 1; j < length; j++) {
                dp[k][j] = Math.max(dp[k][j - 1], prices[j] + max);
                max = Math.max(max, dp[k - 1][j] - prices[j]);
            }
        }
        return dp[K][length - 1];
    }
    
    private int quickSolve(int[] prices) {
        int profit = 0;
        int length = prices.length;
        for (int i = 1; i < length; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}



// Best Optimized Solution
// same as above, dp array is reduced to 1D
// Time = O(n) best case (quick solve), O(kn) otherwise
// Space = O(1) best case (quick solve), O(n) otherwise
// beats 99.96%
class Solution {
    public int maxProfit(int K, int[] prices) {
        int length = prices.length;
        if (length == 0) return 0;
        if (K >= length / 2) return quickSolve(prices);
        
        // dp(k,j) is the max profit for up to k transactions by time j (0<=k<=K, 0<=j<=length)
        int[] dp = new int[length];
        
        for (int k = 1; k <= K; k++) {
            int max = -prices[0];
            for (int j = 1; j < length; j++) {
                int temp = dp[j];
                dp[j] = Math.max(dp[j - 1], prices[j] + max);
                max = Math.max(max, temp - prices[j]);
            }
        }
        return dp[length - 1];
    }
    
    private int quickSolve(int[] prices) {
        int profit = 0;
        int length = prices.length;
        for (int i = 1; i < length; i++) {
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}