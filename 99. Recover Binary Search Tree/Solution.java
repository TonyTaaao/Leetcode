/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Solve the problem using In-order traversal, see detailed explanation in docx.

// Recursive Solution
// beats 56.73%
// Time = O(n), just one inorder traversal, which takes O(n)
// Constant space
class Solution {
    TreeNode firstToSwap = null;
    TreeNode secondToSwap = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        traverse(root);
        if (firstToSwap != null && secondToSwap != null)
            swap(firstToSwap, secondToSwap);
    }
    
    private void traverse(TreeNode cur) {
        if (cur == null) return;
        
        traverse(cur.left);
        
        if (firstToSwap == null && prev.val >= cur.val) {
            firstToSwap = prev;
        } 
        if (firstToSwap != null && prev.val >= cur.val) {
            secondToSwap = cur;
        }
        prev = cur;
        
        traverse(cur.right);
    }
    
    private void swap(TreeNode node1, TreeNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
}



// Iterative Solution
// Space = O(n) due to stack, Time = O(n)
// beats 18.55%
class Solution {
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        TreeNode firstSwapped = null, secondSwapped = null, prev = null;
        
        //perform inorder traversal
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) { // push left branch and root into stack
                stack.push(root);
                root = root.left;
            }
            TreeNode cur = stack.pop();
            if (prev != null && prev.val >= cur.val) {
                if (firstSwapped == null) firstSwapped = prev;
                if (firstSwapped != null) {
                    secondSwapped = cur;
                }
            }
            prev = cur;
            root = cur.right; // push right branch into stack
        }
        
        swap(firstSwapped, secondSwapped);
    }
    
    private void swap(TreeNode node1, TreeNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
}