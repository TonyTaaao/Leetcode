/*This is not a solution to LC.89 Gray Code, but an algorithm to generate all 
n-bit binary numbers from 0 to (2^n-1) in ascending order.

Note that gray code is a binary numeral system where two successive values differ in 
only one bit, so it should not be simply ascending order.

eg. Given n = 2, this code will output [0,1,2,3], 
while the gray code sequence should be:
00 - 0
01 - 1
11 - 3
10 - 2
resulting in an output of [0,1,3,2], which is not in ascending order.
*/
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new LinkedList<>();
        res.add(0);
        helper(res, 1, n);
        return res;
    }
    
    private void helper(List<Integer> res, int prefix, int n) {
        if (n == 0) return;
        res.add(prefix);
        for (int i = 0; i <= 1; i++) {
            helper(res, 2*prefix + i, n - 1);
        }
    }
}