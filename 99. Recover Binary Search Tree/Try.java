/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public void recoverTree(TreeNode root) {
        recoveredTree(root);
    }
    
    // returns whether the tree is already recovered
    // return true if swap occurs, otherwise return false
    public boolean recoveredTree(TreeNode root) {
        if (root == null) return false;
        TreeNode left_rightmost= root.left;
        TreeNode right_leftmost = root.right;
        TreeNode leftToSwap = checkValidLeftBranch(root, left_rightmost);
        if (leftToSwap != null) {
            swap(root, leftToSwap);
            return true;
        }
        TreeNode rightToSwap = checkValidRightBranch(root, right_leftmost);
        if (rightToSwap != null) {
            swap(root, rightToSwap);
            return true;
        }
        if (recoveredTree(root.left)) return true;
        if (recoveredTree(root.right)) return true;
        return false;
    }
    
    // returns whether the left branch of current root is valid
    // if invalid, return the node that causes this error; else, return null
    private TreeNode checkValidLeftBranch(TreeNode root, TreeNode left_rightmost) {
        while (left_rightmost != null) {
            if (left_rightmost.val > root.val) return left_rightmost;
            else left_rightmost = left_rightmost.right;
        }
        return null;
    }
    
    // returns whether the right branch of current root is valid
    // if invalid, return the node that causes this error; else, return null
    private TreeNode checkValidRightBranch(TreeNode root, TreeNode right_leftmost) {
        while (right_leftmost != null) {
            if (right_leftmost.val < root.val) return right_leftmost;
            else right_leftmost = right_leftmost.left;
        }
        return null;
    }
    
    private void swap(TreeNode node1, TreeNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
}