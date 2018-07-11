/*
Roughly,
T(n)=T(n-1)+T(n-2)+..+T(1)

T(n+1)=T(n)+T(n-1)+..+T(1)

T(n+1)=2T(n)

T(n)=2^n

The function isPalindrome is O(n), so the total runtime is O(n)*2^n = O(n*2^n)
Time complexity is O(n*(2^n)).
*/
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();
        List<String> list = new ArrayList<>();
        int start = 0;
        int end = s.length() - 1;
        helper(res, list, s, start, end);
        return res;
    }
    
    private void helper(List<List<String>> res, List<String> list,
                       String s, int start, int end) {
        //if we made it through all characters in s, add current answer to res
        if (start == s.length()) {
            res.add(new ArrayList(list));
            return;
        }
        
        for (int i = start; i <= end; i++) {
            //int mid = i; //first half = [start, mid], second half = [mid + 1, end]
            if (!isPalindrome(s, start, i)) continue;
            
            //String.substring(int startIndex, int endIndex), endIndex is exclusive
            list.add(s.substring(start, i + 1));
            helper(res, list, s, i + 1, end);
            list.remove(list.size() - 1);
        }
    }
    
    private boolean isPalindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) return false;
            lo++;
            hi--;
        }
        return true;
    }
}