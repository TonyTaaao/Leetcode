/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//Solution1
/*
My solution has O(n) time complexity and O(1) memory.
The basic idea is to connect the list into a circle. First, count the length of list 
while going through the list to find the end of it. Connect the tail to head. The 
problem asked to rotate k nodes, however, now the tail is at the end of the list and 
its difficult to move backward, so move (k - len) nodes along the list instead. 
"k = k % len" saves the unnecessary moves because rotate a list with length = len 
by len times doesn't change the list at all.

So the code has three parts:
 1. Get the length
 2. Move to the (l-n%l)th node
 3. Do the rotation
*/
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        
        ListNode tail = head;
        int size; //size of the list
        for (size = 1; tail.next != null; size++) { //get the size of the list
            tail = tail.next;
        }
        
        tail.next = head; //form a cycle
        k %= size;
        for (int i = 1; i <= size - k; i++) {
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        return head;
    }
}





//HashMap Solution: Time Limit Exceed
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        
        Map<Integer, ListNode> map = new HashMap<>();
        int index = 0;
        ListNode cur = head;
        while (cur != null) {
            map.put(index++, cur);
        }
        int size = index;
        k %= size; //k = k % size;
        if (k == 0) return head;
        
        ListNode newhead = map.get(size - k);
        map.get(size - k - 1).next = null;
        map.get(size - 1).next = head;
        //head = newhead;
        return newhead;
    }
}