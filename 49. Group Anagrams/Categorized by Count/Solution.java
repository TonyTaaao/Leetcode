class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        
        int[] count = new int[26]; //count the number of a-z occurrences in a String, size = 26 cuz only lower-case letters
        Map<String, List> ans = new HashMap();
        for (String str : strs) {
            //static void fill(int[] a, int val): Assigns the specified int value to each element of the specified array of ints.
            Arrays.fill(count, 0);
            for (char c : str.toCharArray()) count[c - 'a']++;
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(str);
        }
        return new ArrayList(ans.values());
    }
}