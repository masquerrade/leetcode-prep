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
    public int maxDepth(TreeNode root) {

        // //Recursive version
        // //We can't pass the depth as state recursively
        // if(root == null){
        //     return 0;
        // }

        // //Left depth 
        // int left=maxDepth(root.left);
        // int right=maxDepth(root.right);

        // return Math.max(left,right)+1 ;

        //Iterative solution
        return maxDepthBfs(root);
        
    }


    //Iterative solution
    private int maxDepthBfs(TreeNode root){

        if(root == null){
            return 0;
        }

        //Track each level
        Queue<TreeNode> bfs= new ArrayDeque<>();

        bfs.offer(root);

        int maxDepth=0;

        //Just count the total number of levels
        while(!bfs.isEmpty()){
            int levelSize=bfs.size();
            maxDepth++;

            for(int i=0; i<levelSize; i++){
                //Add all the adjcent nodes to the queue
                TreeNode currentNode=bfs.poll();

                TreeNode left = currentNode.left;
                TreeNode right = currentNode.right;

                if(left != null){
                    bfs.offer(left);
                }

                if(right != null){
                    bfs.offer(right);
                }

            }

        }

        return maxDepth;


    }
}
