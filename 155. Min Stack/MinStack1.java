题意：设计一个能输出栈内最小值的栈

该题设计两个栈，一个栈是正常的栈s，而另一个是存最小值的栈sm

在push时要判断sm是否为空，如果sm为空或者非空但是插入值小于等于当前最小值的，需要在sm中插入x

同样地在pop时，s的元素被删除了，那么sm中的也应该被删除。

通过这些操作维护sm能很巧妙在O（1）复杂度得到最小值。

// beats 48.29%
class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) 
            minStack.push(x);
    }
    
    public void pop() {
        // Why do we have to use .equals() here? See line 54.
        if (stack.peek().equals(minStack.peek())) minStack.pop();
        stack.pop();
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



    Integer a = new Integer(100);
    Integer b = new Integer(100);

    // 1. compare Integer with int: use "=="
    System.out.println(a == 100); //true

    // 2. compare Integer with Integer: use .equals()
    System.out.println(a == b); //false
    System.out.println(a.equals(b)); //true