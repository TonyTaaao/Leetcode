class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        if (needle.length() == 0) return 0;
        
        char[] arr = haystack.toCharArray();
        char[] n = needle.toCharArray();
        a: for (int i = 0; i <= arr.length - n.length; i++) {
            if (arr[i] == n[0]) {
                b: for (int j = 1; j < n.length; j++) {
                    if (arr[i+j] != n[j]) continue a;
                }
                return i;
            }
        }
        return -1;
    }
}