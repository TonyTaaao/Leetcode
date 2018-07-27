// Solution 1: Binary Search 二分法搜索
// Time = amortized O(logn), Space = O(1)
// beats 100%
class Solution {
    public int findMin(int[] nums) {
        int low = 0, hi = nums.length - 1;
        while (low < hi) {
            int mid = (low + hi) / 2;
            if (nums[mid] > nums[hi]) { // rotate occurs at right half [mid + 1, hi]
                low = mid + 1;
            }
            else if (nums[low] > nums[mid]) { // rotate occurs at left half [low + 1, mid]
                low++;
                hi = mid;
            }
            // cases where nums[low] <= nums[mid] <= nums[hi]
            else if (nums[low] < nums[hi]) { // eg. [2,2,2,3,3] or [2,2,3,3,3] or [2,3,4,5,6], left is smallest
                return nums[low];
            } else { // case where nums[low] = nums[mid] = nums[hi]
                low++;
                hi--;
            }
        }
        return nums[low];
    }
}