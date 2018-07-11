/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//Solution1: using dummy head, append unique nodes to dummy
//beats 43% runtime
//Time Complexity = O(N), iterate through the whole list
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1); // dummy head
        ListNode prev = dummy;
        ListNode cur = head; //for iterating through the whole list
        while (cur != null) {
            prev.next = cur;
            prev = prev.next;
            //skip duplicate element
            while (cur.next != null && cur.val == cur.next.val) cur = cur.next;
            cur = cur.next;
        }
        prev.next = null; //cut unnecessary part after the last unique node
        return dummy.next;
    }
}



//Solution2: no dummy, directly modify the list itself
//beats 100% runtime
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode list = head;
        while (list != null) {
            if (list.next != null && list.val == list.next.val) {
                list.next = list.next.next; //delete the duplicate node
            } else {
                list = list.next;
            }
        }
        return head;
    }
}



//Solution3: Recursion
//beats 100% runtime
/*
Highlight:
I highly doubt if we should use recursion in solving linked list problems. 
We use it for tree because its stack space is O(logn), where n is the number of 
nodes. But it's O(n) space required for linked list, which is very likely to be 
stack overflow. Point me out if you hold a different opinion.

every time we make a recursive call, it places a frame on our stack memory. 
Because we must make a recursive call for each node in list, recursion is often not 
the best way to manipulate lists if it can be avoided.
Since recursive might cause stack overflow.
*/
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        head.next = deleteDuplicates(head.next);
        return (head.next != null && head.val == head.next.val) ? head.next : head;
    }
}