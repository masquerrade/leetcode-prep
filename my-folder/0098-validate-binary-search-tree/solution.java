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
 //Iterative inorder traversal gives the sorted list in binary search tree
class Solution {
    public boolean isValidBST(TreeNode root) {

        //Why I need a stack ->Since it is a DFS  I need to reach the left most depth of the tree to find the smallest node

        //This check is not needed as it will be handled in the while loop
        // if(root == null){
        //     return true;
        // }

        // Not valid definition Stack<Treenode> dfs=new ArrayDeque<>();
        Deque<TreeNode> inorder=new ArrayDeque<>();
        TreeNode current = root;
        TreeNode prev=null;
        
        //No need to push here as we will push inside the while loop
        // inorder.push(root);

        //Traverse the full tree while filling the stack till it becomes empty
        // while(!inorder.isEmpty() && current!=null/*To avoid first null*/)This will fail because inorder is empty in first iteration .So we process if there are nodes in inorder or current in not null
        while(!inorder.isEmpty() || current!=null/*To avoid first null*/){
            //This will effectively push right and root node to the stack
            //inorder.push(current); This need to be inside the if condition to avoid pushing null

            //This will not be the smallest node so no need to pop the parent immediately .I need to pop the leftmost node
            // TreeNode current =inorder.pop();

            //Reach the leftmost node for the dfs
            while(current!=null){
                inorder.push(current);
                //While traversing left do I need to add these element to the stack ->Yes because I need to process these nodes later
                //Here only I can check the condition and return accordingly
                //If the node is valid then only it is added to the stack
                //To break early
                //This is not needed as we have a global check
                // if(current.left!=null&&current.left.val>=current.val){
                //     return false;
                // }

                current=current.left;
            }

            //Now I need to keep track of the previous node and compare with the next smallest node
            current=inorder.pop();
            if(prev!=null&& prev.val>=current.val){
                return false;
            }
            prev=current;

            //This is not needed as we have a global check
            // if(current.right!=null){
            //     if(current.val>=current.right.val){
            //         return false;
            //     }
            //     //We can move this push to the beginning of the outer loop
            //     // inorder.push(current);
            // }

            //Even if right is null I need to move to right 
            //Making the current as null is the crucial part otherwise same left node will keep on getting inserted repeatedly
            current=current.right;

        }

        //If all the nodes has been visited then return true
        return true;        
        
    }
}


// // //Passing the range for recursion
// class Solution {
//     public boolean isValidBST(TreeNode root) {

//         //Call the validate bst with the root and its range
//         //Root can have infinite range
//         return validateBst(root,null,null);
                
//     }

//     private boolean validateBst(TreeNode root,Integer lLimit,Integer uLimit){
        
//         if(root == null){
//             return true;
//         }

//         //Check if the current node falls within the specified limit
//         //LL=null & val<ULimit
//         //UL=null & val>LLimit
//         //LL&UL=null 
//         //I'll split initial check in two conditions
//         //Check for lLimit
//         //I need to check for equality also . Even if the value is equal to the limits the condition fails
//         if(lLimit!=null && root.val<=lLimit){
//             return false;
//         }

//         if(uLimit!=null && root.val>=uLimit){
//             return false;
//         }

//         //Check if left and right subtree is valid 
//         //This can be replaced by single if condition only
//         if(!validateBst(root.left,lLimit,root.val) || !validateBst(root.right,root.val,uLimit)){
//             return false;
//         }

//         return true;

//     }
// }
