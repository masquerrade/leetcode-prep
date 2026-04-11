/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {

        //Inorder traversal
        //Using stack reach the left nost node 
        //ArrayDeque implements Deque interface
        Deque<TreeNode> stack=new ArrayDeque<>();

        TreeNode current=root;

        while (!stack.isEmpty() || current!=null){
            //Take whatever node current points to push it to stack and explore it's left side
            while(current!=null){
                stack.push(current);
                current=current.left;
            }
        
            //After reaching the leftmost node only the depth first search will start
            current=stack.pop();
            if(0==--k){
                return current.val;
            }

            //From root I need to just push the right node
            //This is actually not needed as the current node is automatically being pushhed in the start
            // if(current.right!=null){
            //     stack.push(current.right);
            // }
            current=current.right;
            

        }

        return -1;
        
    }
}
