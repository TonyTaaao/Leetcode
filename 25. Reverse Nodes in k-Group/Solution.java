/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Approach 1: Iterative
// Time Complexity = O(n), visit each node once
// Space Complexity = O(1), constant extra memory
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode result = new ListNode(-1);
        result.next = head;
        // dummy head is the node before current K group head: dummyHead.next == current K group
        ListNode dummyHead = result;
        while (head != null) {
            dummyHead = reverseKNodes(dummyHead, head, k); // reverse current K group
            if (dummyHead == null) break;
            else {
                head = dummyHead.next; // continue on the next K group
            }
        }
        return result.next;
    }
    
    // reverse current k nodes, and return dummy head for the next K group
    // dummy head of enxt K group = tail of current K group
    private ListNode reverseKNodes(ListNode dummyHead, ListNode head, int k) {
        ListNode cur = head;
        int length = 0;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        if (length < k) return null;
        
        int reversedCount = 1;
        while (reversedCount < k) {
            ListNode temp = head.next;
            head.next = temp.next;
            temp.next = dummyHead.next;
            dummyHead.next = temp;
            reversedCount++;
        }
        return head; // head is the head of original list == tail of reversed list
        /*
        original list = 1-2-3-4, head = 1
        reversed list = 4-3-2-1, tail = 1 == original head
        */
    }
}



// Approach 2: Recursive
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part, 
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group: 
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list 
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}