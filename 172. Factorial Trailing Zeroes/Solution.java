//Time Complexity = O(log(5, N))
class Solution {
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n/5 + trailingZeroes(n/5);
    }
}



//Iterative version
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
         while (n >= 5) {
             n /= 5;
             res += n;
         }
        return res;
    }
}