// beats 55.09%
// Time Complexity = O(n^2)
/*
The idea is to sort an input array and then run through all indices of a possible first 
element of a triplet. For each possible first element we make a standard bi-directional 
2Sum sweep of the remaining part of the array. Also we want to skip equal elements to 
avoid duplicates in the answer without making a set or smth like that.
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < length - 2; i++) {
            // skip duplicate elements
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int lo = i + 1;
            int hi = length - 1;
            int target = 0 - nums[i];
            while (lo < hi) {
                if (nums[lo] + nums[hi] == target) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    lo++; hi--;
                    // skip duplicate elements
                    while (lo < hi && nums[lo] == nums[lo - 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi + 1]) hi--;
                } else if (nums[lo] + nums[hi] < target) lo++;
                else hi--;
            }
        }
        return res;
    }
}