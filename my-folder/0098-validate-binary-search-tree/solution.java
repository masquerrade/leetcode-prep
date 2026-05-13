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
 //Passing the range for recursion
class Solution {
    public boolean isValidBST(TreeNode root) {

        //Call the validate bst with the root and its range
        //Root can have infinite range
        return validateBst(root,null,null);
                
    }

    private boolean validateBst(TreeNode root,Integer lLimit,Integer uLimit){
        
        if(root == null){
            return true;
        }

        //Check if the current node falls within the specified limit
        //LL=null & val<ULimit
        //UL=null & val>LLimit
        //LL&UL=null 
        //I'll split initial check in two conditions
        //Check for lLimit
        //I need to check for equality also . Even if the value is equal to the limits the condition fails
        if(lLimit!=null && root.val<=lLimit){
            return false;
        }

        if(uLimit!=null && root.val>=uLimit){
            return false;
        }

        //Check if left and right subtree is valid 
        //This can be replaced by single if condition only
        if(!validateBst(root.left,lLimit,root.val) || !validateBst(root.right,root.val,uLimit)){
            return false;
        }

        return true;

    }
}
