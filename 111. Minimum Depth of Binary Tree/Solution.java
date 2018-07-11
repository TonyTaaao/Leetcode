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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return 1 + minDepth(root.right); //We're looking for the nearest leaf node, which cannot be null, so we must use the non-null side. leaf node = node without children, but the node itself should not be null
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}

//2nd Version (same thing)
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
       
    }
}

//3rd version (same thing)
public int minDepth(TreeNode root) {
    if(root == null) return 0;
    if(root.left == null || root.right == null) {
        return 1 + Math.max(minDepth(root.left), minDepth(root.right));
    }
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}