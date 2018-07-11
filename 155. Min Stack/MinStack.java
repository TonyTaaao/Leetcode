/*
Constant runtime, O(n) space – Extra stack:

Use an extra stack to keep track of the current minimum value. During the push 
operation we choose the new element or the current minimum, whichever that is 
smaller to push onto the min stack. 

Minor space optimization:

If a new element is larger than the current minimum, we do not need to push it 
on to the min stack. When we perform the pop operation, check if the popped element 
is the same as the current minimum. If it is, pop it off the min stack too.

*/
// beats 66%
class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack; // store the minimum up to that point in stack

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) minStack.push(x);
        else minStack.push(Math.min(minStack.peek(), x));
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */