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

 //Recursive approach
class Solution {
    public int kthSmallest(TreeNode root, int k) {

        //I need back the kth smallest element
        //The helper function in each level needs k, current node and it needs to return the kth node
        //So here we'll use state array so that sibling and parent call are aware of the state

        int[] state=new int[]{k,-1};

        findKthNode(root,state);

        return state[1];

    }

    private void findKthNode(TreeNode node,int [] state){

        //If node=null or k=0 return
        if(node==null || state[0]==0){
            return;
        }

        //First I  need to reach to the leftmost node
        findKthNode(node.left,state);

        if(state[0]==0){
            return ;
        }

        //Do the required work with the current node and trust the recursion to do the remaining work
        state[0]--;
        if(state[0]==0){
            state[1]=node.val;
            return;
        }

        //Now I need to look at the right side of the current node
        findKthNode(node.right,state);

    }
}


// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode() {}
//  *     TreeNode(int val) { this.val = val; }
//  *     TreeNode(int val, TreeNode left, TreeNode right) {
//  *         this.val = val;
//  *         this.left = left;
//  *         this.right = right;
//  *     }
//  * }
//  */

// //Modified node approach
// public class TreeNode{
//     int val;
//     TreeNode left;
//     TreeNode right;
//     int leftSubtreeSize;
//     TreeNode(){}

//     TreeNode(int val){this.val=val;}

//     TreeNode(int val,TreeNode left, TreeNode right){
//         this.val=val;
//         this.right=right;
//         this.left=left;
//     }
// }

// class Solution {
//     public int kthSmallest(TreeNode root, int k) {

//         //Here I don't need to manage the insertion
//         //Here I don't need to go to the left most node to traverse the whole tree
//         //I cannot change the next pointer of the root node
//         TreeNode current=root;

//         //I need to keep traversing till I reach the null
//         while(current!=null){
//             int leftSize=current.leftSubtreeSize;
//             //If k== leftSize+1 return  the current element 
//             if(k==leftSize+1){
//                 return current.val;
//             }

//             //If k is less than the leftSize then move to the left subtree
//             if(k<=leftSize){
//                 current=current.left;
//             }

//             //If k is greater than the leftSize+1 ,I need to move to the right 

//             if(k>leftSize+1){
//                 current=current.right;
//                 k=k-(leftSize+1);
//             }

//         }

//         return -1;
        
//     }
// }

// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode() {}
//  *     TreeNode(int val) { this.val = val; }
//  *     TreeNode(int val, TreeNode left, TreeNode right) {
//  *         this.val = val;
//  *         this.left = left;
//  *         this.right = right;
//  *     }
//  * }
//  */

// //Stack approach
// class Solution {
//     public int kthSmallest(TreeNode root, int k) {

//         //Inorder traversal
//         //Using stack reach the left nost node 
//         //ArrayDeque implements Deque interface
//         Deque<TreeNode> stack=new ArrayDeque<>();

//         TreeNode current=root;

//         while (!stack.isEmpty() || current!=null){
//             //Take whatever node current points to push it to stack and explore it's left side
//             while(current!=null){
//                 stack.push(current);
//                 current=current.left;
//             }
        
//             //After reaching the leftmost node only the depth first search will start
//             current=stack.pop();
//             if(0==--k){
//                 return current.val;
//             }

//             //From root I need to just push the right node
//             //This is actually not needed as the current node is automatically being pushhed in the start
//             // if(current.right!=null){
//             //     stack.push(current.right);
//             // }
//             current=current.right;
            

//         }

//         return -1;
        
//     }
// }
