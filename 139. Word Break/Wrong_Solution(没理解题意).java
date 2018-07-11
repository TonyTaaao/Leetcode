class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int start = 0;
        for (int end =1; end <= s.length(); end++){
            if(wordDict.contains(s.substring(start, end))) {
                wordDict.remove(s.substring(start, end));
                start = end;
                if (end == s.length()) return true;
            }
        }
        
        return false;
    }
}