// Two Map Solution
// beats 61.56%
/*
We use a map "counts" to record the expected times of each word and another map "seen" to 
record the times we have seen the word. Then we check for every possible position of i and see
if we can form a full concatentation starting at index i. Once we meet an unexpected word or 
the times of some word seen exceeds its expected times, we stop the check and move on to next i. 
If we finish the check successfully, push i to our result indices.

Use HashTable to check if a substring of s is in the dictionary word list. --> O(1) search time.

Two Maps: 1 for expected times of each word, 1 for actual times we saw the word.
Two Pointers(i and j): 1 to traverse String s, 1 to traverse dictionary word list.

Time Complexity = O(mn), n = length of string s, m = length of array words
i to iterate through each index in s (n times total), j to iterate through each index 
in words (m times total), so total time = O(nm)

Space Complexity = O(m), map stores every word in words .
*/
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0) return new LinkedList<Integer>();
        
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        
        List<Integer> indices = new LinkedList<>();
        int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i <= n - num*len; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                String word = s.substring(i + j*len, i + (j+1)*len);
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.get(word)) break;
                } else {
                    break;
                }
                j++;
            }
            if (j == num) {
                indices.add(i);
            }
        }
        return indices;
    }
}