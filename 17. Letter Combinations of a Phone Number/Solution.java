//Recursive Solution
/*Time Complexity = O(k^N)
k = average length of string in KEYS, N = length of digits.
T(n) = k* T(n-1) --> T(n) = k^n
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        //cannot use (digits == ""), because String is an Object, not primitive type
        if (digits.equals("")) return res;
            
        String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        combinations(res, digits, "", 0, KEYS);
        return res;
    }
    
    private void combinations(List<String> res, String digits, String prefix, 
                              int offset, String[] KEYS) {
        if (offset == digits.length()) {
            res.add(prefix);
            return;
        }
        
        String letters = KEYS[digits.charAt(offset) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            combinations(res, digits, prefix + letters.charAt(i), offset + 1, KEYS);
        }
    }
}



//BFS Solution using Queue
/*Time Complexity = O(k^N)
k = average length of string in KEYS, N = length of digits.
*/
class Solution {
    public List<String> letterCombinations(String digits) {
        //List<String> res = new LinkedList<>(); This won't compile --> cannot find method peek()
        LinkedList<String> res = new LinkedList<>();
        if (digits.length() == 0) return res;
        
        String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        while (res.peek().length() != digits.length()) {
            String prefix = res.remove(); //same as res.poll()
            String letters = KEYS[digits.charAt(prefix.length()) - '0'];
            /*
            for (int i = 0; i < letters.length(); i++) {
                res.add(prefix + letters.charAt(i));
            }
            */
            for (char c : letters.toCharArray()) {
                res.add(prefix + c);
            }
        }
        return res;
    }
}