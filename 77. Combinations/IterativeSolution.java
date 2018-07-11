/*
There are n! / (n-k)!k! combinations (binomial coefficients).
For each comination, List<Integer> update = new ArrayList(comb) takes O(N) time.

Time complexity = #combations * O(N) per combibation due to update list
                = O(n * n! / (n-k)!k!)
*/
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        for (int i = 1; i <= n; i++) res.add(Arrays.asList(i));
        
        while (res.peek().size() < k) {
            List<Integer> comb = res.poll();
            int last = comb.size() - 1;
            for (int i = comb.get(last) + 1; i <= n; i++) {
                List<Integer> update = new ArrayList(comb);
                update.add(i);
                res.add(update);
            }
        }
        return res;
    }
}