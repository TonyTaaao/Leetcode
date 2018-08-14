// Space Complexity = O(n) due to leftProduct[] and rightProduct[], Time Complexity = O(n)
// beats 0%
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int length = nums.length;
        //if (length == 1) return new int[]{1};
        int[] leftProduct = new int[length];
        int[] rightProduct = new int[length];
        leftProduct[0] = nums[0];
        rightProduct[length - 1] = nums[length - 1];
        for (int i = 1; i < length - 1; i++) {
            leftProduct[i] = nums[i] * leftProduct[i - 1];
        }
        for (int i = length - 2; i > 0; i--) {
            rightProduct[i] = nums[i] * rightProduct[i + 1];
        }
        
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            int leftproduct = i == 0 ? 1 : leftProduct[i - 1];
            int rightproduct = (i == length - 1) ? 1 : rightProduct[i + 1];
            ans[i] = leftproduct * rightproduct;
        }
        return ans;
    }
}



// Space Complexity = O(1), Time Complexity = O(n)
// beats 100%
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        int length = nums.length;
        //if (length == 1) return new int[]{1};
        int[] ans = new int[length];
        ans[0] = 1;
        for (int i = 1; i < length; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        int temp = 1;
        for (int i = length - 2; i >= 0; i--) {
            temp = temp * nums[i + 1];
            ans[i] = ans[i] * temp;
        }
        return ans;
    }
}