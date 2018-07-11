/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Recursive solution
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
    }
    
    private TreeNode helper(int postRoot, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (postRoot < 0 || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(postorder[postRoot]);
        int inRoot = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inRoot = i;
                break;
            }
        }
        int postRightRoot = postRoot - 1;
        //index(left subtree) = index(right subtree) - length(right subtree)
        int postLeftRoot = postRoot - 1 - (inEnd - inRoot);
        root.left = helper(postLeftRoot, inStart, inRoot - 1, inorder, postorder);
        root.right = helper(postRightRoot, inRoot + 1, inEnd, inorder, postorder);
        return root;
    }
}

//Iterative solution
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) return null;
        
        Stack<TreeNode> stack = new Stack<>();
        int post = postorder.length - 1;
        int in = postorder.length - 1;
        TreeNode root = new TreeNode(postorder[post]);
        stack.push(root);
        post--;
        
        TreeNode parent = null;
        while (post >= 0) {
            while (!stack.empty() && stack.peek().val == inorder[in]) {
                parent = stack.pop();
                in--;
            }
            
            TreeNode cur = new TreeNode(postorder[post]);
            if (parent != null) {
                parent.left = cur;
            } else if (!stack.empty()) {
                stack.peek().right = cur;
            }
            stack.push(cur);
            post--;
            parent = null;
        }
        return root;
    }
}

/***
This is my iterative solution. Think about “Constructing Binary Tree from inorder and preorder array”,
the idea is quite similar. Instead of scanning the preorder array from beginning to end and using 
inorder array as a kind of mark, in this question, the key point is to scanning the postorder array 
from end to beginning and also use inorder array from end to beginning as a mark because the logic is 
more clear in this way. 

The core idea is: Starting from the last element of the postorder and inorder 
array, we put elements from postorder array to a stack and each one is the right child of the last one 
until an element in postorder array is equal to the element on the inorder array. Then, we pop as many
as elements we can from the stack and decrease the mark in inorder array until the peek() element is 
not equal to the mark value or the stack is empty. Then, the new element that we are gonna scan from 
postorder array is the left child of the last element we have popped out from the stack.
*/