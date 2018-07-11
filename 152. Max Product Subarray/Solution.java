class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        int max = nums[0], min = nums[0], result = max; //keep max AND min since min x a negative number could become max
        for (int i = 1; i < nums.length; i++) {
            //max is the largest among nums[i], max*nums[i], min*nums[i]
            int temp = max;
            max = Math.max(Math.max(nums[i], max*nums[i]), min*nums[i]);
            min = Math.min(Math.min(nums[i], temp*nums[i]), min*nums[i]);
            if (max > result) result = max;
        }
        return result;
    }
}

//Clearer solution using DP array: Space = O(n)
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] maxproducts = new int[nums.length];
        int[] minproducts = new int[nums.length];
        maxproducts[0] = nums[0];
        minproducts[0] = nums[0];
        int max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //max contiguous product ending with nums[i]
            maxproducts[i] = Math.max(Math.max(maxproducts[i-1]*nums[i], minproducts[i-1]*nums[i]), nums[i]);
            //min contiguous product ending with nums[i]
            minproducts[i] = Math.min(Math.min(maxproducts[i-1]*nums[i], minproducts[i-1]*nums[i]), nums[i]);
            max = Math.max(max, maxproducts[i]);
            min = Math.min(min, minproducts[i]);
        }
        return max;
    }
}
//For this solution, since we only need maxproducts[i-1] to get maxproducts[i], all the preceeding 
//elements are not needed, so we can only keep maxproducts[i-1] and discard the remaining array.
//As a result, maxproducts[] of size n --> max (max contiguous product preceeding it), same with min
//-->Save space to O(1)