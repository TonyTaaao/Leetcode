class Solution {
    public int findMin(int[] nums) {
        int length = nums.length;
        
        //Find rotate index
        int lo = 0, hi = length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[hi]) { //rotate is on right side
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) { //rotate is on left side
                hi = mid; //mid偏左,nums只有两个数时会移到左边的数
            } else { //when nums[mid] == nums[hi]
                
            }
        }
        //After this, lo == rotate index
        
        return nums[lo]; //return the min number, Note that we should return the actual number, not its index
    }
}