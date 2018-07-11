import java.util.Collections;

class Solution {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        if (length == 0) return "";
        
        LinkedList<String> s = new LinkedList<>();
        for (int num : nums) {
            s.add(Integer.toString(num));
        }
        // Collections.sort(List<T> list, Comparator<? super T> c)
        // the first argument is a list, not an array.
        Collections.sort(s, new MSBComparator());
        
        StringBuilder res = new StringBuilder();
        for (String str : s) {
            if (!(res.length() == 0 && str.equals("0")))
                res.append(str);
        }
        return res.length() == 0 ? "0" : res.toString();
    }
    
    private class MSBComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            // if MSB(s1) > MSB(s2), place s1 before s2
            if (s1.charAt(0) > s2.charAt(0)) return -1;
            // if MSB(s1) < MSB(s2), place s1 after s2
            else if (s1.charAt(0) < s2.charAt(0)) return 1;
            // if MSB(s1) == MSB(s2)
            int len1 = s1.length(), len2 = s2.length();
            if (len1 > 1 && len2 > 1)
                return compare(s1.substring(1, len1), s2.substring(1, len2));
            else if (len1 > 1)
                return s1.charAt(1) > s1.charAt(0) ? -1 : 1;
            else if (len2 > 1)
                // return s2.charAt(1) > s2.charAt(0) ? -1 : 1; --> Wrong
                return s2.charAt(1) > s2.charAt(0) ? 1 : -1;
            else
                return 0;
        }
    }
}




import java.util.Collections;

class Solution {
    public String largestNumber(int[] nums) {
        int length = nums.length;
        if (length == 0) return "";
        
        LinkedList<String> s = new LinkedList<>();
        for (int num : nums) {
            s.add(Integer.toString(num));
        }
        // Collections.sort(List<T> list, Comparator<? super T> c)
        // the first argument is a list, not an array.
        Collections.sort(s, new MSBComparator());
        
        StringBuilder res = new StringBuilder();
        for (String str : s) {
            if (!(res.length() == 0 && str.equals("0")))
                res.append(str);
        }
        return res.length() == 0 ? "0" : res.toString();
    }
    
    private class MSBComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            // if MSB(s1) > MSB(s2), place s1 before s2
            if (s1.charAt(0) > s2.charAt(0)) return -1;
            // if MSB(s1) < MSB(s2), place s1 after s2
            else if (s1.charAt(0) < s2.charAt(0)) return 1;
            // if MSB(s1) == MSB(s2)
            int len1 = s1.length(), len2 = s2.length();
            if (len1 > 1 && len2 > 1) {
                int i = 1;
                while (i < len1 && i < len2) {
                    if (s1.charAt(i) > s2.charAt(i)) return -1;
                    else if (s1.charAt(i) < s2.charAt(i)) return 1;
                    else i++;
                }
                if (i < len1) return s1.charAt(i) > s1.charAt(0) ? -1 : 1;
                else if (i < len2) return s2.charAt(i) > s2.charAt(0) ? 1 : -1;
                else return 0;
            }
            else if (len1 > 1)
                return s1.charAt(1) > s1.charAt(0) ? -1 : 1;
            else if (len2 > 1)
                // return s2.charAt(1) > s2.charAt(0) ? -1 : 1; --> Wrong
                return s2.charAt(1) > s2.charAt(0) ? 1 : -1;
            else
                return 0;
        }
    }
}