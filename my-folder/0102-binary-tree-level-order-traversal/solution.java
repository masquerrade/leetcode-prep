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




// Gemini solution

// package com.google.interview.tree;

// import java.util.ArrayDeque;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;
// import java.util.Queue;

// /**
//  * Definition for a binary tree node.
//  */
// class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode() {}
//     TreeNode(int val) { this.val = val; }
//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

// public final class LevelOrderTraversal {

//     /**
//      * Traverses a binary tree level by level from left to right.
//      *
//      * @param root the root node of the binary tree
//      * @return a list of levels, each containing the values of nodes at that depth
//      */
//     public List<List<Integer>> levelOrder(TreeNode root) {
//         // Defensive boundary guard: an empty tree yields an empty list
//         if (root == null) {
//             return Collections.emptyList();
//         }

//         List<List<Integer>> levels = new ArrayList<>();
        
//         // ArrayDeque is chosen over LinkedList for cache locality and zero node-wrapper overhead
//         Queue<TreeNode> queue = new ArrayDeque<>();
//         queue.offer(root);

//         while (!queue.isEmpty()) {
//             // Snapshot current level's width to isolate generation boundaries
//             int levelSize = queue.size();
            
//             // Pre-allocate exact capacity to prevent dynamic array resizes and reallocations
//             List<Integer> currentLevel = new ArrayList<>(levelSize);

//             for (int i = 0; i < levelSize; i++) {
//                 TreeNode currentNode = queue.poll();
                
//                 // currentNode is guaranteed non-null because only non-null references are enqueued
//                 currentLevel.add(currentNode.val);

//                 if (currentNode.left != null) {
//                     queue.offer(currentNode.left);
//                 }
//                 if (currentNode.right != null) {
//                     queue.offer(currentNode.right);
//                 }
//             }

//             levels.add(currentLevel);
//         }

//         return levels;
//     }
// }
