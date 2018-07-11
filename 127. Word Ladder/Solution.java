//More efficient than the second Solution if wordList is large.
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
        Set<String> unvisited = new HashSet<>(wordList); //backed up by a hash table, search time = O(1) due to hash function
        if (!unvisited.contains(endWord)) return 0;
        
        Queue<String> level = new LinkedList<>(); //stores all words at current level, aka same distance to beginWord
        level.add(beginWord);
        int distance = 1;
        
        while (!level.isEmpty()) {
            int size = level.size();
            for (int i = 0; i < size; i++) {
                String w = level.poll();
                if (w.equals(endWord)) return distance;
                wordMatch(w, level, unvisited);
            }
            distance++;
        }
        return 0;
    }
    
    private void wordMatch(String cur, Queue<String> level, Set<String> unvisited) {
        for (int i = 0; i < cur.length(); i++) {
            char[] transform = cur.toCharArray(); //take notes on this method
            for (int j = 0; j < 26; j++) { //or for char : 'a' to 'z'
                char c = (char) ('a' + j);
                if (transform[i] == c) continue;
                transform[i] = c;
                String s = String.valueOf(transform); //this String only has one letter different from cur
                if (unvisited.contains(s)) {
                    level.offer(s); //take notes on this one
                    unvisited.remove(s);
                }
            }
        }
    }
}