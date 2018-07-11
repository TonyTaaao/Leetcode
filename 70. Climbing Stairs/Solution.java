//DP without memoization (Time Limit Exceeded), Time complexity = O(2^n), Space complexity = O(n)
class Solution {
    public int climbStairs(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else if (n == 2) return 2;
        else return climbStairs(n-1) + climbStairs(n-2);
    }
}

//DP with memoization, Time complexity = O(n), Space complexity = O(n)
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        
        int[] memo = new int[n+1];
        memo[1] = 1;
        memo[2] = 2;
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }
}

//Fibonacci Number Solution, Time complexity = O(n), Space complexity = O(1)
class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        
        int first = 1, second = 2;
        for (int i = 3; i <= n; i++) {
            /***
            int third = first + second;
            first = second;
            second = third;
            */
            second += first;
            first = second - first;
        }
        return second;
    }
}