/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


//BFS
//Go through each node once, Time = O(n) because LinkedList.add(0, cur.val) takes O(1) time.
//For LinkedList, adding to head and adding to tail both takes O(1) time due to head/tail pointer
//Java LinkedList is a doubly-linked list!!!!!!!
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>(); //at line 36, LinkedList.add() takes O(1) time, 
        //better than ArrayList.add() O(N) time.
        if (root == null) return res;
        
        Queue<TreeNode> level = new LinkedList<>(); //Java LinkedList is a doubly-linked list, remove head takes O(1) time, remove tail takes O(1) time
        level.add(root);
        boolean reverse = false;
        int size = level.size();
        
        while (size != 0) {
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = level.poll();
                if (reverse) temp.add(0, cur.val);
                else temp.add(cur.val);
                if (cur.left != null) level.add(cur.left);
                if (cur.right != null) level.add(cur.right);
            }
            reverse = !reverse;
            res.add(temp);
            size = level.size();
        }
        return res;
    }
}



//DFS
/***
O(N^2) worst runtime solution by using LinkedList along with ArrayList. 
Insertion in the inner list (LinkedList) is O(1), 
Insertion in the outer list (happens when creating a new level) is O(N) due to ArrayList backup array resizing.
Get level in outer list (ArrayList) is O(1) due to direct index access.

Using DFS and creating new lists when needed.
*/
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        addToList(root, res, 0);
        return res;
    }
    
    private void addToList(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) return;
        if (res.size() <= level) {
            List<Integer> newLevel = new LinkedList<>();
            res.add(newLevel);
        }
        
        if (level % 2 == 0) res.get(level).add(root.val);
        else res.get(level).add(0, root.val);
        addToList(root.left, res, level + 1);
        addToList(root.right, res, level + 1);
    }
}