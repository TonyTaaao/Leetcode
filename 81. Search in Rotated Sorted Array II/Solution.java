class Solution {
    public boolean search(int[] nums, int target) {
        //edge case: nums is empty
        if (nums.length == 0) return false;
        
        int lo = 0, hi = nums.length - 1;
        int mid;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if (target == nums[mid]) return true;
            
            if (nums[mid] > nums[hi]) { //if rotate is on right side, meaning left side is sorted for sure
                //if target falls into the sorted side
                if (target >= nums[lo] && target <= nums[mid]) hi = mid;
                else lo = mid + 1;
            }
            else if (nums[mid] < nums[hi]) { //if right side is sorted for sure
                if (target >= nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid;
            }
            //deal with nums[mid] == nums[hi]
            else if (nums[lo] > nums[mid]) { //if rotate is on left side, right side is sorted for sure
                if (target >= nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid;
            }
            else { //nums[lo] == nums[mid] == nums[hi], unsure about which side is sorted
                hi--;
            }
        }
        return false;
    }
}