/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//root level只有一个node，所以root level是不需要check是否对称的。
//要从第二行（root的left和right）开始比。
//所以recursion的function parameter是left和right两个，而不是一个node，因为只有左右互相比才能判断出是否对称，
//单一node不知道自己要和谁比，无法判断是否对称。

//Recursive
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null || helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}

//Iterative
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> level = new LinkedList<>();
        /*Queue implementations generally do not allow insertion of null elements, although some implementations, such as 
        LinkedList, do not prohibit insertion of null. Even in the implementations that permit it, null should not be inserted 
        into a Queue, as null is also used as a special return value by the poll method to indicate that the queue contains no elements.
        */
        level.add(root.left); //can add null to LinkedList
        level.add(root.right);
        while (!level.isEmpty()) {
            TreeNode left = level.poll();
            TreeNode right = level.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;
            level.add(left.left);
            level.add(right.right);
            level.add(left.right);
            level.add(right.left);
        }
        return true;
    }
}