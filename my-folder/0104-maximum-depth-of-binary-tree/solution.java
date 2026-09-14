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

// //Gemini solution
// package com.interview.trees;

// import java.util.ArrayDeque;
// import java.util.Objects;
// import java.util.Queue;

// public final class BinaryTreeDepthSolver {

//     public static final class TreeNode {
//         public int val;
//         public TreeNode left;
//         public TreeNode right;

//         public TreeNode(int val) {
//             this.val = val;
//         }

//         public TreeNode(int val, TreeNode left, TreeNode right) {
//             this.val = val;
//             this.left = left;
//             this.right = right;
//         }
//     }

//     /**
//      * Recursive Post-Order (DFS):
//      * Idiomatic, optimal for balanced or reasonably bounded trees (N < 5,000).
//      */
//     public int maxDepthRecursive(final TreeNode root) {
//         if (root == null) {
//             return 0;
//         }

//         final int leftSubtreeDepth = maxDepthRecursive(root.left);
//         final int rightSubtreeDepth = maxDepthRecursive(root.right);

//         // Math.max guarantees no integer overflow because tree node count <= Integer.MAX_VALUE - 1
//         return 1 + Math.max(leftSubtreeDepth, rightSubtreeDepth);
//     }

//     /**
//      * Iterative Level-Order (BFS):
//      * Stack-safe alternative. Guarantees resilience against StackOverflowError
//      * on highly degenerate/skewed trees by managing allocations on the JVM heap.
//      */
//     public int maxDepthIterative(final TreeNode root) {
//         if (root == null) {
//             return 0;
//         }

//         // ArrayDeque is faster than LinkedList due to cache locality and zero node-object overhead
//         final Queue<TreeNode> levelQueue = new ArrayDeque<>();
//         levelQueue.offer(root);

//         int treeDepth = 0;

//         while (!levelQueue.isEmpty()) {
//             final int currentLevelSize = levelQueue.size();

//             // Process all nodes strictly residing at the current depth tier
//             for (int i = 0; i < currentLevelSize; i++) {
//                 final TreeNode currentNode = levelQueue.poll();
                
//                 // Defensive check: poll should never return null due to size snapshot
//                 if (currentNode != null) {
//                     if (currentNode.left != null) {
//                         levelQueue.offer(currentNode.left);
//                     }
//                     if (currentNode.right != null) {
//                         levelQueue.offer(currentNode.right);
//                     }
//                 }
//             }

//             // Increment depth only after the entire level is fully exhausted
//             treeDepth++;
//         }

//         return treeDepth;
//     }
// }



