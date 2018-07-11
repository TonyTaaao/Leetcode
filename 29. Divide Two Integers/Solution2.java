/***
Keep substracting divisor and double it each time. Whenever you overshoot and divisor goes more than 
dividend, let divisor and count go back to previous value (divided by 2), and try again.
*/
class Solution {
    public int divide(int a, int b) {
        if(a==Integer.MIN_VALUE){
            if(b==-1) return Integer.MAX_VALUE;
        }

        long x = a < 0 ? -(long)a : (long)a;
        long y = b < 0 ? -(long)b : (long)b;

        int res = recurse(x, y, 1);
        if(a < 0 && b < 0) return res;
        if(a < 0 || b < 0) return -res;
        return res;
    }

    public int recurse(long x, long y, int count) { //count is a power of 2 (2^N), consider count as base==2^N
        if(x == 0 || count==0) return 0; //if no remainder or remainder is too small (smaller than original y)

        //if dividend >= divisor, go to the next power of 2
        if (x >= y) return count + recurse(x-y, y+y, count+count);
        //if overshot, divide base by 2 and try again.
        else return recurse(x, y >>> 1, count >>> 1); //must use >>> (unsigned shift), not >> (signed shift)
    }
}

/***
The difference between this and Solution.java is that:

Solution.java uses dividend = (a*2^N + b*2^N-1 + c*2^N-2 + ... + k*2^1 + m*1)*divisor, (a,b,c,...,k,m) = 0 or 1
and add count from most significant bit to least significant bit

Solution2.java uses dividend = (a*1 + b*2^1 + c*2^2 + ... + k*2^N-1 + m*2^N)*divisor, (a,b,c,...,k,m) = 0 or 1
and add count from least significant bit to most significant bit
When meeting a remainder (remainder=x < y=2^N*original_y), we go back to N-1, N-2,N-3... and 
add remainder/2^N-1, remainder/2^N-2, remainder/2^N-3,...

An example will illustrate this more clearly:
Eg, x=128, y=2, x/y=64
Solution.java Approach: x = 2^6*y +0(remainder), <--64=2^6+0, return 64.
Solution2.java Approach:  64=(1+2+4+8+16) +31(remainder), but 31<32(next value of count after 16),
so we let count go back from 32 to 16 and see: 31 = 16+8+4+2+1 +0(remainder).
-->Steps: 64=(1+2+4+8+16) +31(31<32,-->31>16,-->31=16+8+4+2+1+0)
now remainder == 0, return 64.
*/