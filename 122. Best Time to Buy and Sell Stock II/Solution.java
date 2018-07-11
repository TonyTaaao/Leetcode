//每到低谷时买，到顶峰时卖
//Solution1, O(N) time & O(1) space
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }
}




//Solution2, closer to the real problem, O(N) time & O(1) space
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int maxProfit = 0;
        int minPrice = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) minPrice = prices[i];
            else if ((i < prices.length - 1 && prices[i] > prices[i + 1]) || 
                    (i == prices.length - 1)) {
                maxProfit += prices[i] - minPrice;
                minPrice = prices[i];
            }
        }
        return maxProfit;
    }
}