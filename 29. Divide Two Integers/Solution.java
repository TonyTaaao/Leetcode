/***Solve the problem using this equation:
dividend = (a*2^N + b*2^N-1 + c*2^N-2 + ... + k*2^1 + m*1)*divisor, (a,b,c,...,k,m) = 0 or 1
similar to binary number conversion, from most to least significant bit
*/

/***Special case: Integer.MIN_VALUE/(-1) will cause an overflow.
Reason: 32-bit signed integer range: [-2^31,  2^31 - 1].
Integer.MIN_VALUE/(-1) is -Integer.MIN_VALUE, a positive number greater than Integer.MAX_VALUE,
so it will cause an overflow.
*/

/***NOTE:
Only division involving negative number(s) may cause overflow, division of two positive numbers won't overflow.
Reason: integer range == [-2^31,  2^31 - 1], positive limit(2^31 - 1) < negative limit(2^31).
In this question, divisor is a non-zero Integer, meaning its absolute value must be >= 1.
If Number A is divided by a divisor whose abs >= 1, the result must be <= abs(Number A).

(positive number a)/(positive number b) will always be <= positive number a, and since
(positive number a) is a valid integer (it is int type), the result must be valid. -->No overflow

Negative division: 
Overflow only happens if abs(divisor) == 1, otherwise divisor >=2-->(2^31)/2 < 2^31-1, no overflow.
If an overflow happens, then abs(divisor) must be 1, and abs(Dividend) must be 2^31, since it is
the only value > Integer.MAX.
-->This will only happen if -2^31 / -1, resulting in a positive number > Integer.MAX==2^31-1.
So the only case when overflow happens is Integer.MIN / (-1).
*/

//Recursive
class Solution {
    public int divide(int dividend, int divisor) {
        //if (divisor == 0) return Integer.MAX_VALUE;
        
        //Reduce the problem to positive long integer to make it easier.
        //Use long to avoid integer overflow cases.
        boolean negative = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) 
            negative = true;

        //boolean negative = (dividend > 0) ^ (divisor > 0) ? true : false; //Use XOR to determine same sign
        
        long ans = binaryDivide(Math.abs((long) dividend), Math.abs((long) divisor));
        //Handle overflow: a positive number greater than Integer.MAX_VALUE will cause an overflow.
        if (!negative && ans > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        //Also, a negative number whose absolute value greater than Integer.MIN_VALUE will cause an overflow, but we do not need to consider it here since such case won't happen in this scenario.
        else return negative ? (int) (-ans) : (int) ans;
    }
    
    private long binaryDivide(long dividend, long divisor) {
        // Recursion exit condition
        if (dividend < divisor) return 0;
        
        //  Find the largest multiple such that (divisor * multiple <= dividend), 
        //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
        //  Think this as a binary search.
        long multiple = 1; //This number is always 2^N, starting from 1 to 2,4,8,16,32...
        long sum = divisor; //sum = multiple*divisor = 2^N * divisor

        //Find Most significant bit. After this while loop, multiple == Most significant bit == 2^N
        while (sum + sum <= dividend) {
            multiple += multiple; //multiple*=2 each time, so it is a power of 2
            sum += sum;
        }
        //Look for the next valid bit (non-zero 2^N) from the reminder (dividend - sum) recursively.
        return multiple + binaryDivide(dividend - sum, divisor);
    }
}

//Iterative
class Solution {
    public int divide(int dividend, int divisor) {
        //XOR: ^
        boolean positive = (dividend > 0) ^ (divisor > 0) ? false : true;
        
        long remainder = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        long res = 0;
        long multiple;
        long sum;
        
        while (remainder >= dvs) {
            multiple = 1; //reset multiple, start from 1 to 2,4,8,16...
            sum = dvs; //reset sum to 1*dvs, will go to 2*dvs, 4*dvs, 8*dvs, 16*dvs...
            while (remainder >= (sum << 1)) { //sum << N == sum*2^N
                sum <<= 1;
                multiple <<= 1;
            }
            res += multiple; //multiple == Most significant bit
            remainder -= sum;
        }
        if (!positive) res = -res;
        //if res is POSITIVE and greater than Integer.max, cause an overflow.
        //if res is negative, overflow won't happen
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
    }
}


//No Use of Long Java Solution
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; //edge case when overflow happens
        }

        if (dividend > 0 && divisor > 0) {
            return divideHelper(-dividend, -divisor);
        } else if (dividend > 0) {
            return -divideHelper(-dividend, divisor);
        }
        else if(divisor > 0) {
            return -divideHelper(dividend, -divisor); //if -2^31/1, divideHelper() will return 2^31-1, 
            //since result in divideHelper() is a positive number and cannot go beyond Integer.MAX,
            //but -(2^31-1) == -2^31, aka -Integer.MAX == Integer.MIN, you can try it.
            //eg. if int is only 3-bit, its range will be [-2^3, 2^3-1] == [-8, 7].
            //7 == 0b111, flipped to get negative number--> -7 =-(0b111) = 0b(000+overhead)=0b(1000)=-8
            //So -Integer.MAX == Inter.MIN automatically in Java
        }
        else {
            return divideHelper(dividend, divisor);
        }
    }

    //Here both dividend and divisor is negative, since negative integer has wider range than positive
    //integer, and Integer.MIN converting to Integer.MAX will cause overflow, so we use negative number
    //division to prevent it.
    private int divideHelper(int dividend, int divisor) {
        int result = 0;
        int currentDivisor = divisor;
        while(true) {
            if(dividend > divisor) {
                break;
            }
            int temp = 1;
            while(dividend <= currentDivisor << 1 && currentDivisor << 1 < 0) {
                temp = temp << 1;
                currentDivisor = currentDivisor << 1;
            }
            dividend -= currentDivisor;
            result += temp;
            currentDivisor = divisor;
        }
        return result;
    }
}