/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Recursive Solution
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(0, 0, inorder.length - 1, preorder, inorder);
    }
    
    //preRoot = root index in preorder, inStart = starting index in inorder, inEnd = end idex in inorder
    private TreeNode buildTree(int preRoot, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preRoot >= preorder.length || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(preorder[preRoot]);
        int inRoot = 0; //inRoot = root index in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inRoot = i;
                break;
            }
        }
        root.left = buildTree(preRoot + 1, inStart, inRoot - 1, preorder, inorder);
        root.right = buildTree(preRoot + 1 + inRoot - inStart, inRoot + 1, inEnd, preorder, inorder);
        return root;
    }
}

//Iterative Solution
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        
        Stack<TreeNode> stack = new Stack<>(); //Stack for DFS, Queue for BFS
        int pre = 0; //the index we're currently at in preorder[]
        int in = 0; //the index we're currently at in inorder[]
        TreeNode root = new TreeNode(preorder[pre]);
        stack.push(root);
        pre++;
        
        TreeNode parent = null; //null parent-->add to left, has parent-->add to right
        while (pre < preorder.length) {
            //if we reach the end of the left branch (the leftmost node), we pop out the stack
            //and assign the parent node of current left node to TreeNode parent
            while (!stack.empty() && stack.peek().val == inorder[in]) {
                parent = stack.pop();
                in++;
            }
            //Therefore, after this while loop, if parent is not null, we know we need to build
            //right branch; if parent is null, we know we are still on the step of building the
            //left branch, so we need to build left branch;
            //-->TreeNode parent is a flag of whether we build left branch(when it is null)
            //or right branch(when it is not null).
            
            TreeNode cur = new TreeNode(preorder[pre]);
            
            if (parent != null) {
                parent.right = cur;
            } else if (!stack.empty()) {
                parent = stack.peek();
                parent.left = cur;
            }
            stack.push(cur); //push the currently constructed node to stack
            pre++; //increment pre index after constructing one more node
            parent = null; //reset the flag
        }
        return root;
    }
}

/***
Iterative method explanation:
Scanning the preorder array from beginning to end and using inorder array as a mark of the leftmost node.
The mark signifies where we should stop constructing the left branch (since we've already reached the 
leftmost node) and should start constructing the right branch.

The core idea is:
Starting from the fiest element of the preorder and inorder array, we put elements from preorder 
array to a stack and each one is the left child of the last one until an element in preorder array 
is equal to the element on the inorder array. Then, we pop as many as elements we can from the stack 
and increase the mark(==index) in inorder array until the peek() element is not equal to the mark value 
or the stack is empty. Then, the new element that we are gonna scan from preorder array is the right 
child of the last element we have popped out from the stack.
*/