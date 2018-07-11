/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//BFS, Time = O(N)
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>(); //Use LinkedList instead of ArrayList because
        //LinkedList.add(E) takes O(1) time, while ArrayList.add() takes O(N) time due to 
        //backup array resizing
        if (root == null) return res;
        
        Queue<TreeNode> level = new LinkedList<>();
        level.add(root);
        while (level.size() != 0) {
            int levelNum = level.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                if (level.peek().left != null) level.add(level.peek().left); //O(1) for LinkedList
                if (level.peek().right != null) level.add(level.peek().right);
                subList.add(level.poll().val);
            }
            res.add(subList); //O(1) for LinkedList
        }
        return res;
    }
}

//Less concise version
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        
        Queue<TreeNode> level = new LinkedList<>();
        level.add(root);
        int size = 0;
        while (level.size() != 0) {
            size = level.size();
            List<Integer> temp = new LinkedList<>();
            while (size != 0) {
                TreeNode cur = level.poll();
                temp.add(cur.val);
                size--;
                if (cur.left != null) level.add(cur.left);
                if (cur.right != null) level.add(cur.right);
            }
            res.add(temp);
        }
        return res;
    }
}

//DFS, Worst running time = O(N^2)
//Since ArrayList.add() takes O(N) time each, and if you change res to LinkedList, LinkedList.get(height)
//would take O(N) time each.
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }
    
    private void helper(TreeNode root, List<List<Integer>> res, int height) {
        if (root == null) return;
        if (res.size() <= height) res.add(new LinkedList<>()); //new LinkedList<Integer>()
        
        res.get(height).add(root.val);
        helper(root.left, res, height + 1);
        helper(root.right, res, height + 1);
    }
}