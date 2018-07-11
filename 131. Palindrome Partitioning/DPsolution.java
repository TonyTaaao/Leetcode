//O(2^n) time complexity
class Solution {
    public List<List<String>> partition(String s) {
        //dp[i] stores solution to s.substring(0, i)
        //dp[s.length()] stores solution to (s.subtring(0, s.length()) == s)
        List<List<List<String>>> dp = new ArrayList<>(); 
        //or List<List<String>>[] dp = new List[len + 1];
        List<List<String>> dp_0 = new LinkedList<>(); //dp[0] = an empty list
        dp_0.add(new LinkedList());
        dp.add(dp_0);
        
        int len = s.length();
        for (int i = 1; i <= s.length(); i++) {
            List<List<String>> ans = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                String latter = s.substring(j, i);
                if (isPalindrome(latter)) {
                    List<List<String>> l = dp.get(j);
                    for (List<String> former : l) {
                        List<String> update = new LinkedList<>(former);
                        update.add(latter);
                        ans.add(update);
                    }
                }
            }
            dp.add(ans);
        }
        return dp.get(s.length());
    }
    
    private boolean isPalindrome(String s) {
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) return false;
            lo++;
            hi--;
        }
        return true;
    }
}


//Another version, same idea, use dp array instead of dp ArrayList
public class Solution {
    public static List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>>[] result = new List[len + 1]; //result == dp
        result[0] = new ArrayList<List<String>>();
        result[0].add(new ArrayList<String>());

        boolean[][] pair = new boolean[len][len];
        for (int i = 0; i < s.length(); i++) {
            result[i + 1] = new ArrayList<List<String>>();
            for (int left = 0; left <= i; left++) {
                if (s.charAt(left) == s.charAt(i) && (i-left <= 1 || pair[left + 1][i - 1])) {
                    pair[left][i] = true;
                    String str = s.substring(left, i + 1);
                    for (List<String> r : result[left]) {
                        List<String> ri = new ArrayList<String>(r);
                        ri.add(str);
                        result[i + 1].add(ri);
                    }
                }
            }
        }
        return result[len];
    }
}