class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new LinkedList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> ans = res.get(j);
                List<Integer> newL = new LinkedList(ans);
                newL.add(nums[i]);
                res.add(newL);
            }
        }
        return res;
    }
}