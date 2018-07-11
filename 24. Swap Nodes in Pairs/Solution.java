/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//Wrong Answer
/*
Input:
[1,2,3,4]
Output:
[2,1,3]
Expected:
[2,1,4,3]

Why this is wrong:
we only swapped current pair here, but forget to update previous pair.next
eg. first pair = (1,2) --> we swap it to (2,1), 1.next = 3
second pair = (3,4), --> we swap it to (4,3), but 1.next is still 3, it should be updated
to 4, but we didn't do this
*/
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode cur = head;
        head = cur.next;
        while (cur != null && cur.next != null) {
            ListNode nextPair = cur.next.next;
            cur.next.next = cur;
            cur.next = nextPair;
            cur = nextPair;
        }
        return head;
    }
}



//Iterative Solution, Time = O(N), Space = O(1)
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode start = new ListNode(-1); //dummy node, easier to manipulate the list
        start.next = head;
        
        ListNode cur = start;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next.next;
            ListNode second = cur.next;
            ListNode nextPair = first.next;
            
            cur.next = first;
            first.next = second;
            second.next = nextPair;
            cur = second;
        }
        return start.next;
    }
}



//Recursive Solution, Time = O(N)
/*
This may or may not be considered a constant-space solution.
Unless compiler is geared towards tail recursion, this will cause stack overflow 
if the list is large enough (due to the function calls). 

The above statement is true, but that's the case for any recursive implementation 
on a problem with a non-determinate end. By that logic, recursion should never be 
used on any linked list, trees, or any other structure where the recursive solution 
is the most likely the simplest and the most elegant - like we have here.

No one here is writing production code to solve simple LeetCode problems. Recursion is fine.
*/
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newhead = head.next;
        head.next = swapPairs(head.next.next);
        newhead.next = head;
        return newhead;
    }
}