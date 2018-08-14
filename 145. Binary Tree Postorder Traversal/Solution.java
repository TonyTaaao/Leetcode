/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Solution 1: Recursive
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        postOrder(root, res);
        return res;
    }
    
    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }
}



// Solution 2: Iterative
// Time Complexity = O(n), each node is pushed and popped once
// Space Complexity = O(n)
// beats 65.82%
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        HashSet<TreeNode> visited = new HashSet<>(); // stores all visited nodes
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (visited.contains(top)) {
                stack.pop();
                res.add(top.val);
            } else {
                if (top.right != null) stack.push(top.right);
                if (top.left != null) stack.push(top.left);
                visited.add(top);
            }
        }
        return res;
    }
}