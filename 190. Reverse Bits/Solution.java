// beats 57.74%
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        int shift = 0; // count number of bits shift
        while (shift < 32) {
            // if the last bit of n is 1: if ((n & 1) == 1)
            // (n & 1), () cannot be omitted
            /*
            if (n % 2 == 1) is also able to check whether the last bit of n is 1,
            Why n % 2 == 1 is wrong here?
            Because when we perform division on n, we treat it as a normal int, 
            which is signed by default. range of signed integer = [-2^31, 2^31-1].
            But here n is unsigned, meaning its range is [0, 2^32-1],
            so performing divisions (n % 2) will cause overflow.
            */
            if ((n & 1) == 1) res |= 1 << (31 - shift);
            n = n >> 1; // delete the last bit
            shift++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution soln = new Solution();
        int ans = soln.reverseBits(2147483648);
        System.out.println(ans);
    }
}

// Version 2, same approach
// beats 72.07%
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result |= 1; // or: result++;
            n >>= 1;
        }
        return result;
    }
}



// Solution 2: use Integer.reverse(int i)
/*
class Integer: public static int reverse(int i)
Returns the value obtained by reversing the order of the bits in the 
two's complement binary representation of the specified int value.
*/
// beats 57.74%
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }
}