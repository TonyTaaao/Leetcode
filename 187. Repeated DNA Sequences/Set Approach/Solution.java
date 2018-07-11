class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 11) return new ArrayList();
        
        Set<String> set = new HashSet(), ans_set = new HashSet();
        for (int i = 0; i <= s.length()-10; i++) {
            //public String substring(int beginIndex, int endIndex)
            //Returns a new string that is a substring of this string. The substring begins at the specified beginIndex 
            //and extends to the character at index endIndex - 1. Thus the length of the substring is endIndex-beginIndex.
            String cur = s.substring(i, i+10);
            if (!set.add(cur)) ans_set.add(cur);
        }
        return new ArrayList(ans_set);
    }
}