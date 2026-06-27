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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        //The trick is that we have to call the recursion in the order of preorder traversal, then we don't need to bother about the preoder limits

        //I need to create map using inorder 
        //Inorder will just be used to split the tree into two

        Map<Integer,Integer> inorderMap=new HashMap<>();

        for(int i=0;i<inorder.length;i++){
            inorderMap.put(inorder[i],i);
        }

        // //Using streams
        // IntStream.range(0,inorder.length)
        //     .boxed()
        //     .collect(Collectors.toMap(k->inorder[k],
        //                               k->k,
        //                               (k1,k2)->k1));

        //I have my map and I need to make a right call
        //For each iteration I need to track the preorder root for the current iteration
        int []preIndex=new int[1];
        preIndex[0]=0;

        // return buildTreeHelper(inorderMap, 0, inorder.length, preorder, preIndex);
        //Lower and Upper bound are both inclusive
        return buildTreeHelper(inorderMap, 0, inorder.length-1, preorder, preIndex);

    }

    private TreeNode buildTreeHelper(Map<Integer,Integer> inorderMap, int inS, int inE,int[] preorder, int []preIndex){

        //What is the main function of this helper
        // I get the current root using preIndex array 
        //If invalid inorder index then return null
        //Find the index of the root in inorder and split the inorder into two , first build for the first half and then the 2nd half

        // if(inS<inE){
        if(inS>inE){    
            return null;
        }

        //Root for the current iteration
        int crRoot=preorder[preIndex[0]++];

        //Build the current tree with the root 
        TreeNode currentNode =new TreeNode(crRoot);

        int rootIndex=inorderMap.get(crRoot);

        //Get the left tree
        // currentNode.left=buildTreeHelper(inorderMap, inS, rootIndex-1, preorder, preIndex, currentNode);

        //From all the child calls I'll get a new child tree and I'm not modifying the current tree
        currentNode.left=buildTreeHelper(inorderMap, inS, rootIndex-1, preorder, preIndex);
        currentNode.right=buildTreeHelper(inorderMap, rootIndex+1, inE, preorder, preIndex);

        return currentNode;


    }
}



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
// class Solution {
//     public TreeNode buildTree(int[] preorder, int[] inorder) {
//        if(preorder.length==0){
//             return null;
//        }

//        int n=inorder.length-1;

//     //    Arrays.stream(inorder).collect(Collectors.toMap()); Not right way to convert array to hashmap

//     //Convert array to hashmap
//     //Traditional approach
//     Map<Integer,Integer> inorderMap=new HashMap<>();
//     for(int i=0;i<inorder.length;i++){
//         inorderMap.put(inorder[i],i);
//     }

//     //Streams approach
//     // inorderMap= IntStream.range(0,inorder.length)
//     //             .boxed()//Need to use this for making the map
//     //             .collect(Collectors.toMap(a-> inorder[a],
//     //                                       a->a,
//     //                                       (existingIndex,newIndex)->existingIndex
//     //                                     ));
    


//        return buildHelper(inorderMap,0,n,preorder,0,n);
        
//     }

//     private TreeNode buildHelper(Map<Integer,Integer> inorderMap,int iLl,int iRl,int[] preorder,int pLl,int pRl ){

//         if(iLl>iRl){
//             return null;
//         }

//         int rootVal=preorder[pLl];
//         TreeNode node=new TreeNode(rootVal);
        
//         //Left of the root in inorder
//         //iL1 to inOrderMap[rootVal]-1
//         //pL1+1 to pll+lenghth of inorder 
//         node.left=buildHelper(inorderMap,iLl,inorderMap.get(rootVal)-1,preorder,pLl+1,pLl+inorderMap.get(rootVal)-iLl);
//         //Right of the root in inorder
//         //inOrderMap[rootVal]+1 to iRl
//         //pL1+length of inorder +1 to pR1
//         node.right=buildHelper(inorderMap,inorderMap.get(rootVal)+1,iRl,preorder,pLl+inorderMap.get(rootVal)-iLl+1,pRl);

//         return node;

//     }
// }
