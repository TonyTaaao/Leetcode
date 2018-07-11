// beats 10.52%
class MinStack {
    // min[i] stores min element from bottom of stack to the ith pushed element
    LinkedList<Integer> min;
    LinkedList<Integer> list; // stores all elements in stack

    /** initialize your data structure here. */
    public MinStack() {
        min = new LinkedList<>();
        list = new LinkedList<>();
    }
    
    public void push(int x) {
        list.add(x);
        if (min.isEmpty()) min.add(x);
        else min.add(Math.min(min.getLast(), x));
    }
    
    public void pop() {
        list.removeLast();
        min.removeLast();
    }
    
    public int top() {
        return list.getLast();
    }
    
    public int getMin() {
        return min.getLast();
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