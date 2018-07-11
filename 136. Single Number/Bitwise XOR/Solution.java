//This is the solution with fatest running time

class Solution {
    public int singleNumber(int[] nums) {
        //a XOR a = 0, 0 XOR a = a, XOR is mutable
        int ans = 0;
        for (int num : nums) ans ^= num;
        return ans;
    }
}

//Time Complexity = O(n)
//Space Complexity = O(1)