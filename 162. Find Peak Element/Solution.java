/*Time Limit Exceeded
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        
        //Check boundary case: leftmost and rightmost
        int end = nums.length - 1;
        if (nums[0] > nums[1]) return 0;
        if (nums[end] > nums[end - 1]) return end;
        
        return findPeak(nums, 1, nums.length - 2);
    }
    
    private int findPeak(int[] nums, int lo, int hi) {
        if (lo == 0 || hi == nums.length-1 || lo > hi) return -1;
        
        int mid = (lo + hi) / 2;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;
        int leftSearch = findPeak(nums, lo, mid - 1);
        if (leftSearch != -1) return leftSearch;
        int rightSearch = findPeak(nums, lo + 1, hi);
        return rightSearch;
    }
}
*/

class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) return -1;
        return helper(nums, 0, nums.length-1);
    }

    public int helper(int[] nums,int start,int end){
        if (start == end) return start;
        if (start == end - 1) return (nums[start] > nums[end]) ? start : end;
        
        int mid = (start + end) / 2;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
            return mid;
        else if (nums[mid - 1] > nums[mid]) { //OR: else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1])
            return helper(nums, 0, mid - 1);
        } else {
            return helper(nums, mid + 1, end);
        }
    }
}