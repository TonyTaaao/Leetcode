//beats 98% runtime
//Time Complexity = O(max(M,N)), M = version1.length(), N = version2.length()
//Linear Time Complexity
class Solution {
    public int compareVersion(String version1, String version2) {
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < version1.length() && idx2 < version2.length()) {
            int start1 = idx1, start2 = idx2;
            while (idx1 < version1.length() && version1.charAt(idx1) != '.') idx1++;
            while (idx2 < version2.length() && version2.charAt(idx2) != '.') idx2++;
            int num1 = Integer.parseInt(version1.substring(start1, idx1));
            int num2 = Integer.parseInt(version2.substring(start2, idx2));
            if (num1 > num2) return 1;
            else if (num1 < num2) return -1;
            else {
                idx1++;
                idx2++; 
            }
        } 
        
        //version1 and version2 are the same until version2 reaches the end first and breaks the while loop,  
        //version1 still has sth left, so version1 > version2
        while (idx1 < version1.length()) {
            int start1 = idx1;
            while (idx1 < version1.length() && version1.charAt(idx1) != '.') idx1++;
            int num1 = Integer.parseInt(version1.substring(start1, idx1));
            if (num1 > 0) return 1;
            else {
                idx1++;
            }
        }
        //version1 and version2 are the same until version1 reaches the end first and breaks the while loop,
        //version2 still has sth left, so version2 > version1
        while (idx2 < version2.length()) {
            int start2 = idx2;
            while (idx2 < version2.length() && version2.charAt(idx2) != '.') idx2++;
            int num2 = Integer.parseInt(version2.substring(start2, idx2));
            if (num2 > 0) return -1;
            else {
                idx2++;
            }
        }
        //otherwise
        return 0;
    }
}