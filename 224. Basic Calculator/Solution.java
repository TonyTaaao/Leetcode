// beats 77.22%
class Solution {
    public int calculate(String s) {
        int length = s.length();
        int res = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
        	char c = s.charAt(i);
        	if (Character.isDigit(c)) {
        		int num = c  - '0';
                while (i + 1 < length && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                res += num * sign;
        	} else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }

        return res;
    }
}