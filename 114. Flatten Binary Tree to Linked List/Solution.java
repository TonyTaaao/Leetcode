//Iterative solution
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return; //don't forget
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        //dfs: add to res in the order of preorder
        while (!stack.empty()) {
            TreeNode cur = stack.pop();
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
            if (!stack.empty()) cur.right = stack.peek();
            cur.left = null; //don't forget this!!!
        }
    }
}
//Time = O(n), Space = O(logn)

//Recursive solution
class Solution {
    TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
//Time = O(n), Space = O(1)