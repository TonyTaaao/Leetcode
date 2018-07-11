// Solution 1: Brute Force Recursion
// Time Limit Exceeded
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new LinkedList<>();
        partitionWord(s, 0, "", wordDict, res);
        return res;
    }
    
    // start = the part of s starting at index start that we need to break.
    // we only need to break s[start:], s[0: start - 1] is already finished breaking(if valid) 
    // and stored in prev.
    // prev = temporary string that contains words we have already break
    // res = final result, list of all constructed sentences
    // once start == s.length(), it means we break everything before index s.length(), aka 
    // the entire string, so we can add prev into res
    // otherwise, we try to partition the word s[start:] into s[start:i] and s[i+1:] for any i,
    // and if s[start:i] is contained in wordDict, we add it into temporary string prev and
    // continue on s[i+1:]
    private void partitionWord(String s, int start, String prev, List<String> wordDict, List<String> res) {
        int length = s.length();
        if (start == length) {
            res.add(prev.substring(0, prev.length() - 1));
            return;
        }
        for (int i = start; i < length; i++) {
            String cur = s.substring(start, i + 1);
            if (wordDict.contains(cur)) {
                int prev_length = prev.length();
                prev += cur + " ";
                partitionWord(s, i + 1, prev, wordDict, res);
                prev = prev.substring(0, prev_length);
            }
        }
    }
}



// Solution 2: still not efficient enough
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        // dp[i] = all possible combinations of dictionary words that can construct s[0:i]
        // we're going to return dp[length - 1]
        LinkedList<String>[] dp = new LinkedList[length];
        
        for (int i = 0; i < length; i++) {
            // initialize each element(list) in array, DON'T FORGET THIS!!!
            // otherwise dp[i] will be null --> cannot make method call.
            dp[i] = new LinkedList<>();
            if (wordDict.contains(s.substring(0, i + 1))) dp[i].add(s.substring(0, i + 1) + (i == length - 1 ? "" : " "));
            for (int j = 1; j <= i; j++) {
                boolean valid_former = !dp[j - 1].isEmpty();
                if (valid_former && wordDict.contains(s.substring(j, i + 1))) {
                    String latter = s.substring(j, i + 1);
                    for (String former : dp[j - 1]) {
                        dp[i].add(former + latter + (i == length - 1 ? "" : " "));
                    }
                }
            }
        }
        return dp[length - 1];
    }
}




// Solution 3 : memorized DFS
// beats 98.97%
// Using DFS directly will lead to TLE, so I just used HashMap to save the previous results 
// to prune duplicated branches, as the following:
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }       

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s)) 
            return map.get(s);

        LinkedList<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
}