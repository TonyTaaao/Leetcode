/*
The main idea reminds an approach for solving coins/knapsack problem - to store the 
result for all positive i <= target and create the solution from them. For each t from
1 to our target we iterate through every candidate <= t in ascending order. For each 
candidate "c" we run through the answer == t-c, aka all combination sums equal to t-c 
which is stored at dp[t -c], The combination must start with a value >= c to 
avoid duplicates --> store only ordered combinations to avoid duplicates.

*/
public class Solution {
    public List<List<Integer>> combinationSum(int[] cands, int target) {
        //sort candidates to saves runtime since we don't need to go throught numbers > i in this case
        Arrays.sort(cands);
        //use ArrayList due to O(1) runtime access to dp[i]
        List<List<List<Integer>>> dp = new ArrayList<>();
        dp.add(new LinkedList()); //dp[0] = empty list
        
        //dp[i] stores solution to sum == i
        for (int i = 1; i <= target; i++) { //run through all targets from 1 to t
            List<List<Integer>> ans = new LinkedList<>(); //combs for sum == i, stored at dp[i]
            //run through all candidates <= i, the current target
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case: curr target is equal to curr candidate
                if (cands[j] == i) ans.add(Arrays.asList(cands[j]));
                //cur candidate + ans(t - cur candidate) == ans(t)
                //for curr candidate, we find the answer to t - curr candidate, and
                //add curr candidate to each ORDERED answer list
                else for (List<Integer> l : dp.get(i - cands[j])) {
                    //make sure the list is ordered to avoid duplicates
                    //e.g. by using this if comparison, we only add cands[j] to answer list 
                    //if it is <= the first/smallest element in the answer list.
                    //so that only [2,3,3] will be added, 
                    //[3,2,3] and [3,3,2] won't be added since 2 <= 3
                    //-->No duplicate lists in our answer
                    if (cands[j] <= l.get(0)) {
                        List<Integer> newl = new LinkedList<>();
                        newl.add(cands[j]);
                        newl.addAll(l);
                        ans.add(newl);
                    }
                }
            }
            dp.add(ans);
        }
        
        return dp.get(target);
    }
}