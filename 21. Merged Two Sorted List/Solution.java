/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //recursive solution
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    
    /*
    iterative solution
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode current = ans;
        
        while (l1 != null && l2 != null) {
            current.next = l1.val < l2.val ? l1 : l2;
            current = current.next;
            if (l1.val < l2.val) l1 = l1.next;
            else l2 = l2.next;
        }
        if (l1 != null) current.next = l1;
        else current.next = l2;
        
        return ans.next;
    }
    */
}