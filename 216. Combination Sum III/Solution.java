// beats 78.36%
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        addCombinations(k, n, res, temp, 1);
        return res;
    }
    
    private void addCombinations(int k, int n, List<List<Integer>> res, List<Integer> temp, int start) {
        if (k < 0) return;
        else if (k == 0) {
            if (n == 0) {
                res.add(new LinkedList<>(temp));
            }
            return;
        } else if (n <= 0) return;
        
        for (int i = start; i <= 9; i++) {
            temp.add(i);
            addCombinations(k - 1, n - i, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}


// version 2, less efficient
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combination(ans, new ArrayList<Integer>(), k, 1, n);
        return ans;
    }

    private void combination(List<List<Integer>> ans, List<Integer> comb, int k, int start, int n) {
        if (comb.size() == k && n == 0) {
            List<Integer> li = new ArrayList<Integer>(comb);
            ans.add(li);
            return;
        }

        // Break early
        if (comb.size() == k)  {
            return;
        }

        for (int i = start; i <= 9; i++) {
            comb.add(i);
            combination(ans, comb, k, i + 1, n - i);
            comb.remove(comb.size() - 1);
        }
    }
}