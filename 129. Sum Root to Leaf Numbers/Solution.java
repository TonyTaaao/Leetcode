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
class Solution {
    int sum = 0;
    
    public int sumNumbers(TreeNode root) {
        dfsum(root, 0);
        return sum;
    }
    
     private void dfsum(TreeNode cur, int cur_num) {
         if (cur == null) return;
         cur_num += cur.val;
         if (cur.left == null && cur.right == null) sum += cur_num;
         else {
             dfsum(cur.left, 10*cur_num);
             dfsum(cur.right, 10*cur_num);
         }
     }
}

//Recursive Solution without global variable
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfsum(root, 0);
    }
    
     private int dfsum(TreeNode cur, int sum) {
         if (cur == null) return 0;
         sum = 10 * sum + cur.val;
         if (cur.left == null && cur.right == null) return sum;
         return dfsum(cur.left, sum) + dfsum(cur.right, sum);
     }
}

//Iterative soln
class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> level = new LinkedList(); //FIFO
        level.add(root);
        int sum = 0;
        
        TreeNode cur;
        while (!level.isEmpty()) {
            cur = level.poll();
            if (cur.left != null) {
                cur.left.val += 10 * cur.val;
                level.add(cur.left);
            }
            if (cur.right != null) {
                cur.right.val += 10 * cur.val;
                level.add(cur.right);
            }
            if (cur.left == null && cur.right == null) sum += cur.val;
        }
        return sum;
    }
}