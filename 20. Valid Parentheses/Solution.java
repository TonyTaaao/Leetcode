class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0) return true;
        if (s.length() == 1) return false; //unbalanced string. "("

        Stack<Character> stack = new Stack<>();
        //stack.push(s.charAt(0)); //push the first character into stack, cuz stack.peek() throws 
        //EmptyStackException if stack is empty
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (stack.empty() || current != stack.peek()) {
                stack.push(current); //no match, add current to stack
            } else {
                stack.pop(); //find matched parentheses, remove it, do not add current
            }
        }
        return stack.empty();
    }
}