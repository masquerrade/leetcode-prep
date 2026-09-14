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
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> rightList= new ArrayList<>();

        if(root == null){
            return rightList;
        }

        // Queue<TreeNode> bfs=new ArrayDeque<>();
        //If I want to use peek last  I need to use deque
        Deque<TreeNode> bfs=new ArrayDeque<>();


        bfs.offer(root);
        

        while(!bfs.isEmpty()){

            int curSize = bfs.size();            
            rightList.add(bfs.peekLast().val);
            for(int i=0 ; i<curSize; i++){
                //I need to poll in every iteration
                TreeNode current = bfs.poll();
                
                TreeNode left = current.left;
                TreeNode right = current.right;

                if(left != null){
                    bfs.offer(left);
                }

                if(right != null){
                    bfs.offer(right);
                    //If only left is there then what ? I need to take the last value of the queue
                    // rightVal= right.val;
                }
            
            }
            //For last iteration this will throw null pointer exception
            // rightList.add(bfs.peekLast().val);


        }

        return rightList;

        
    }
}
