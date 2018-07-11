class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        /**
        **two loops, two pointer method (low and high) within loop, same way as 3sum
        **O(n^3)
        */
        List<List<Integer>> ans = new LinkedList<>();
        if (nums.length < 4) return ans;
        
        Arrays.sort(nums); //O(nlogn)
        for (int i = 0; i < nums.length-3; i++) {
            if (nums[i] * 4 > target) break; //current element (and the rest) is too large
            if (nums[i] + 3 * nums[nums.length-1] < target) continue; //current element is too small
            if (i > 0 && nums[i-1] == nums[i]) continue; //avoid duplicates (if the first element appeared 
            //already iterate through all elements after it)
            
            for (int j = i+1; j < nums.length-2; j++) {
                if(j > i+1 &&nums[j] == nums[j-1]) continue; //avoid duplicates in list ans
                int low = j+1, high = nums.length-1;
                while (low < high) {
                    if (nums[i] + nums[j] + nums[low] + nums[high] == target) {
                        //sum == target, add to ans
                        ans.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        low++;
                        high--;
                        while (low < high && nums[low] == nums[low-1]) low++;
                        while (low < high && nums[high] == nums[high+1]) high--;
                    } else if (nums[i] + nums[j] + nums[low] + nums[high] < target) {
                        //sum < target, right shift low
                        low++;
                        //while (low < high && nums[low] == nums[low-1]) low++; //not necessary
                    } else {
                        //sum > target, left shift high
                        high--;
                        //while (low < high && nums[high] == nums[high+1]) high--; //not necessary
                    }
                }
            }
        }
        return ans;
    }
}