//Time Complexity = O(N) if not all digits are 9
//Time Complexity = O(2N) if all digits are 9, another for loop to create the new array
//runtime beats 68.51% submissions.
class Solution {
    public int[] plusOne(int[] digits) {
        int lastdigit = digits[digits.length - 1];
        int sum = lastdigit + 1;
        digits[digits.length - 1] = sum % 10;
        int carry = sum / 10; //OR: int carry = sum >= 10 ? 1 : 0;
        if (carry == 0) return digits;
        
        for (int i = digits.length - 2; i >= 0; i--) {
            sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            if (carry == 0) return digits;
        }
        
        //if carry == 1 still, meaning we need to add an extra leading digit == 1
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        for (int i = 0; i < digits.length; i++) res[1 + i] = digits[i];
        return res;
    }
}



//Solution2, More Concise version, the better one
//beats 68.51%
class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        
        int[] res = new int[digits.length + 1];
        res[0] = 1; //we don't need to copy and paste digits into res, because digits = [9,9,...,9]
        //and res = [1,0,0,...,0], so all the remaining digits are zero
        return res;
    }
}