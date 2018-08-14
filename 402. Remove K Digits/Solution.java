// Time Complexity = O(2n) ~ O(n)
// Each element will be popped out from stack at most once.
// beats 70%
class Solution {
    public String removeKdigits(String num, int k) {
        int length = num.length();
        if (k >= length) return "0";
        boolean[] removed = new boolean[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length - 1 && k > 0; i++) {
            if (num.charAt(i) > num.charAt(i + 1)) {
                removed[i] = true;
                k--;
                while (k > 0 && !stack.isEmpty() && num.charAt(stack.peek()) > num.charAt(i + 1)) {
                    int removedIndex = stack.pop();
                    removed[removedIndex] = true;
                    k--;
                }
            } else {
                stack.push(i);
            }
        }
        
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < length - k; i++) {
            if (!removed[i]) {
                if (!(ans.length() == 0 && num.charAt(i) == '0'))
                    ans.append(num.charAt(i));
            }
        }
        return ans.length() == 0 ? "0" : ans.toString();
    }
}