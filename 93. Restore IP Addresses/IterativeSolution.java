/*
3-loop divides the string s into 4 substring: s1, s2, s3, s4. Check if each 
substring is valid.

In isValid, strings whose length is greater than 3 or equal to 0 is not valid; 
or if the string's length is longer than 1 and the first letter is '0', it's invalid; 
or the string whose integer representation is greater than 255 is invalid.
*/

//Time Complexity = O(3^3) = constant time
//Durind each loop we iterate 3 times from 1 to 3, and there are 3 loops like this.
//3^3 = 27 times worst case.
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();
        int len = s.length();
        for (int i = 1; i < 4 && i < len-2; i++) {
            for (int j = i + 1; j < i + 4 && j < len-1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i); //endIndex is exclusive
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, s.length());
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }
    
    private boolean isValid(String s) {
        if (s.length() == 0 || s.length() > 3 || (s.charAt(0) == '0' && s.length() > 1)
            ||Integer.parseInt(s) > 255) return false;
        return true;
    }
}



//Another version
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        String ip = new String();
         for (int a=1; a<=3; a++){
             for (int b=1; b<=3; b++){
                 for (int c=1; c<=3; c++){
                     for (int d=1; d<=3; d++){
                         if (a+b+c+d == s.length()) {
                             int A = Integer.parseInt(s.substring(0, a));
                             int B = Integer.parseInt(s.substring(a, a+b));
                             int C = Integer.parseInt(s.substring(a+b, a+b+c));
                             int D = Integer.parseInt(s.substring(a+b+c));
                             if (A<=255 && B<=255 && C<=255 && D<=255){ 
                                 ip += A + "." + B + "." + C + "." + D;
                                 if(ip.length() == len + 3){
                                     res.add(ip);
                                 }
                                 ip = new String();
                             }
                         }
                     }
                 }
             }
         }
        return res;
    }

}