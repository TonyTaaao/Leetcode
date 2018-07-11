class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        helper(res, list, nums, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        res.add(new ArrayList(list));
        if (start == nums.length) return;
        
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}



//Same idea without "start" parameter
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        helper(res, list, nums);
        return res;
    }
    
    //start is not necessary since list.size() == start
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums) {
        res.add(new ArrayList(list));
        if (list.size() == nums.length) return;
        
        for (int i = list.size(); i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums);
            list.remove(list.size() - 1);
        }
    }
}