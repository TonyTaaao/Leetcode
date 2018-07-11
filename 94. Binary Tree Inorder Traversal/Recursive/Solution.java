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
    public List<Integer> inorderTraversal(TreeNode root) {
        //if (root == null) return new ArrayList(); /** No need for this line. */
        List<Integer> ans = new ArrayList();
        inorderTraversal(root, ans);
        return ans;
    }
    
    //写法1
    /*
    private void inorderTraversal(TreeNode current, List<Integer> list) {
        if (current != null) {
            inorderTraversal(current.left, list);
            list.add(current.val);
            inorderTraversal(current.right, list);
        }
    }
    */

    //写法2
    private void inorderTraversal(TreeNode current, List<Integer> list) {
        if (current == null) return;
        inorderTraversal(current.left, list);
        list.add(current.val);
        inorderTraversal(current.right, list);
    }
}