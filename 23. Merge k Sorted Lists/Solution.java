/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Approach 1: Brute Force, beats 41.94%
/*
Algorithm:
1. traverse all linked lists and store all nodes into an array. --> O(n)
2. sort the array --> O(nlogn)
3. Create a new linked list (result) and add array items into it. --> O(n)

Time Complexity = O(nlogn), n=total number of nodes.
Space Complexity = O(n)
*/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<ListNode> allNodesSorted = new ArrayList<>();
        for (ListNode listNode : lists) {
            while (listNode != null) {
                allNodesSorted.add(listNode);
                listNode = listNode.next;
            }
        }
        Collections.sort(allNodesSorted, new ListNodeComparator());
        
        ListNode result = new ListNode(-1);
        ListNode cur = result;
        for (ListNode node : allNodesSorted) {
            cur.next = node;
            cur = cur.next;
        }
        cur.next = null;
        return result.next;
    }
    
    private class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode l, ListNode r) {
            if (l.val < r.val) return -1;
            else if (l.val == r.val) return 0;
            else return 1;
        }
    }
}

// same approach, without creating ListNodeComparator class, beats 42.99%
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<ListNode> allNodesSorted = new ArrayList<>();
        for (ListNode listNode : lists) {
            while (listNode != null) {
                allNodesSorted.add(listNode);
                listNode = listNode.next;
            }
        }
        Collections.sort(allNodesSorted, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l, ListNode r) {
                if (l.val < r.val) return -1;
                else if (l.val == r.val) return 0;
                else return 1;
            }
        });
        
        ListNode result = new ListNode(-1);
        ListNode cur = result;
        for (ListNode node : allNodesSorted) {
            cur.next = node;
            cur = cur.next;
        }
        cur.next = null;
        return result.next;
    }
}



// Approach 2: Compare one by one
/*
Algorithm:
1. Compare every k nodes (head of every linked list) and get the node with the smallest value.
1. Extend the final sorted linked list with the selected nodes.

Time Complexity = O(kN), n=total number of nodes, k = number of linked lists.
Space Complexity = O(n)
*/
// beats 5.92%
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1); // dummy --> actual ans, return dummy.next
        ListNode cur = dummy;
        int numberOfFinishedLists = 0;
        int n = lists.length;
        while (numberOfFinishedLists < n) {
            // find current smallest node and add to ans
            int minIndex = -1;
            for (int i = 0; i < n; i++) {
                if (lists[i] != null) {
                    if (minIndex == -1 || (lists[i].val < lists[minIndex].val)) minIndex = i;
                }
            }
            if (minIndex == -1) break;
            cur.next = new ListNode(lists[minIndex].val);
            cur = cur.next;
            lists[minIndex] = lists[minIndex].next;
            if (lists[minIndex] == null) {
                numberOfFinishedLists++;
            }
        }
        return dummy.next;
    }
}



// Approach 3: Optimize Approach 2 by Priority Queue, beats 47.6%
/*
Algorithm:
Almost the same as the one above but optimize the comparison process by priority queue.

PriorityQueue provides O(log(n)) time for the enqueing and dequeing methods (offer, poll, 
remove() and add); linear time for the remove(Object) and contains(Object) methods; and 
constant time for the retrieval methods (peek, element, and size).

Time complexity = O(Nlogk) where k is the number of linked lists.
1. 	The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue.
	But finding the node with the smallest value just costs O(1) time.
2. 	There are N nodes in the final linked list.

Space complexity: O(n+k), O(n) for result linked list, O(k) for priority queue.
*/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // PriorityQueue does not permit null elements. If you add null into a PriorityQueue, it throws NullPointerException.
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l, ListNode r) {
                if (l.val < r.val) return -1;
                else if (l.val == r.val) return 0;
                else return 1;
            }
        });
        
        for (ListNode l : lists) {
            if (l != null) pq.add(l);
        }
        
        ListNode result = new ListNode(-1);
        ListNode cur = result;
        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            if (smallest.next != null) pq.add(smallest.next);
            cur.next = smallest;
            cur = cur.next;
        }
        return result.next;
    }
}



// Approach 4: Merge lists one bu one, see LC Solution for explanation.


// Approach 5: Merge with Divide And Conquer, beats 92.38%
// Time Complexity = O(NlogK), N nodes in total, each node will be merged logK times.
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int size = lists.length;
        if (size == 0) return null;
        
        int interval = 1;
        while (interval < size) {
            for (int i = 0; i + interval < size; i += 2*interval) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }
    
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead; // current pointer
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        else cur.next = l2;
        return dummyHead.next;
    }
}