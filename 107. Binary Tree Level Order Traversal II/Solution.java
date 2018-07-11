/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Similar Problem: 102, 103, 107
//BFS, Time = O(N) if use LinkedList to store subList, 
//Time = O(N^2) if we use ArrayList due to O(N) time array resizing at line 31 each time inside the while loop.
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>(); //use LinkedList instead of ArrayList because in line 30,
        //LinkedList.add(0, E) takes O(1) time due to head pointer, while ArrayList.add(0, E) takes O(N) time due to array resizing
        if (root == null) return res;
        
        Queue<TreeNode> level = new LinkedList<>();
        level.add(root);
        int levelNum = 0;
        while (level.size() != 0) {
            levelNum = level.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = level.poll();
                subList.add(cur.val);
                if (cur.left != null) level.add(cur.left);
                if (cur.right != null) level.add(cur.right);
            }
            res.add(0, subList);
        }
        return res;
    }
}


//DFS, Time = O(N^2), because the list.get(index) operation at line 53 is O(N) for LinkedList,
//but if we use ArrayList, the list.add(index, E) operation at line 50 is O(N) for ArrayList
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> wrapList = new ArrayList<List<Integer>>();
        levelMaker(wrapList, root, 0);
        return wrapList;
    }

    public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
        if(root == null) return;
        if(level >= list.size()) {
            list.add(0, new LinkedList<Integer>()); //ArrayList.add(0, E) takes O(n) because of array resizing, 
            //LinkedList.add() take O(1) because of head pointer
        }
        list.get(list.size()-level-1).add(root.val); //ArrayList.get() takes O(1), LinkedList.get() takes O(n)
        levelMaker(list, root.left, level+1);
        levelMaker(list, root.right, level+1);
    }
}