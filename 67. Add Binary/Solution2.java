//Computation from string usually can be simplified by using a carry as such.
//beats 91.8% runtime
//Time Complexity = O(max(M,N)), M = a.length(), N = b.length()
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = 0; //sum = a[current digit] + b[current digit] + carry
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sum += carry;
            res.append(sum % 2);
            carry = sum / 2;
        }
        if (carry > 0) res.append(carry);
        return res.reverse().toString();
    }
}