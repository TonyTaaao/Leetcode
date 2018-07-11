class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        if (k == 0) return;
        
        int offset = length - k;
        if (k <= length / 2) {
            // right shift
            for (int i = length - 1; i >= k; i--) {
                int temp = nums[i];
                nums[i] = nums[i - k];
                if (i + k >= length) {
                    nums[i - offset] = temp;
                }
            }
        } else {
            // left shift
            for (int i = 0; i < k; i++) {
                int temp = nums[i];
                nums[i] = nums[i + offset];
                if (i - offset < 0) {
                    // i - offset + length = i - (length-k) + length = i + k
                    nums[i + k] = temp;
                }
            }
        }
    }
}