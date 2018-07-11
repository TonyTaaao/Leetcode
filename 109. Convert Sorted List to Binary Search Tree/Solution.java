/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        return toBST(head, null);
    }
    
    private TreeNode toBST(ListNode start, ListNode end) {
        if (start == end) return null;
        
        ListNode slow = start; //middle node
        ListNode fast = start; //tail node
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //参考LC108，要找到中间点设为root，然后分别assign left subtree和right subtree
        //Starting from START, each time FAST moves two nodes forward and SLOW moves one node forward.
        //So by the time when FAST reaches end of list, SLOW would be at the middle of the list.
        //we assign the middle node as root, and then assign its left and right child by splitting 
        //the list into first half (from START to MIDDLE) and second half (from MIDDLE.next to END)
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(start, slow);
        root.right = toBST(slow.next, end);
        return root;
    }
}

//Time Complexity = O(n*logn)
//Each time, it takes O(logn) time to find the middle node since you have to go through half 
//of the list to find it.
//n->n/2->n/4->n/8->...->1, this process takes O(logn) time
//And you have to do this for all N nodes
//T(logn per node) * T(n nodes) = T(n*logn)