/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//beats 97.93% runtime
//one pass only, Time Complexity = O(N), N = size of list
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1); //dummy head
        ListNode prev = dummy;
        ListNode cur = head;
        
        //normal order
        for (int i = 1; i < m; i++) {
            prev.next = cur;
            prev = prev.next;
            cur = cur.next;
        }
        
        //reverse order
        ListNode reverseTail = cur;
        for (int i = m; i <= n; i++) {
            ListNode next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = next;
        }
        prev = reverseTail;
        prev.next = null; //This can either be placed here or after the next for loop
        
        //normal order
        for (int i = n+1; cur != null; i++) {
            prev.next = cur;
            prev = prev.next;
            cur = cur.next;
        }
        //prev.next = null;
        
        return dummy.next;
    }
}



//The better version, don't need the last for loop
//More concise and efficient
//Time Complexity = O(n), n == int n of method parameter <= size of list
//beats 97.93%
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode prev = dummy; // make a pointer pre as a marker for the node before reversing
        ListNode cur = head;
        for (int i = 1; i < m; i++) prev = prev.next;
        
        //reverse order
        ListNode start = prev.next; //the original start of the sublist that will be reversed, aka the end of the sublist after reverse
        cur = start.next; //a pointer to current node that will be reversed
        
        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, cur = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5
        
        for (int i = m + 1; i <= n; i++) {
            start.next = cur.next;
            cur.next = prev.next;
            prev.next = cur;
            cur = start.next;
        }
        
        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, cur = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, cur = 5 (finish)
        
        return dummy.next;
    }
}