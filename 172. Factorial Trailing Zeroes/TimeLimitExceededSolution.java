//right algorithm, takes too much time for large input
class Solution {
    public int trailingZeroes(int n) {
        int tens = 0;
        n = n - n % 5;
        while (n >= 5) {
            int cur = n;
            while (cur % 10 == 0) {
                tens++;
                cur /= 10;
            }
            while (cur % 5 == 0) {
                tens++;
                cur /= 5;
            }
            n = n - 5;
        }
        return tens;
    }
}