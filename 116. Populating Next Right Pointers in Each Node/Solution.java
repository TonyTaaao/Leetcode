/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode level_start = root; //first node of current row
        TreeLinkNode cur;
        while (level_start != null) {
            cur = level_start;
            while (cur != null) {
                if (cur.left != null) cur.left.next = cur.right; //if bottom line (leaf node level), cur.left == null
                if (cur.right != null && cur.next != null) cur.right.next = cur.next.left; //if bottom line (leaf node level), cur.right == null
                cur = cur.next;
            }
            level_start = level_start.left;
        }
    }
}