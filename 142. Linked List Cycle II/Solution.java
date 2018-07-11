/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

//HashSet Solution, Time = O(N), Space = O(N)
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        Set<ListNode> visited = new HashSet<>(); //stores all visited nodes
        ListNode cur = head;
        while (cur != null) {
            //set.add(E e) returns true if e is added to set, aka e is not a duplicate
            if (visited.add(cur)) cur = cur.next;
            else return cur;
        }
        return null;
    }
}




//Two Pointer Solution, Time = O(N), Space = O(1)
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}