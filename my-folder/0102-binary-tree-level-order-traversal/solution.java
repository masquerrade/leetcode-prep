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
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result=new ArrayList<>();

        if(root == null){
            return result;
        }

        //BFS
        Queue<TreeNode> bfs= new ArrayDeque<>();

        //I missed to offer the first root to bfs
        bfs.offer(root);
        /**
        *       bfs

         */
        while(!bfs.isEmpty()){
            int levelSize=bfs.size();
            List<Integer> currentLevel= new ArrayList<>(levelSize);

            for(int i=0; i<levelSize; i++){

                TreeNode currentNode= bfs.poll();
                currentLevel.add(currentNode.val);

                TreeNode left= currentNode.left;
                TreeNode right = currentNode.right;

                if(left!= null){
                    bfs.offer(left);
                }

                if(right != null){
                    bfs.offer(right);
                }

            }

            result.add(currentLevel);

        }

        return result;
        
    }
}
