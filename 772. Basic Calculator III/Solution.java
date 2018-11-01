class Solution {
    public int basicCalculatorIII(String expression) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0;
        int i = 0;
        
        while (i < expression.length()) {
            char ch = expression.charAt(i);
            
            if (ch == '(' || Character.isDigit(ch)) {
                int num = 0;
                
                if (ch == '(') {
                    int start = ++i;
                    int count = 1;

                    while (count > 0) {
                        char curr = expression.charAt(i++);

                        if (curr == '(') {
                            count++;
                        } else if (curr == ')') {
                            count--;
                        }
                    }

                    String str = expression.substring(start, i);
                    num = basicCalculatorIII(str);
                } else {                
                    while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                        num = num * 10 + expression.charAt(i++) - '0';
                    }
                }
                
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
            } else if (ch != ' ') {
                sign = ch;
                i++;
            } else {
                i++;
            }
        }
        
        for (int num : stack) {
            res += num;
        }
        
        return res;
    }
}
