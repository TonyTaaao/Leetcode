/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        
        int reversedCount = 1;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = head;
        while (reversedCount < k && prev.next != null) {
            ListNode temp = prev.next;
            prev.next = temp.next;
            temp.next = dummyHead.next;
            dummyHead.next = temp;
            reversedCount++;
        }
        return dummyHead.next;
    }
}