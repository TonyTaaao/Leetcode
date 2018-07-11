/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//THis is wrong.
/*
We set a pointer that points to the last node, aka tail, of the first-half partitioned list where all nodes.val < x.
Iterate through the list, if we encounter a node whose val < x, we set pointer.next to this node, 
parent(this node).next = this node.next, this node.next = pointer.next.next, and set this node as pointer
*/
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode start = new ListNode(-1);
        start.next = head;
        
        ListNode pointer = (head.val < x) ? head : start; //pointer points to the tail of the partitioned list where all nodes < x
        ListNode cur = pointer;
        while (cur != null && cur.next != null) {
            if (cur.next.val < x) {
                ListNode secondHalf = pointer.next;
                pointer.next = cur.next;
                cur.next = cur.next.next;
                pointer = pointer.next;
                if (secondHalf != null) pointer.next = secondHalf;
            } else {
                cur = cur.next;
            }
        }
        return start.next;
    }
}
//This does not work for cases like [1,1,1,1,1], val = 3
//where all elements < x, there is no secondHalf, so secondHalf = pointer.next is 
//still within first half --> causing an infinite loop, time limit exceeded



//Correct Solution
/*
思路：刚开始试着把所有小于x的结点依次插到前面去，但是因为第一个和最后结点的问题真的把我搞得焦头烂额，
后来想想，用分开建立两个half list的方法可能更清晰一点，用两个链表分别连接小于和大于等于x 的结点，
然后再把两个结点链接到一起，就可以了。在实施的时候稍微偷点懒，首先创建两个头节点，哎，现在终于明白
头节点的巨大作用了，其实，按我的那个思路，先搞个头节点，然后再用两个引用pre和cur就可以轻松搞定
本题了。
做完本题感觉收获好大，头节点的出现真的让我可以很轻松地搞定许多前面我费了好大的劲才搞定的题目，
尤其是涉及到在链表的开始进行插入的题目。
*/
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        
        ListNode first = new ListNode(-1); //first half of the list, all nodes in it < x
        ListNode second = new ListNode(-1); //second half of the list, all nodes in it >= x
        ListNode tail1 = first, tail2 = second; //add node at the end of its respective half
        ListNode cur = head; //used to iterate through list
        while (cur != null) {
            if (cur.val < x) {
                tail1.next = cur;
                tail1 = tail1.next;
            } else {
                tail2.next = cur;
                tail2 = tail2.next;
            }
            cur = cur.next;
        }
        
        tail1.next = null; //clean first half
        tail2.next = null; //clean second half
        
        //Connect first and second
        first = first.next;
        second = second.next;
        if (first != null) {
            tail1.next = second;
            return first;
        } else {
            return second;
        }
    }
}
/*
Why do we need line 73-74?
ex. Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
In this problem, firstHalf = 1-2-2, tail1 = the last 2,
secondHalf = 4-3-5, tail2 = the last 5, 
but notice that tail2 == 5 is connected to a 2 that is not supposed to be in
the secondHalf, because of 5->2 in the original list
So in order to delete whatever after the partitioned tail that is not supposed to
be there but connected in the original list, we must set tail.next to null to
clean/delete the unwanted part.
*/





//Solution: more concise version
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode first = new ListNode(-1); //first half of the list, all nodes in it < x
        ListNode second = new ListNode(-1); //second half of the list, all nodes in it >= x
        ListNode tail1 = first, tail2 = second; //tail pointer
        while (head != null) {
            if (head.val < x) {
                tail1.next = head;
                tail1 = tail1.next;
            } else {
                tail2.next = head;
                tail2 = tail2.next;
            }
            head = head.next;
        }
        
        tail2.next = null; //delete unnecessary part connected to tail2
        
        //Connect first and second
        tail1.next = second.next;
        return first.next;
    }
}