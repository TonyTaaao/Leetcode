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
        TreeLinkNode queue = root; //node in current level, iterating from left to right
        TreeLinkNode startofrow = new TreeLinkNode(0); //starting point of next level, each node in this row is the child of queue(current level), iterating through current level(queue) to connect adjacent nodes in next level (row = next level)
        while (queue != null) {
            startofrow.next = null; //each time you go to next row, you clear the nodes from preceding row and restart from the start of this new row.
            TreeLinkNode cur = startofrow;
            while (queue != null) {
                if (queue.left != null) {
                    cur.next = queue.left;
                    cur = cur.next;
                }
                if (queue.right != null) {
                    cur.next = queue.right;
                    cur = cur.next;
                }
                queue = queue.next;
            }
            queue = startofrow.next;
        }
        //After queue goes to the last row (last row has no children), and startofrow is set 
        //to null at line 14, the pseudo node startofrow (it is not part of the ree,
        //only an auxilary node to help us connect next nodes) will be disconnected
        //from the rest of the tree. Since its next is set to null and cur won't 
        //connect any more nodes for it since that row since does not have nodes.
    }
}