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

//O(1) Space Solution
/*
Idea: Use two pointers, walker and runner.
walker moves step by step. runner moves two steps at time.
If the Linked List has a cycle, then walker and runner will meet at some point.

为什么有环的情况下二者一定会相遇呢？因为fast先进入环，在slow进入之后，如果把slow看作在前面，
fast在后面每次循环都向slow靠近1，所以一定会相遇，而不会出现fast直接跳过slow的情况。
*/
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode walker = head, runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }
        return false;
    }
}




//Recursive Solution
/*
Note that "head.next = head". This makes node.next point to itself. 
That is, each node that we have visited points to itself. So if there exist a loop,
we will go back to a node already visited before, so we will finally visit a node 
that points to itself.
*/
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        if (head.next == head) return true; //if a node points to itself, has cycle
        ListNode nextNode = head.next;
        head.next = head; //all nodes that have been visited will point to itself
        return hasCycle(nextNode);
    }
}