class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                nums[i]++;
                int temp = nums[i];
                int j = i + 1;
                int k = nums.length - 1;
                while (j <= k) {
                    if (nums[j] == temp) nums[j]--;
                    else if (nums[k] == temp) nums[k]--;
                    swap(nums, j++, k--);
                }
                return;
            }
        }
        
        //if next permutation is impossible
        int j = 0;
        int k = nums.length - 1;
        while (j < k) {
            swap(nums, j++, k--);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}