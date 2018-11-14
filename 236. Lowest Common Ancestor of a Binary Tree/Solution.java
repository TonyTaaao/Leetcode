/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
Recursive Solution, beats 98%:
If the current (sub)tree contains both p and q, then the function result is their LCA. 
If only one of them is in that subtree, then the result is that one of them. 
If neither are in that subtree, the result is null/None/nil.
*/
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // if p and q are on the two sides of root, return root
        if(left != null && right != null) return root;
        // if p and q are on the same side of root
        return left != null ? left : right;
    }
}