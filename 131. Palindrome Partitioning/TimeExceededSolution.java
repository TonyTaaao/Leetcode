//This works, but exceeds maximum runtime limit if input is large
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();
        List<String> partition = new ArrayList<>();
        int start = 0;
        int end = s.length() - 1;
        //int mid = end;
        helper(res, partition, s, start, end);
        return res;
    }
    
    private boolean helper(List<List<String>> res, List<String> partition,
                       String s, int start, int end) {
        //End case
        if (start > end) return false;
        
        for (int i = start; i <= end - 1; i++) {
            int mid = i;
            boolean move_on = helper(res, partition, s, start, mid);
            if (move_on) helper(res, partition, s, mid + 1, end);
            partition.remove(partition.size() - 1);
            if (move_on) partition.remove(partition.size() - 1);
        }
        
        
        partition.add(s.substring(start, end + 1));
        
        //Check if current substring is palindrome
        boolean palindrome = true;
        int lo = start;
        int hi = end;
        while (lo < hi && palindrome) {
            palindrome = s.charAt(lo) == s.charAt(hi);
            lo++;
            hi--;
        }
        //If current substring is a palindrome, add it to temporary answer(partition)
        if (palindrome) {
            //If current substring is a palindrome and we made it through all characters in String s
            if (end == s.length() - 1) {
                res.add(new ArrayList(partition));
            }
        }
        
        return palindrome;
    }
}