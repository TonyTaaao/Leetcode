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
    public boolean isBalanced(TreeNode root) {
        return height(root) != -2;
    }
    
    //null node height = -1, leaf node height = 0 --> height of every possible node should >= -1
    //If a node is not balanced, set its height to -2 and immediately return -->differentiate from 
    //valid height value to represent that: 
    //"this height is invalid" means "it is invalid, no need to further process, can return false"
    private int height(TreeNode cur) {
        if (cur == null) return -1;
        
        int lefth = height(cur.left);
        if (lefth == -2) return -2; //case 2
        int righth = height(cur.right);
        if (righth == -2) return -2; //case 2
        
        //case 1
        if (Math.abs(lefth - righth) > 1) return -2; //if unbalanced, return -2, meaning we find the unbalanced node
        return 1 + Math.max(lefth, righth);

        //When is a node unbalanced (heigt invalid)?
        //case 1. when abs(left_height - right_height) > 1
        //case 2. when its left subtree or right subtree is unbalanced

        //case 2 is prone to be neglected, and we must check case 2 because:
        //even if a node returns height == -2, its parent calls the height (lefth, righth) within
        //the function, so the invalid height is only stored in lefth/righth variable, but not returned
        //outside the helper function and directly to the boolean comparison statement in isBalanced().
        //Therefore, we also need to check if lefth & righth are valid, because otherwise,
        //Math.abs(lefth - righth) may return a valid height (eg. -2 - (-2) = 0 -->valid, even though 
        //both of the subtree heights are INVALID). And this will result in a wrong answer

        //Summary:
        //if a node is not balanced, set its height to -2
        //For a given node, check its left child, right child and itself. If any of them  is not 
        //balanced, return -2 and no need for further calculation.
    }
}