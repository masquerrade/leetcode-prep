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
    public TreeNode invertTree(TreeNode root) {

        // //Let's try how we can do this
        // if(root == null){
        //     return null;
        // }

        // //Find inverted left and right and invert the current  node
        // TreeNode invertedLeft = invertTree(root.left);
        // TreeNode invertedRight = invertTree(root.right);

        // TreeNode temp = root.left;
        // root.left = root.right;
        // root.right = temp;

        // return root;

        return invertTreeBfs(root);
        

    }

    //Invert tree bfs
    public TreeNode invertTreeBfs(TreeNode root) {

        //Initialze queue
        Queue<TreeNode> bfs= new ArrayDeque<>();

        //We can't add null value to the Queue
        if(root == null){
            return null;
        }
        
        bfs.offer(root);

        while( !bfs.isEmpty() ){
            //Take the current root 
            //Add it's children to the queue 
            //Reverse the children
            TreeNode currentNode = bfs.poll();

            //Reverse the children
            TreeNode temp =  currentNode.left;
            currentNode.left = currentNode.right;
            currentNode.right = temp;

            if(currentNode.left != null){
                bfs.offer(currentNode.left);
            }

            if(currentNode.right != null){
                bfs.offer(currentNode.right);
            }
        }

        return root;

    }

}
