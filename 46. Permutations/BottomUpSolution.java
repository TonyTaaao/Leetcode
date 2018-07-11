public class Solution {
   public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        List<Integer> list = new ArrayList<>(); //O(1) access time at list.add(index, E) in helper method
        insert(res, list, nums);
        return res;
	}
    
    //Given the previously generated temp list, insert current element at all possible indices 
    //of the list to generate all possible permutations
    private void insert(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i <= list.size(); i++) {
            list.add(i, nums[list.size()]); //insert current element at all possible indices of list to
            //get all possible permutation combinations
            insert(res, list, nums);
            list.remove(i); //remove(int index)
        }
    }
}



//99% Faster Runtime Version, at the cost of extra space due to new lists generated 
//during for loop
public class Solution {
   public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        List<Integer> list = new ArrayList<>(); //O(1) access time at list.add(index, E) in helper method
        insert(res, list, nums);
        return res;
    }
    
    //Given the previously generated temp list, insert current element at all possible indices 
    //of the list to generate all possible permutations
    private void insert(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(list);
            return;
        }
        
        for (int i = 0; i <= list.size(); i++) {
            List<Integer> newPermutation = new ArrayList<>(list);
            newPermutation.add(i, nums[list.size()]);
            insert(res, newPermutation, nums);
        }
    }
}