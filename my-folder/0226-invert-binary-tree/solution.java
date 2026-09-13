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

//Gemini solution

// /**
//  * Definition for a binary tree node.
//  */
// final class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode(int val) {
//         this.val = val;
//     }

//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

// public final class TreeInverter {

//     private TreeInverter() {
//         // Prevent instantiation of utility class
//     }

//     /**
//      * Inverts a binary tree in-place using Depth-First Search (DFS).
//      * <p>
//      * Optimal for balanced or shallow trees. Vulnerable to StackOverflowError
//      * on pathologically skewed trees (depth &gt; ~8000).
//      *
//      * @param root the root of the binary tree
//      * @return the root of the inverted tree, or {@code null} if input is null
//      */
//     public static TreeNode invertTreeRecursive(TreeNode root) {
//         if (root == null) {
//             return null;
//         }

//         // Divide: invert both subtrees recursively
//         TreeNode invertedLeft = invertTreeRecursive(root.left);
//         TreeNode invertedRight = invertTreeRecursive(root.right);

//         // Conquer: swap left and right pointers
//         root.left = invertedRight;
//         root.right = invertedLeft;

//         return root;
//     }

//     /**
//      * Inverts a binary tree in-place using Breadth-First Search (BFS).
//      * <p>
//      * Production-safe implementation: converts recursion stack allocation to heap
//      * allocation, eliminating the risk of thread stack exhaustion.
//      *
//      * @param root the root of the binary tree
//      * @return the root of the inverted tree, or {@code null} if input is null
//      */
//     public static TreeNode invertTreeIterative(TreeNode root) {
//         if (root == null) {
//             return null;
//         }

//         // ArrayDeque is faster and uses less memory than LinkedList as a queue
//         Queue<TreeNode> queue = new ArrayDeque<>();
//         queue.offer(root);

//         while (!queue.isEmpty()) {
//             TreeNode current = queue.poll();

//             // Mutate pointers: swap current node&apos;s immediate children
//             TreeNode temp = current.left;
//             current.left = current.right;
//             current.right = temp;

//             // Enqueue non-null children for subsequent level-order processing
//             if (current.left != null) {
//                 queue.offer(current.left);
//             }
//             if (current.right != null) {
//                 queue.offer(current.right);
//             }
//         }

//         return root;
//     }
// }
