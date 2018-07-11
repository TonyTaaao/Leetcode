//DFS Solution
//TIME Comlexity = O(N!) since we generate all the permutations, aka all the subsets.
//Total number of permutations/subsets = N*(N-1)*(N-2)*...*2*1 = N!
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums.length == 0) return res;
        List<Integer> list = new LinkedList<>();
        helper(res, list, nums, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        if (start == nums.length) {
            res.add(new LinkedList(list));
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            if (i != start) swap(nums, i, start);
            helper(res, list, nums, start + 1); //Notice that the last parameter should be start+1, not i+1
            list.remove(list.size() - 1);
            if (i != start) swap(nums, i, start);
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }
}



//Same idea without using swap, just iterate through every non-visited element.
class Solution {
    public List<List<Integer>> permute(int[] nums) {
   List<List<Integer>> list = new ArrayList<>();
   backtrack(list, new ArrayList<>(), nums);
   return list;
}

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
       if(tempList.size() == nums.length){
          list.add(new ArrayList<>(tempList));
       } else{
          for(int i = 0; i < nums.length; i++){ 
             if(tempList.contains(nums[i])) continue; // element already exists/been visited, skip
             tempList.add(nums[i]);
             backtrack(list, tempList, nums);
             tempList.remove(tempList.size() - 1);
          }
       }
    }
}