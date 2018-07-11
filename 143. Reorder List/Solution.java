/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//HashMap Solution, Time = O(2N), scan the list twice, Space = O(N)
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode cur = head;
        int index = 0;
        while (cur != null) {
            map.put(index++, cur);
            cur = cur.next;
        }
        
        int size = index;
        ListNode start = new ListNode(-1); //dummy node
        cur = start;
        for (int i = 0; i < size / 2; i++) {
            cur.next = map.get(i);
            cur = cur.next;
            cur.next = map.get(size - 1 - i);
            cur = cur.next;
        }
        if (size % 2 != 0) {
            cur.next = map.get(size / 2);
            cur = cur.next;          
        }
        cur.next = null; //don't forget to cut unnecessary part behind the new tail
    }
}


//O(1) Space Solution, Time = O(N/2 + N/2 + N/2) = O(1.5N) = O(N)
/*
思路：先使用快慢指针将链表从中间分割成两段，然后后半段就地逆置．之后合并插入到前半段链表即可.
3 Steps:
1. Find part2;
2. Reverse part2;
3. Append each node in part2 to part1, one by one.
*/
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        
        //Find the middle of the list
        ListNode p1 = head, p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        ListNode preMiddle = p1; //1->2->3->4->5->6, preMiddle = 3 = tail of first half
        
        //Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
        ListNode tail = preMiddle.next; //tail = start of the original second half = 4
        while (tail.next != null) {
            ListNode cur = tail.next;
            tail.next = cur.next;
            //preMiddle.next = cur; //This should be placed after next line
            cur.next = preMiddle.next;
            preMiddle.next = cur;
        }
        
        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
        p1 = head;
        //p2 = preMiddle.next;
        while (p1 != preMiddle) {
            ListNode toInsert = preMiddle.next;
            preMiddle.next = toInsert.next;
            toInsert.next = p1.next;
            p1.next = toInsert;
            p1 = toInsert.next;
        }
    }
}