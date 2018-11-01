// beats 34.83%
class Solution {
    public int calculate(String s) {
        int num = 0, length = s.length();
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
        	char c = s.charAt(i);
        	if (Character.isDigit(c)) {
        		num = num * 10 + c - '0';
        	}
            if ((!Character.isDigit(c) && c != ' ') || i == length - 1) {
        		if (sign == '+') {
        			stack.push(num);
        		} else if (sign == '-') {
        			stack.push(-num);
        		} else if (sign == '*') {
        			stack.push(stack.pop() * num);
        		} else if (sign == '/') {
        			stack.push(stack.pop() / num);
        		}
        		num = 0;
        		sign = c;
        	}
        }

        int res = 0;
        for (int i : stack)
        	res += i;
        return res;
    }
}


// beats 96%
public class Solution {
    public int calculate(String s) {
        int len = s.length();
        char sign = '+';
        int prev = 0;
        int result = 0;

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            
            if (Character.isDigit(ch)) {
                int curr = s.charAt(i) - '0';
                
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    curr = curr * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                
                if (sign == '+') {
                    result += curr;
                    prev = curr;
                } else if (sign == '-') {
                    result -= curr;
                    prev = -curr;
                } else if (sign == '*') {
                    result = result - prev + prev * curr;
                    prev = prev * curr;
                } else {
                    result = result - prev + prev / curr;
                    prev = prev / curr;
                }
            } else if (ch != ' ') {
                sign = ch;
            }
        }
        
        return result;
    }
}
