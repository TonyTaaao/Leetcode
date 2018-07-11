class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()+1];
        for (int i = triangle.size() - 1; i >= 0; i--) { //i is current row number
            for (int j = 0; j <= i; j++) { //j is index number on current row
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0]; //This problem is easier to do from bottom up than from top to bottom
        //Since we want to find the min path sum from ROOT (top)!!!
    }
}