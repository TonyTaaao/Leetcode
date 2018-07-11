//Less efficient when wordList is large
/***
Reason: using helper function differ(), we compare each unvisited word in wordlist with the current
word popped out from queue. 
Number of comparisons = O(N*K), N = number of words in wordList, K = word length

In Solution.java, the way we process unvisited words is that, for a current popped out word (from queue),
in wordMatch() function, we transform it into all possible one-letter-different pattern(制造出所有只改一个
字母的可能情况-->只改一个字母，在所有可能的index上都改，每个index上26个字母都尝试), and if a possible pattern 
is in wordList, we add it to queue and remove it from unvisited.
Number of comparisons = O(26*K), K = word length

-->You can see that if N (number of words in wordList) >> 26, then Solution.java is much more efficient.
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int distance = 1;
        Queue<String> level = new LinkedList<>();
        level.add(beginWord);
        while (!level.isEmpty()) {
            int size = level.size();
            for (int i = 0; i < size; i++) {
                String cur = level.poll();
                if (cur.equals(endWord)) return distance; //String is not primitive type, use equals() to compare
                wordList.remove(cur);
                for (String str : wordList) {
                    //if (str.equals(cur)) wordList.remove(cur);
                    //else if (differ(str, cur) == 1) level.add(str);
                    if (differ(str, cur) == 1 && /*Important!!*/!level.contains(str)) level.add(str);
                }
                
            }
            distance++;
        }
        return 0;
    }
    
    private int differ(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
    }
}