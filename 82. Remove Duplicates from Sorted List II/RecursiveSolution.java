/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//beats 100% runtime
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        
        //if head is not duplicate
        if (head.next == null || head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
        } else {
            int duplicate = head.val;
            head = head.next.next; //skip head and head.next since they are both duplicates
            while (head != null && head.val == duplicate) head = head.next;
            return deleteDuplicates(head);
        }
        
        return head;
    }
}



//version2, same idea, different with line 17-20
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        if (head.next != null && head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
        }
        return head;
    }
}


//version3, self-written
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        
        //if duplicate
        if (head.val == head.next.val) {
            int duplicate = head.val;
            //skip all duplicates
            head = head.next.next;
            while (head != null && head.val == duplicate) head = head.next;
            return deleteDuplicates(head);
        } else {
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}