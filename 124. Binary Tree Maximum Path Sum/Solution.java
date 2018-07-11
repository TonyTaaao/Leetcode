/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


// Solution 1: Bottom-Up dfs
// beats 99.93%
/*
Here's the idea:

A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or 
more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the 
lowest common ancestor of all other nodes on the path.
A recursive method findMax(TreeNode node) (1) computes the maximum path sum with highest 
node is the input node, update maximum if necessary (2) returns the maximum sum of the path 
that can be extended to input node's parent.
*/

/* Another good explanation:
Not many problems exhibit such a clear sub-structure. Here we need to simply solve the problem 
for a tree with up to two levels and then recursively repeat that logic.

Maximum path can be formed only in a few ways:

Root + Left sub-tree
Root + Right sub-tree
Just the Root, in case left and right sums are -ve.
Root + Left + Right sub-trees, this is closed path
Only greater of the first three values can move up the ladder. Repeat this at every step, and 
we have a bottom up solution.
*/
class Solution {
    int maxValue;
    
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        findMax(root);
        return maxValue;
    }
    
    // a bottom-up DFS approrach to find max path sum with node as root of the tree
    private int findMax(TreeNode node) {
        if (node == null) return 0;
        
        int left = Math.max(0, findMax(node.left));
        int right = Math.max(0, findMax(node.right));
        maxValue = Math.max(left + right + node.val, maxValue);
        return Math.max(left, right) + node.val;
    }
}




// Solution 1 without using global variable
class Solution {
    // int is a primitive type which passes by value (a copy of it)
    // in order to let ans itself be updated(instead of updating a copy), we create an OBJECT
    // to replace the primitive type
    class Int {
        int val;
        Int(int x) {
            val = x;
        }
    }
    
    public int maxPathSum(TreeNode root) {
        Int ans = new Int(Integer.MIN_VALUE);
        findMax(root, ans);
        return ans.val;
    }
    
    // a bottom-up DFS approrach to find max path sum with node as root of the tree
    private int findMax(TreeNode node, Int maxValue) {
        if (node == null) return 0;
        
        int left = Math.max(0, findMax(node.left, maxValue));
        int right = Math.max(0, findMax(node.right, maxValue));
        maxValue.val = Math.max(left + right + node.val, maxValue.val);
        return Math.max(left, right) + node.val;
    }
}