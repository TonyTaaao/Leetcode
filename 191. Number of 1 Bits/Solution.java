// Solution 1 : loop through each bit and count
/*
 We check each of the 32 bits of the number. If that bit is 1, 
 we add one to the number of 1-bits.

 The run time depends on the number of bits in n. Because n in this scenario 
 is a 32-bit integer, the time complexity is O(32) ~ O(1).

 The space complexity is O(1), since no additional space is allocated.
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) count++; // if the last bit of n is 1, increment count
            n >>= 1; // delete the last bit of 1 through right shift
        }
        return count;
    }
}


// Solution 1, version 2: Use mask
/*
We can check the i-th bit of a number using a bit mask. 
We start with a mask m=1, because the binary representation of 1 is,

0000 0000 0000 0000 0000 0000 0000 0001

Clearly, a bitwise AND between any number and the mask m=1 gives us 
the least significant bit of this number. To check the next bit, we shift 
the mask to the left by one.

0000 0000 0000 0000 0000 0000 0000 0010

And so on.
*/
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0; // count number of 1 bits
        int mask = 1;
        for (int i = 0; i < 32; i++) {
        	// cannot use (n & mask) > 0 here, because n is unsigned,
        	// so n could be > Integer.MAX_VALUE
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }
}