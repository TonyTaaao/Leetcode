/*
there are n! / (n-k)!k! combinations (binomial coefficients).

First, there is nothing wrong with using O(n! / (n-k)!k!) - or any other 
function f(n) as O(f(n)), but I believe you are looking for a simpler solution that 
still holds the same set.

If you are willing to look at the size of the subset k as constant,

for k<=n-k:

n! / ((n-k)!k!) = ((n-k+1) (n-k+2) (n-k+3) ... n ) / k! 
But the above is actually (n^k + O(n^(k-1))) / k!, which is in O(n^k)

Similarly, if n-k<k, you get O(n^(n-k))

Which gives us O(n^min{k,n-k})

So Time Complexity = O(n^min{k,n-k})
*/
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        helper(res, list, 1, n, k);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list,
                        int start, int end, int k) {
        if (k == 0) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = start; i <= end; i++) {
            list.add(i);
            helper(res, list, i + 1, end, k - 1); //Note that start should be i+1, not start+1
            list.remove(list.size() - 1);
        }
    }
}