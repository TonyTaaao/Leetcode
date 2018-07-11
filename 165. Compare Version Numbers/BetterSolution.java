//This solution uses String.split(pattern)
//beats 66.43%
/*
Time Complexity is O(kn):

split: O(n), n is the length of the longer string array.
loop: O(kn), k is the average length of subversion number
I check the Integer.parseInt, it should be O(k).
So the result will be O(kn).
*/
class Solution {
    public int compareVersion(String version1, String version2) {
        // . is a regular expression, so we cannot directly use ".",
        //we need to use "\\."
        //String[] v1 = version1.split(".") IS WRONG!!!
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int i = 0;
        while (i < v1.length && i < v2.length) {
            int num1 = Integer.parseInt(v1[i]);
            int num2 = Integer.parseInt(v2[i]);
            if (num1 > num2) return 1;
            else if (num1 < num2) return -1;
            i++;
        }
        while (i < v1.length) {
            int num1 = Integer.parseInt(v1[i++]);
            if (num1 > 0) return 1;
        }
        while (i < v2.length) {
            int num2 = Integer.parseInt(v2[i++]);
            if (num2 > 0) return -1;
        }
        return 0;
    }
}



//Version2, same idea
//beats 66.43%
class Solution {
    public int compareVersion(String version1, String version2) {
        // . is a regular expression, so we cannot directly use ".",
        //we need to use "\\."
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int length = Math.max(v1.length, v2.length);
        for (int i = 0; i < length; i++) {
            Integer num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            Integer num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            
            //Note that int cannot be dereferenced, so we must use Integer for 
            //num1 and num2.
            int compare = num1.compareTo(num2);
            if (compare != 0) return compare;
        }
        return 0;
    }
}