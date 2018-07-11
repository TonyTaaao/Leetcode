/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        //The key point is that we need to return a NEW copy, so any node in our answer has to 
        //be a new object, and it must point to a new copy of next/random, not the original one
        Map<RandomListNode, RandomListNode> map = new HashMap();
        RandomListNode cur = head;
        //map.get(null) is null, so we can reduce the check
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random); //cur.random may be null, but map.get(null) 
            //is null, so we can reduce the check
            cur = cur.next;
        }
        return map.get(head);
    }
}