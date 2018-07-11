/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Recursion
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            if (root.val == sum) return true;
            else return false;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}




//Iteration (DFS):
//The idea is preorder traverse, but instead of using recursive call, I am using a stack.
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        Stack<Integer> dfs_sums = new Stack<>();
        dfs_sums.push(root.val);
        
        int dfs_sum = 0;
        while (!nodes.empty()) {
            dfs_sum = dfs_sums.pop();
            TreeNode cur = nodes.pop();
            if (dfs_sum == sum && cur.left == null && cur.right == null) return true;
            if (cur.right != null) {
                nodes.push(cur.right);
                dfs_sums.push(cur.right.val + dfs_sum);
            }
            if (cur.left != null) {
                nodes.push(cur.left);
                dfs_sums.push(cur.left.val + dfs_sum);
            }
        }
        //During the while loop above, the nodes are popped out in root-->left-->right order, 
        //which is a preorder traversal.
        return false;
    }
}




//Iteration (DFS) using one Stack only:
//The idea is preorder traverse, but instead of using recursive call, I am using a stack.
//The only trick is that I change TreeNode value to the sum of all nodes from root to itself, so
//the TreeNode val is equal to its path sum.
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            if (cur.val == sum && cur.left == null && cur.right == null) return true;
            if (cur.right != null) {
                cur.right.val += cur.val;
                stack.push(cur.right);
            }
            if (cur.left != null) {
                cur.left.val += cur.val;
                stack.push(cur.left);
            }
        }
        //During the while loop above, the nodes are popped out in root-->left-->right order, 
        //which is a preorder traversal.
        return false;
    }
}