class Solution {
    public int singleNumber(int[] nums) {
        //Math: 2*(a+b+c) - (a+a+b+b+c) = c
        Set<Integer> set = new HashSet();
        int arr_sum = 0, set_sum = 0;
        for (int i = 0; i < nums.length; i++) {
            arr_sum += nums[i];
            if (set.add(nums[i])) set_sum += nums[i];
        }
        return 2*set_sum - arr_sum;
    }
}