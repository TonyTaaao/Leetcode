class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList(); //return new ArrayList();
        
        //key = sorted string (same for anagrams), value = list of original strings
        HashMap<String, List<String>> map = new HashMap();
        for (String str : strs) { //O(N) time, N = number of elements in String[]
            char[] ca = str.toCharArray(); //Class String does not have a sort method, so need to convert to char array
            //and then use Arrays.sort(object[], including primitive types)
            Arrays.sort(ca); //O(KlogK) time, K = length of string in strs
            String key = String.valueOf(ca); //Convert char[] to String
            if (!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(str);
        }
        return new ArrayList(map.values());
    }
}