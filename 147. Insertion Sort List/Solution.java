/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//O(1) Space Solution
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode start = new ListNode(Integer.MIN_VALUE); //dummy node
        ListNode prev = start; //insert node between pre and pre.next
        
        while (head != null) {
            ListNode rest = head.next;
            
            /* Before insert, prev is the last node of the sorted list.
           We should move prev back to head only if the last node's value > the current node.
           */
            if (prev.val > head.val) prev = start; //if current node < prev.val, let prev go back to start and then insert at the right place
            
            //find the right place to insert
            while (prev.next != null && prev.next.val < head.val) {
                prev = prev.next;
            }
            
            //insert between pre and pre.next
            head.next = prev.next;
            prev.next = head;
            
            //move on to the rest of the list
            head = rest;
        }
        return start.next;
    }
}