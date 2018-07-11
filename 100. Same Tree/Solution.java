/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Recursive Solution
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p.val == q.val) return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        else return false;
    }
}

//Iterative Solution
//DFS Search
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //Stack is a class, Queue is an interface
        Stack<TreeNode> stack = new Stack<>(); //Stack for Depth-first search, Queue for BFS
        stack.push(p);
        stack.push(q);
        while (!stack.empty()) {
            TreeNode q1 = stack.pop(); //LIFO
            TreeNode p1 = stack.pop();
            if (q1 == null && p1 == null) continue;
            if (q1 == null || p1 == null) return false;
            if (q1.val != p1.val) return false;
            stack.push(p1.left);
            stack.push(q1.left);
            stack.push(p1.right);
            stack.push(q1.right);
        }
        return true;
    }
}

//BFS Search (Iterative)
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>(); //LinkedList is an implementing class of interface Queue
        queue.add(p);
        queue.add(q);
        while (queue.size() != 0) {
            TreeNode pcur = queue.poll(); //FIFO
            TreeNode qcur = queue.poll();
            if (pcur == null && qcur == null) continue;
            if (pcur == null || qcur == null) return false;
            if (pcur.val != qcur.val) return false;
            queue.add(pcur.left);
            queue.add(qcur.left);
            queue.add(pcur.right);
            queue.add(qcur.right);
        }
        return true;
    }
}