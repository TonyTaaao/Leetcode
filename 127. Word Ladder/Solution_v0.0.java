//More efficient if wordList is large
/***
Reason: In 2ndSolution.java, using helper function differ(), we compare each unvisited word in wordlist 
with the current word popped out from queue. 
-->Number of comparisons = O(N*K), N = number of words in wordList, K = word length

In Solution.java, the way we process unvisited words is that, for a current popped out word (from queue),
in wordMatch() function, we transform it into all possible one-letter-different pattern(制造出所有只改一个
字母的可能情况-->只改一个字母，在所有可能的index上都改，每个index上26个字母都尝试), and if a possible pattern 
is in wordList, we add it to queue and remove it from unvisited.
-->Number of comparisons = O(26*K), K = word length

You can see that if N (number of words in wordList) >> 26, then Solution.java is much more efficient.
*/
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        //To avoid time limit exceed convert list to set and check if word is present or not!
        HashSet<String> set = new HashSet<String>(wordList); //This class implements the Set interface, backed by a hash table (actually a HashMap instance). so it has O(1) search time, better than the O(N) search time in List
        //HashSet uses object equality (Object.equals), not identity ("reference equals").
        Queue<String> q = new LinkedList<String>();
        int length = 1;
        q.add(beginWord);
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();
                if (w.equals(endWord)) return length;
                wordMatch(w, set, q);
            }
            length++;
        }
        return 0;
    }
    
    public void wordMatch(String w, Set<String> set, Queue<String> q) {
        for (int i = 0; i < w.length(); i++) {
            char[] word = w.toCharArray();
            for (int j = 0; j < 26; j++) {
                char c = (char) ('a' + j);
                if (word[i] == c) continue;
                word[i] = c;
                String s = String.valueOf(word);
                if (set.contains(s)) { //HashSet.contains(Object o): Returns true if this set contains the specified element. More formally, returns true if and only if this set contains an element e such that (o==null ? e==null : o.equals(e)).
                    set.remove(s);
                    q.offer(s);
                }
            }
        }
    }
}