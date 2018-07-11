//beats 37%
//Time Complexity = O(max(M,N)), M = a.length(), N = b.length()
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = 0; //index for iteration
        int overhead = 0;
        int la = a.length(), lb = b.length();
        int sum;
        
        while (i < la && i < lb) {
            sum = Character.getNumericValue(a.charAt(la-1 - i)) + 
                Character.getNumericValue(b.charAt(lb-1 - i)) + overhead;
            overhead = (sum >= 2) ? 1 : 0;
            res.insert(0, sum % 2);
            i++;
        }
        while (i < la) {
            sum = Character.getNumericValue(a.charAt(la-1 - i)) + overhead;
            overhead = (sum >= 2) ? 1 : 0;
            res.insert(0, sum % 2);
            i++;
        }
        while (i < lb) {
            sum = Character.getNumericValue(b.charAt(lb-1 - i)) + overhead;
            overhead = (sum >= 2) ? 1 : 0;
            res.insert(0, sum % 2);
            i++;
        }
        
        if (overhead > 0) res.insert(0, overhead);
        return res.toString();
    }
}



//Same idea, using int array to store digits
//beat 91.8% runtime
class Solution {
    public String addBinary(String a, String b) {
        int i = 0; //index for iteration
        int overhead = 0;
        int la = a.length(), lb = b.length();
        int[] ans = new int[Math.max(la, lb) + 1];
        int sum;
        
        while (i < la && i < lb) {
            sum = Character.getNumericValue(a.charAt(la-1 - i)) + 
                Character.getNumericValue(b.charAt(lb-1 - i)) + overhead;
            overhead = (sum >= 2) ? 1 : 0;
            ans[ans.length-1 - i] = sum % 2;
            i++;
        }
        while (i < la) {
            sum = Character.getNumericValue(a.charAt(la-1 - i)) + overhead;
            overhead = (sum >= 2) ? 1 : 0;
            ans[ans.length-1 - i] = sum % 2;
            i++;
        }
        while (i < lb) {
            sum = Character.getNumericValue(b.charAt(lb-1 - i)) + overhead;
            overhead = (sum >= 2) ? 1 : 0;
            ans[ans.length-1 - i] = sum % 2;
            i++;
        }
        if (overhead > 0) ans[0] = overhead;
        
        StringBuilder sb = new StringBuilder();
        for (int digit : ans) sb.append(digit);
        if (sb.charAt(0) == '0') sb.delete(0, 1);
        return sb.toString();
    }
}