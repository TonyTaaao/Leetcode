/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//Best version, beats 100%
//Time Complexity = O(N), N = size of list
//Idea: skip all duplicates, and add unique element to result list
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        
        ListNode dummy = new ListNode(-1); //result with a dummy head
        ListNode prev = dummy;
        ListNode cur = head; //pointer to iterate through the list
        
        while (cur != null) {
            //if duplicate, skip all duplicates-->move on to the next value
            if (cur.next != null && cur.val == cur.next.val) {
                int duplicate = cur.val;
                cur = cur.next.next; //skip cur and cur.next since they are duplicate
                //skip all current duplicates, move on to the next value
                while (cur != null && cur.val == duplicate) cur = cur.next;
            }
            //if unique, add to result
            else {
                prev.next = cur;
                prev = prev.next;
                cur = cur.next;
            }
            prev.next = null; //cut unnecessary parts behind the last unique node
        }
        
        return dummy.next;
    }
}

//-------------------------------------------------------------------------------
//Version1, beats 51% runtime
//Time Complexity = O(N), N = size of list
//Idea: skip all duplicates, and add unique element to result list
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode res = new ListNode(-1); //result with a dummy head
        ListNode prev = res;
        ListNode cur = head;
        
        while (cur != null) {
            //if duplicate
            if (cur.next != null && cur.val == cur.next.val) {
                int duplicate = cur.val;
                cur = cur.next.next; //skip cur and cur.next since they are duplicate, so they won't be added to res
                while (cur != null && cur.val == duplicate) cur = cur.next;
            }
            //after skipping all current duplicates, cur is either null, or a unique node, or the next duplicate
            //we need should add cur if it is null or unique, we should not add cur if it is the next duplicate
            if (cur == null || cur.next == null || cur.val != cur.next.val) {
                prev.next = cur;
                prev = prev.next;
                if (cur != null) cur = cur.next;
            }
        }
        
        return res.next;
    }
}




//Version2, same idea, slightly improved logic
//Idea: skip all duplicates, and add cur to result list
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        
        ListNode dummy = new ListNode(-1); //result with a dummy head
        ListNode prev = dummy;
        ListNode cur = head;
        
        while (cur != null) {
            //if duplicate
            if (cur.next != null && cur.val == cur.next.val) {
                int duplicate = cur.val;
                cur = cur.next.next; //skip cur and cur.next since they are duplicate, so they won't be added to res
                while (cur != null && cur.val == duplicate) cur = cur.next;
                //after skipping all current duplicates, cur is either null, or a unique node, or the next duplicate
                //We only check cur.val != current duplicate, but we need to check if cur.val == the next duplicate
                
                //if cur is the next duplicate, we need to skip it, do not add to res
                if (cur != null && cur.next != null && cur.val == cur.next.val) continue;
            }
            //we can add unique node or null to res
            prev.next = cur;
            prev = prev.next;
            if (cur != null) cur = cur.next;
        }
        
        return dummy.next;
    }
}