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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        
        Queue<TreeNode> level = new LinkedList<>(); //store all nodes at current level, BFS
        level.add(root);
        TreeNode temp = null;
        int size = 0;
        while (level.size() != 0) {
            res.add(level.peek().val);
            size = level.size();
            while (size != 0) {
                temp = level.poll();
                if (temp.right != null) level.add(temp.right);
                if (temp.left != null) level.add(temp.left);
                size--;
            }
        }
        return res;
    }
}