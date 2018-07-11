/*Binaet search approach, only works if we only need length, cannot modify the array
class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length; //answer to return
        int lo = 0, hi = length - 1;
        if (nums[lo] == hi) return 1; //Edge case: all elements are duplicates.
        
        lo++;
        hi--;
        while (lo <= hi) {
            if (nums[lo] == nums[lo - 1]) length--;
            if (nums[hi] == nums[hi + 1]) length--;
            if (nums[lo] == nums[hi]) {
                length -= (hi - lo);
                break;
            }
        }
        return length;
    }
}
*/      

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 1) return 1;
        
        int pos = 1;
        for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i-1]) nums[pos++] = nums[i];
        }
        return pos; //last index = pos - 1, length = last index + 1 = pos
    }
}