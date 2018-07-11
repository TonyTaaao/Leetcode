/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //Iterative, Time Complexity = O(N), Space Complexity = O(N) worst case???
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        
        Stack<TreeNode> dfs = new Stack<>();
        dfs.push(root);
        while (!dfs.empty()) {
            root = dfs.pop();
            res.add(root.val);
            //preorder is root-->left-->right
            //Stack is LIFO, right should be added first-->popped out to res last
            if (root.right != null) dfs.push(root.right);
            //Stack is LIFO, left should be pushed later-->popped out to res first
            if (root.left != null) dfs.push(root.left);
        }
        return res;
    }
}

//Recursive, Time Complexity = O(N)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preorder(root, res);
        return res;
    }
    
    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
}