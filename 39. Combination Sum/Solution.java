//DFS Solution
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        helper(res, list, candidates, target, 0); //why do we need parameter start?
        return res;
    }
    
    /*
    Why do we need parameter start?
    start is used to prevent going back to previous index and add the same list to res multiple times
    eg. in Example 1, res = [[2,2,3], [7]]
    if we don't use start and always start the for loop from i = 0, we will get res like this:
    res = [[2,2,3], [2,3,2], [3,2,2], [7]]
    */
    private void helper(List<List<Integer>> res, List<Integer> list,
                        int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            //res.add(list); is WRONG!!! Since list is constantly changing, we must 
            //add a new copy of list --> add the content of list at that moment!!!
            res.add(new LinkedList(list));            
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int cur = candidates[i];
            list.add(cur);
            helper(res, list, candidates, target - cur, i);
            list.remove(list.size() - 1); //IMPORTANT!!!!!!
            //Once we finish this current number, we need to delete it from list
            //and then move on to the next number, otherwise all visited numbers
            //are stored in list and added to res
        }
    }
}
/*
I think the runtime should be O(2^n)
T(n) = T(n-1) + T(n-2) + T(n-3) + ...
T(n-1) = T(n-2)+T(n-3) + ... use this to replace T(n-1) in the above,
so T(n) = 2 * [ T(n-2) + T(n-3) + ...], and so on, T(n) = O(2^n)

 I agree, this is an exponential algorithm, but the base is larger than 2. 
 Consider where target is 10000, and the smallest/first number of candidates is 1. 
 Situations like this could add a very large co-efficient to terms like T(N-1).
*/