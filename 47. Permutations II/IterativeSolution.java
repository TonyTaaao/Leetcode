/*
I like this solution because it's iterative. Unlike dfs, iterative is fast, 
but hard to distinguish duplicates without using hashset.

Same idea as the iterative solution of Leetcode 46.
We build all permutations for the first (i-1) elements of input array, and insert 
the ith element into all positions of each prebuilt (i-1) permutation.
Using Set to make sure all permutations in res are unique.
*/

/*
Time Complexity: it doesn't do any extra computation but we need to copy N elements 
into (List<Integer> update) for N! operations.
O(N) per list copied in List<Integer> update * N! times = O(N*N!)
So Time Complexity = O(N*N!).

Space Complexity = O(N*N!) because res contains N! permutations, and each permutation 
contains N elements, same with set.
*/
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        
        //Iterate through every number in nums
        for (int i = 0; i < nums.length; i++) {
            //avoid adding duplicate answers
            Set<List<Integer>> set = new HashSet<>();
            //Iterate through every list/permutation in res
            while (res.peek().size() == i) {
                List<Integer> l = res.poll();
                //Insert current number nums[i] at all possible positions of l
                for (int j = 0; j <= l.size(); j++) {
                    List<Integer> update = new ArrayList<>(l);
                    update.add(j, nums[i]);
                    if (set.add(update)) res.add(update);
                }
            }
        }
        return res;
    }
}



//Better version without using Set, saves space/memory
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        res.add(new ArrayList<>());
        
        //Iterate through every number in nums
        for (int i = 0; i < nums.length; i++) {
            //Iterate through every permutation/list(l) in res
            while (res.peek().size() == i) {
                List<Integer> l = res.poll();
                //Insert current number nums[i] at all possible positions of l
                for (int j = 0; j <= l.size(); j++) {
                    //avoid adding duplicate answers
                    //"j < l.size()" is to prevent OutOfBounds Exceptions for l.get(index)
                    boolean shouldBreak = j < l.size() && nums[i] == l.get(j);
                    List<Integer> update = new ArrayList<>(l);
                    update.add(j, nums[i]);
                    res.add(update);
                    if (shouldBreak) break;
                }
            }
        }
        return res;
    }
}
