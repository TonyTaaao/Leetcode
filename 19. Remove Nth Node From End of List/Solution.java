/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//Solution 1
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (n-- != 0) {
            fast = fast.next;
        }
        if (fast == null) return head.next;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}



//Solution 1 with dummy node
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        
        ListNode start = new ListNode(-1);
        start.next = head;
        ListNode slow = start, fast = start;
        
        //Keep (N+1) distance between slow and fast
        //-->the distance between the node we need to remove and the null node at the end of the list = N,
        //so the node preceeding the to-be-removed one should be (N+1) nodes ahead of the null node at the end
        for (int i = 1; i <= n+1; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }
}



//Solution 2: HashMap
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;
        Map<Integer, ListNode> map = new HashMap<>();
        int n_th = 1;
        ListNode cur = head;
        while (cur != null) {
            map.put(n_th++, cur);
            cur = cur.next;
        }
        
        int size = n_th - 1; //list size
        if (n == size) head = head.next;
        else if (n == 1) map.get(size -1).next = null; //Directly set map.get(size) == null won't work, Google "Java : setting object to null within a method has no effect (Reusing code)" for why
        else map.get(size - n).next = map.get(size - n).next.next;
        return head;
    }
}