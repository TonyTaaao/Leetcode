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
    //写法1
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        TreeNode current = root; //pointer to iterate through the whole tree
        while (current != null || !stack.empty()) {
            while (current != null) {
                stack.push(current);
                current= current.left;
            }
            current = stack.pop();
            ans.add(current.val);
            current = current.right;
        }
        return ans;
    }

    //写法2
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        TreeNode current = root; //pointer to iterate through the whole tree
        while (current != null || !stack.empty()) {
            if (current != null) { //两种写法的区别在这里，1.while, 2.if else
                stack.push(current);
                current= current.left;
            } else {
                current = stack.pop();
                ans.add(current.val);
                current = current.right;
            }
        }
        return ans;
    }
}