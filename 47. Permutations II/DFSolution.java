/*
交换是个非常好的思路。

相对上一道题目(LC.46)，主要有1点变化。

把第i个和第i个右边的元素(假设为第j个)进行swap的过程中，那么出现下面的情况i和j就可以不用交换了：
j元素和j元素之前的某个元素相同。因为，既然两元素相同，那swap之后和swap之前没有区别。

另外要说明的是，网上很多代码都先sort了输入数组但是再进行dfs，实际上sort是可以去掉的。
*/

//DFS Solution
//TIME Comlexity = O(N*N!) since we generate all the permutations, aka all the subsets.
//Total number of permutations/subsets = N*(N-1)*(N-2)*...*2*1 = N!
//And for each permutation, we need to check duplicates with all numbers in arr before current,
//And checkDuplicate takes O(N) time each
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        //Arrays.sort(nums); //No need for this
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        helper(res, list, nums, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        if (start == nums.length) {
            res.add(new LinkedList(list));
            return;
        }
        
        //把num[start]和num[start]右边的所有点(包括num[start]自己)依次进行交换。 
        for (int i = start; i < nums.length; i++) {
            //如果有两个元素交换后permutation没有变化，则不进行交换
            //If we meet a duplicate, skip to next. -->avoid duplicates
            if (checkDuplicate(nums, start, i)) continue;

            list.add(nums[i]);
            swap(nums, i, start);
            helper(res, list, nums, start + 1); //对start右边的数进行dfs
            list.remove(start); //same as list.remove(list.size() - 1);
            swap(nums, i, start); //该位置的dfs完成之后把array还原
        }
     }
    
    //num[target]是否和num[start] ~ num[target-1]中的任何一个数一样，如果一样的话，
    //swap之后没有变化，说明没有必要swap.  -->To avoid duplicate answer
    private boolean checkDuplicate(int[] nums, int start, int target) {
        for (int i = start; i<= target - 1; i++) {
            if (nums[i] == nums[target]) return true;
        }
        return false;
    }
    
    private void swap(int[] nums, int a, int b) {
        if (a == b) return;
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }
}