class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 11) return new ArrayList();
        
        Set<Integer> set = new HashSet();
        Set<String> ans = new HashSet();
        int[] map = new int[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        
        int identifier;
        int length = s.length();
        for (int i = 0; i < length-9; i++) {
            String cur = s.substring(i, i+10);
            identifier = 0;
            for (int j = 0; j < 10; j++) {
                //identifier += map[cur.charAt(j) - 'A'];
                identifier <<= 2;
                identifier |= map[cur.charAt(j) - 'A'];
            }
            if (!set.add(identifier)) ans.add(cur);
        }
        return new ArrayList(ans);
    }
}