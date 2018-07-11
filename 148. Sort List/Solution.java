//Time = O(N*logN), Space = O(1)
/*
解题报告：就是对一个链表进行归并排序。
主要考察3个知识点，
知识点1：归并排序的整体思想
知识点2：找到一个链表的中间节点的方法
知识点3：合并两个已排好序的链表为一个新的有序链表

!!!!!!!归并排序的基本思想是：找到链表的middle节点，然后递归对前半部分和后半部分分别进行归并排序，
最后对两个以排好序的链表进行Merge。
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //Core idea of merge sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode middle = getMiddleOfList(head);
        ListNode rightHalf = middle.next;
        middle.next = null; //split the list into left half and right half, This line is important!!!
        return mergeTwoLists(sortList(head), sortList(rightHalf));
    }
    

    //Find the middle of the list, using two-pointer method (slow and fast)
    private ListNode getMiddleOfList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    

    //Merge two sublists
    private ListNode mergeTwoLists(ListNode A, ListNode B) {
        ListNode dummy = new ListNode(-1); //IMPORTANT!!!! Much more convenient to use
        ListNode cur = dummy;
        while (A != null && B != null) {
            if (A.val <= B.val) {
                cur.next = A;
                A = A.next;
            } else {
                cur.next = B;
                B = B.next;
            }
            cur = cur.next;
        }
        cur.next = (A == null) ? B : A;
        return dummy.next;
    }
}