class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        int length = s1.length();
        
        
        for (int i = 0; i <= length; i++) {
            char[] s1_sorted1 = s1.substring(0, i).toCharArray();
            char[] s1_sorted2 = s1.substring(i, length).toCharArray();
            char[] s2_sorted1 = s2.substring(0, i).toCharArray();
            char[] s2_sorted2 = s2.substring(i, length).toCharArray();
            Arrays.sort(s1_sorted1);
            Arrays.sort(s2_sorted1);
            Arrays.sort(s1_sorted2);
            Arrays.sort(s2_sorted2);
            
            if (String.valueOf(s1_sorted1).equals(String.valueOf(s2_sorted1)) && 
                String.valueOf(s1_sorted2).equals(String.valueOf(s2_sorted2)))
                return true;
        }
        return false;
    }
}


class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        
        int length = s1.length();
        for (int i = 1; i < length; i++) {
            char[] s1_first = s1.substring(0, i).toCharArray();
            char[] s1_second = s1.substring(i, length).toCharArray();
            char[] s2_first = s2.substring(0, i).toCharArray();
            char[] s2_second = s2.substring(i, length).toCharArray();
            Arrays.sort(s1_first);
            Arrays.sort(s1_second);
            Arrays.sort(s2_first);
            Arrays.sort(s2_second);
            
            if (String.valueOf(s1_first).equals(String.valueOf(s2_first)) && 
                String.valueOf(s1_second).equals(String.valueOf(s2_second)))
                return true;
            
            char[] s2_1 = s2.substring(length - i, length).toCharArray();
            char[] s2_2 = s2.substring(0, length - i).toCharArray();
            Arrays.sort(s2_1);
            Arrays.sort(s2_2);
            if (String.valueOf(s1_first).equals(String.valueOf(s2_1)) && 
                String.valueOf(s1_second).equals(String.valueOf(s2_2)))
                return true;
        }
        return false;
    }
}