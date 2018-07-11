/*
Use an extra boolean array " boolean[] used" to indicate whether the element has been added to list.

Sort the array "int[] nums" to make sure we can skip the same value.

when a number is equal to its previous one, we can only use this number if its previous is used
*/
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        helper(res, list, nums, used);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new LinkedList(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            else if (i > 0 && nums[i] == nums[i - 1] && !used[i-1]) continue;
            
            used[i] = true;
            list.add(nums[i]);
            helper(res, list, nums, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
     }
}