//DFS Solution
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); //to help avoid dupliates
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        helper(res, list, candidates, target, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list,
                        int[] cands, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new LinkedList<Integer>(list));
            return;
        }
        
        for (int i = start; i < cands.length && cands[i] <= target; i++) {
            //prevent duplicates
            if (i > start && cands[i] == cands[i - 1]) continue;
            
            list.add(cands[i]);
            helper(res, list, cands, target - cands[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}
/*
Time Complexity Analysis: 
https://leetcode.com/problems/combination-sum-ii/discuss/16871/time-complexity-analysis-of-recursive-approach
-->
On the first thought, the time complexity analysis of this brute force approach looks difficult. We are going through each elements and calling recursively on each of those elements. Is it n ^ n ?

The fact that we are doing brute force gives us the answer of complexity. If you think, we are essentially selecting all possible subsets of of set.

{1,2,3} -> {1} {2} {3} {1,2} {1,3} {2,3} {1,2,3}

There are 2 ^n such elements and hence the time complexity is O(2^n)

Example:
It is easy to see this with example also. We select input that will explore all the paths such as {1,2,3,4,5,6,7} and the target is big enough so it will not prune any path. It will call the iteration 128 times.


------------------------------
Actually, I think for the time complexity it should be the sum of C(n, n) + C(n, n - 1) + C(n, n - 2) + C(n, n - 3) + .... + C(n, 1) ~= n^n
*/




//-----------------------------------------------------------------------------
//Previous version (correct but not that good)

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); //to help avoid dupliates
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        helper(res, list, candidates, target, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list,
                        int[] cands, int target, int start) {
        //If index out of bounds, we stop.
        if (start >= cands.length) return;
        
        for (int i = start; i < cands.length && cands[i] <= target; i++) {
            //prevent duplicates
            if (i > start && cands[i] == cands[i - 1]) continue;
            
            if (cands[i] == target) {
                list.add(cands[i]);
                res.add(new LinkedList<Integer>(list));
                list.remove(list.size() - 1); //DON'T FORGET THIS!!!!!!!!
                return;
            } else {
                list.add(cands[i]);
                helper(res, list, cands, target - cands[i], i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}



//More Concise Version
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); //to help avoid dupliates
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        helper(res, list, candidates, target, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list,
                        int[] cands, int target, int start) {
        //If index out of bounds, we stop.
        if (start >= cands.length) return;
        
        for (int i = start; i < cands.length && cands[i] <= target; i++) {
            //prevent duplicates
            if (i != start && cands[i] == cands[i - 1]) continue;
            
            list.add(cands[i]);
            if (cands[i] == target) {
                res.add(new LinkedList<Integer>(list));
            } else {
                helper(res, list, cands, target - cands[i], i + 1);
            }
            list.remove(list.size() - 1); //DON'T FORGET THIS!!!!!!!!
        }
    }
}