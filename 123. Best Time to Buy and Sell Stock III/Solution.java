// Solution 1: Recursion
// Time Limit Exceeded
class Solution {
    public int maxProfit(int[] prices) {
        return transaction(prices, 0, 0);
    }
    
    // start = we can buy stock on or after this start date (date == index)
    // trans = number of transactions completed
    private int transaction(int[] prices, int start, int trans) {
        // if we have already completed two transactions, we cannot do more transactions, return 0.
        if (trans == 2) return 0;
        int length = prices.length;
        // if start date is the last element in array, it means we buy the stock on the last day,
        // and we do not have more days to sell the stock --> zero profit
        if (start >= length - 1) return 0;
        
        int maxprofit = 0;
        HashSet<Integer> visited = new HashSet<>();
        // we can buy stock on or after start date, i = date we buy the stock, j = date we sell the stock
        for (int i = start; i < length - 1; i++) {
            if (visited.contains(prices[i])) continue;
            else visited.add(prices[i]);
            for (int j = i + 1; j < length; j++) {
                // buy stock on day i, sell stock on day j, j has to be after i because we must
                // buy first, then sell
                // profit = (the profit of the first stock that we buy on day i and sell on day j) +
                // the profit of the second stock we can buy after day j (from day j+1 forward)
                int curprofit = prices[j] - prices[i] + transaction(prices, j + 1, trans + 1);
                maxprofit = Math.max(maxprofit, curprofit);
            }
        }
        return maxprofit;
    }
}



// Solution 2: DP
// Time Complexity = O(Kn^2), K=2
// Space Complexity = O(Kn)
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;
        
        int K = 2;
        // dp[k, i] represents the max profit up until prices[i] (Note: NOT ending with prices[i]) using at most k transactions.
        // dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
        // dp[0, i] = 0; 0 times transation makes 0 profit
        // dp[k, 0] = 0; if we only have prices[0], we can't make any money no matter how many times you can trade
        int[][] dp = new int[K + 1][length];
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i < length; i++) {
                int max = prices[i] - prices[0];
                for (int j = 1; j < i; j++) {
                    max = Math.max(max, prices[i] - prices[j] + dp[k - 1][j - 1]);
                }
                dp[k][i] = Math.max(dp[k][i - 1], max);
            }
        }
        return dp[K][length - 1];
    }
}

// version 2
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;
        
        int K = 2;
        // dp[k, i] represents the max profit up until prices[i] (Note: NOT ending with prices[i]) using at most k transactions.
        // dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
        // dp[0, i] = 0; 0 times transation makes 0 profit
        // dp[k, 0] = 0; if we only have prices[0], we can't make any money no matter how many times you can trade
        int[][] dp = new int[K + 1][length];
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i < length; i++) {
                int max = 0;
                for (int j = 0; j < i; j++) {
                    max = Math.max(max, prices[i] - prices[j] + (j == 0 ? 0 :dp[k - 1][j - 1]));
                }
                dp[k][i] = Math.max(dp[k][i - 1], max);
            }
        }
        return dp[K][length - 1];
    }
}




// Solution 3: DP (optimized)
// Time Complexity = O(Kn), K=2
// Space Complexity = O(Kn)

// dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
// dp[k, i] = max(dp[k, i-1], max(prices[i] - prices[j] + dp[k-1, j-1]) for j=[0..i-1])
//          = max(dp[k, i-1], prices[i] + max(-prices[j] + dp[k-1, j-1])for j=[0..i-1])
//          = max(dp[k, i-1], prices[i] - min(prices[j] - dp[k-1, j-1])for j=[0..i-1])
// In the above code, max is repeated calculated. It can be easily improved as this:
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;
        
        int K = 2;
        // dp[k, i] represents the max profit up until prices[i] (Note: NOT ending with prices[i]) using at most k transactions.
        // dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
        // dp[0, i] = 0; 0 times transation makes 0 profit
        // dp[k, 0] = 0; if we only have prices[0], we can't make any money no matter how many times you can trade
        int[][] dp = new int[K + 1][length];
        for (int k = 1; k <= K; k++) {
            int max = -prices[0];
            for (int i = 1; i < length; i++) {
                max = Math.max(max, -prices[i] + dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] + max);
            }
        }
        return dp[K][length - 1];
    }
}

//  version 2
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;
        
        int K = 2;
        // dp[k, i] represents the max profit up until prices[i] (Note: NOT ending with prices[i]) using at most k transactions.
        // dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
        // dp[0, i] = 0; 0 times transation makes 0 profit
        // dp[k, 0] = 0; if we only have prices[0], we can't make any money no matter how many times you can trade
        int[][] dp = new int[K + 1][length];
        for (int k = 1; k <= K; k++) {
            int min = prices[0];
            for (int i = 1; i < length; i++) {
                min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
            }
        }
        return dp[K][length - 1];
    }
}



// Solution 3: swap loops
// beats 68%
// We need to save min for each transaction, so there are k 'min'.
// We can find the second dimension (variable i) is only dependent on the previous 
// one (i-1), so we can compact this dimension. (We can choose the first dimension 
// (variable k) as well since it is also only dependent on its previous one k-1, but 
// can't compact both.)
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;
        
        int K = 2;
        // dp[k, i] represents the max profit up until prices[i] (Note: NOT ending with prices[i]) using at most k transactions.
        // dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1, j-1]), j=[0..i-1]
        // dp[0, i] = 0; 0 times transation makes 0 profit
        // dp[k, 0] = 0; if we only have prices[0], we can't make any money no matter how many times you can trade
        int[][] dp = new int[K + 1][length];
        int[] min = new int[K + 1];
        for (int i = 0; i <= K; i++) min[i] = prices[0];

        
        for (int i = 1; i < length; i++) {
            for (int k = 1; k <= K; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min[k]);
            }
        }
        return dp[K][length - 1];
    }
}



// Solution 4 (optimized)
// Time Complexity = O(Kn), K=2
// Space Complexity = O(K)
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length <= 1) return 0;
        
        int K = 2;
        int[] dp = new int[K + 1];
        int[] min = new int[K + 1];
        for (int i = 0; i <= K; i++) min[i] = prices[0];

        
        for (int i = 1; i < length; i++) {
            for (int k = 1; k <= K; k++) {
                min[k] = Math.min(min[k], prices[i] - dp[k - 1]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }
        return dp[K];
    }
}