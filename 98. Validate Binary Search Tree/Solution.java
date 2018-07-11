/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Recursive Solution
/**
If null node or leaf node (no child) -->is valid BST, return true.
Otherwise, Check two things:
1. Left < root, right > root
2. Rightmost child of left < root, Leftmost child of right > root
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        boolean left = root.left == null || root.left.val < root.val;
        boolean right = root.right == null || root.right.val > root.val;
        if (!left || !right) return false;
        TreeNode left_max = root.left;
        if (left_max != null) {
            while (left_max.right != null) left_max = left_max.right;
        }
        TreeNode right_min = root.right;
        if (right_min != null) {
            while (right_min.left != null) right_min = right_min.left;
        }
        return (left_max == null || left_max.val < root.val) && 
            (right_min == null || right_min.val > root.val) &&
            isValidBST(root.left) && isValidBST(root.right);
    }
}

//Iterative Solution, Time=O(n), Space=O(n)
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.empty()) { //root != null is for case when we go through and pop out all nodes in the left subtree and root, at that moment stack is empty since we have not pushed nodes in right subtree into it, but we stil need to proceed, so the loop continue case should be either root != null or stack is not empty
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && prev.val >= root.val) return false;
            prev = root;
            root = root.right;
        }
        return true;
    }
}