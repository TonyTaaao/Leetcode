/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// did not quite understand what the problem asked version.
class Solution {
    public int maxPathSum(TreeNode root) {
        return findMax(root, Integer.MIN_VALUE);
    }
    
    private int findMax(TreeNode root, int overallMax) {
        if (root == null) return 0;
        
        findMax(root.left, overallMax);
        findMax(root.right, overallMax);
        int left = root.left == null ? Integer.MIN_VALUE : root.left.val;
        int right = root.right == null ? Integer.MIN_VALUE : root.right.val;
        int temp = root.val + Math.max(left, 0) + Math.max(right, 0);
        
        int curMax;
        if (root.val >= 0) curMax = temp;
        else curMax = Math.max(Math.max(left, right), root.val);
        root.val = temp;
        
        overallMax = Math.max(overallMax, curMax);
        return overallMax;
    }
}




// Wrong
class Solution {
    public int maxPathSum(TreeNode root) {
        return findMax(root, Integer.MIN_VALUE);
    }
    
    private int findMax(TreeNode root, int overallMax) {
        if (root == null) return Integer.MIN_VALUE;
        
        overallMax = findMax(root.left, overallMax);
        overallMax = findMax(root.right, overallMax);
        int left = root.left == null ? Integer.MIN_VALUE : root.left.val;
        int right = root.right == null ? Integer.MIN_VALUE : root.right.val;
        
        int curMax;
        if (root.val >= 0) curMax = root.val + Math.max(left, 0) + Math.max(right, 0);
        else curMax = Math.max(Math.max(left, right), root.val);
        root.val = root.val + Math.max(Math.max(left, right), 0);
        
        overallMax = Math.max(overallMax, curMax);
        return overallMax;
    }
}