class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }
    
    // quick sort --> O(nlogn) average, O(n^2) worst case.
    private int findKthLargest(int[] nums, int low, int high, int K) {
        int pivot = nums[low];
        int l = low;
        int h = high;
        while (l < h) {
            while (l < h && nums[h] >= pivot) {
                h--;
            }
            if (l < h) {
                swap(nums, l, h);
                l++;
            }
            while (l < h && nums[l] <= pivot) {
                l++;
            }
            if (l < h) {
                swap(nums, l, h);
                h--;
            }
        }
        if (l == nums.length - K) return nums[l];
        else if (l < nums.length - K) return findKthLargest(nums, l + 1, high, K);
        else return findKthLargest(nums, low, l - 1, K);
    }
    
    private void swap(int[] nums, int l, int h) {
        int temp = nums[l];
        nums[l] = nums[h];
        nums[h] = temp;
    }
}