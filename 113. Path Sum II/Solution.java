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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(root, sum, res, temp);
        return res;
    }
    
    private void helper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> temp) {
        if (root == null) return;
        temp.add(root.val);
        if (root.left == null && root.right == null) { //reach leaf node, end of recursion
            if (root.val == sum) res.add(new ArrayList(temp)); //if not, do nothing
        } else { //not reaching leaf node -->continue recursive call
            helper(root.left, sum - root.val, res, temp);
            helper(root.right, sum - root.val, res, temp);
        }
        //If current node is leaf node, remove it from temp
        //If current node is not leaf node, remove it after dfs search to all its children (thi is done when 
        //we call helper() function to its left and right children)
        temp.remove(temp.size() - 1); //remove item at index in parentheses
    }

    //Note in line 22, we add a new copy of current temp to res, instead of temp
    //-->use res.add(new ArrayList(temp)); Not res.add(temp);
    //because in back-tracking, temp always changes (temp is a reference, meaning it will 
    //change throughout the recursion), so the result which refers to temp will also change.
    //LinkedList currentResult = new LinkedList(temp);
    //result.add(currentResult);
    //so that the result refers to the currentResult of temp at that moment, which will not 
    //change in recursion.


}