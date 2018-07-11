// beats 16.12% ~ 48.11%
/* Time Complexity = O(4*N) = O(4N) ~O(N)
 One for loop, everything within the loop takes constant time.
 */

import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        // stack stores all calculation results and all visited numbers 
        // that has not been calculated yet.
        Stack<Integer> stack = new Stack<>();
        // HashSet Constructor: HashSet(Collection<? extends E> c)
        // Set的contains()方法是通过equals()方法来查重的
        HashSet<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        int length = tokens.length;
        
        for (int i = 0; i < length; i++) {
            if (operators.contains(tokens[i])) {
                int last = stack.pop();
                int prev = stack.pop();
                stack.push(calculate(prev, last, tokens[i]));
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
    
    private int calculate(int a, int b, String operator) {
        if (operator.equals("+")) return a + b;
        else if (operator.equals("-")) return a - b;
        else if (operator.equals("*")) return a * b;
        else return a / b;
    }

    // switch的用法
    private int calculate2(int a, int b, String operator) {
        // switch (sth): 括号必须要有
        switch (operator) {
            case "+":
                return a + b;

            case "-":
                return a - b;

            case "*":
                return a * b;

            default:
                return a / b;
        }
    }
}


// Version2, same approach
public class Solution {
    public int evalRPN(String[] tokens) {
        int a,b;
        Stack<Integer> S = new Stack<Integer>();
        for (String s : tokens) {
            if(s.equals("+")) {
                S.add(S.pop()+S.pop());
            }
            else if(s.equals("/")) {
                b = S.pop();
                a = S.pop();
                S.add(a / b);
            }
            else if(s.equals("*")) {
                S.add(S.pop() * S.pop());
            }
            else if(s.equals("-")) {
                b = S.pop();
                a = S.pop();
                S.add(a - b);
            }
            else {
                S.add(Integer.parseInt(s));
            }
        }   
        return S.pop();
    }
}