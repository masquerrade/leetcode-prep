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


//Gemini approach
// package com.google.interview.tree;

// import java.util.ArrayDeque;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;
// import java.util.Objects;
// import java.util.Queue;

// /**
//  * Standard LeetCode TreeNode definition provided by the platform.
//  */
// final class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode() {}

//     TreeNode(int val) {
//         this.val = val;
//     }

//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

// /**
//  * Production-grade engine to compute projections of binary trees.
//  */
// public final class BinaryTreeRightSideView {

//     /**
//      * Extracts the right side view of a binary tree using an iterative Level-Order
//      * (BFS) traversal with defensive level snapshots.
//      *
//      * Invariant: At the end of processing each queue level, the final element
//      * processed is the rightmost element at that depth.
//      *
//      * @param root the root node of the binary tree
//      * @return an unmodifiable list containing values visible from the right side
//      */
//     public List<Integer> rightSideView(TreeNode root) {
//         if (root == null) {
//             return Collections.emptyList();
//         }

//         List<Integer> visibleNodes = new ArrayList<>();
//         // ArrayDeque offers better cache locality and performance than LinkedList
//         Queue<TreeNode> queue = new ArrayDeque<>();
//         queue.offer(root);

//         while (!queue.isEmpty()) {
//             // Snapshot queue size: represents exact number of nodes at current depth
//             int levelSize = queue.size();

//             for (int i = 0; i < levelSize; i++) {
//                 TreeNode currentNode = queue.poll();
                
//                 // Defensive assertion against illegal internal state corruption
//                 Objects.requireNonNull(currentNode, "Queue element must never be null.");

//                 // If this is the terminal node of the current level snapshot, record it
//                 if (i == levelSize - 1) {
//                     visibleNodes.add(currentNode.val);
//                 }

//                 // Push children left-to-right to preserve horizontal ordering
//                 if (currentNode.left != null) {
//                     queue.offer(currentNode.left);
//                 }
//                 if (currentNode.right != null) {
//                     queue.offer(currentNode.right);
//                 }
//             }
//         }

//         return Collections.unmodifiableList(visibleNodes);
//     }

//     /**
//      * Alternative DFS implementation (Reverse Pre-Order: Root -> Right -> Left).

//     	 * Optimal for deep trees where memory constraints prioritize stack depth O(H)

//      	* over level width O(W).
//      */
//     public List<Integer> rightSideViewDfs(TreeNode root) {
//         if (root == null) {
//             return Collections.emptyList();
//         }
//         List<Integer> visibleNodes = new ArrayList<>();
//         dfsReversePreOrder(root, 0, visibleNodes);
//         return Collections.unmodifiableList(visibleNodes);
//     }

//     private void dfsReversePreOrder(TreeNode node, int currentDepth, List<Integer> collector) {
//         if (node == null) {
//             return;
//         }

//         // First node reached at this depth is guaranteed to be the rightmost node
//         if (currentDepth == collector.size()) {
//             collector.add(node.val);
//         }

//         // Prioritize right branch to establish visibility before visiting left
//         dfsReversePreOrder(node.right, currentDepth + 1, collector);
//         dfsReversePreOrder(node.left, currentDepth + 1, collector);
//     }
// }
