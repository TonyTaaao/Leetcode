/*Wrong version, square = mid * mid will cause integer overflow if x is large
eg. x == Integer.MAX_VALUE, mid == (1 + x) / 2 --> mid*mid will exceed Integer.MAX_VALUE, causing overflow
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int lo = 1, hi = x;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int square = mid * mid;
            if (square == x) return mid;
            else if (square > x) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo - 1;
    }
}
*/

/*To prevent the integer overflow:

    int mid = left + (right - left)/2;
    mid > x/mid instead of mid*mid > x
*/
class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int lo = 1, hi = x;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (mid == x / mid) return mid; //mid always <= x, x/mid always <= x, won't cause overflow. 
            else if (mid > x / mid) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo - 1;
    }
}