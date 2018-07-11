/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList();
        return generateTrees(1, n);
    }
    
    //s is starting point, e is ending point, left = (s..root-1), right = (root+1..e)
    public List<TreeNode> generateTrees(int s, int e) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        
        if (s > e) {
            list.add(null);
            return list;
        }

        /* Add this will improve time efficiency
        if (s == e) {
            list.add(new TreeNode(s));
            return list;
        }
        */
        
        List<TreeNode> leftree, rightree;
        for (int i = s; i <= e; i++) { //i is root
            leftree = generateTrees(s, i-1);
            rightree = generateTrees(i+1, e);
            for (TreeNode left : leftree) {
                for (TreeNode right : rightree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }
}