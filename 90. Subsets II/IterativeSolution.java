class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>(); //O(1) time for res.get(j)
        res.add(new LinkedList<Integer>());
        
        int newIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            int oldsize = res.size();
            int start = (i != 0 && nums[i] == nums[i - 1]) ? newIndex : 0;
            newIndex = oldsize;
            for (int j = start; j < oldsize; j++) {
                List<Integer> list = res.get(j);
                List<Integer> newL = new LinkedList<>(list);
                newL.add(nums[i]);
                res.add(newL);
            }
        }
        return res;
    }
}