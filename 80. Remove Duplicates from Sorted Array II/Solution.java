//Solution 1
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 2) return nums.length;
        
        int length = 1;
        int count = 1; //count the number of duplicates for current number
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[length - 1]) { //OR: if (nums[i] != nums[i - 1])
                nums[length++] = nums[i];
                count = 1;
            } else if (count < 2) {
                nums[length++] = nums[i];
                count++;
            }
        }
        return length;
    }
}



//Solution 2
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length <= 2) return nums.length;
        
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) nums[i++] = n;
        }
        return i;
    }
}


//Solution 1 -->O(N) Time solution when duplicates are allowed at most K times.
/*
I think both Remove Duplicates from Sorted Array I and II could be solved in a 
consistent and more general way by allowing the duplicates to appear k times 
(k = 1 for problem I and k = 2 for problem II). Here is my way: we need a count 
variable to keep how many times the duplicated element appears, if we encounter 
a different element, just set counter to 1, if we encounter a duplicated one, we 
need to check this count, if it is already k, then we need to skip it, otherwise, 
we can keep this element. The following is the implementation and can pass both OJ:
*/
class Solution {
    public int removeDuplicates(int[] nums, int k) {
        if (nums.length <= k) return nums.length;
        
        int length = 1;
        int count = 1; //count the number of duplicates for current number
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[length++] = nums[i];
                count = 1;
            } else if (count < k) {
                nums[length++] = nums[i];
                count++;
            }
        }
        return length;
    }
}