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
       if(preorder.length==0){
        return null;
       }

       int n=inorder.length-1;

    //    Arrays.stream(inorder).collect(Collectors.toMap()); Not right way to convert array to hashmap

    //Convert array to hashmap
    //Traditional approach
    Map<Integer,Integer> inorderMap=new HashMap<>();
    // for(int i=0;i<inorder.length;i++){
    //     inorderMap.put(inorder[i],i);
    // }

    //Streams approach
    inorderMap= IntStream.range(0,inorder.length)
                .boxed()//Need to use this for making the map
                .collect(Collectors.toMap(a-> inorder[a],
                                          a->a,
                                          (existingIndex,newIndex)->existingIndex
                                        ));
    


       return buildHelper(inorderMap,0,n,preorder,0,n);
        
    }

    private TreeNode buildHelper(Map<Integer,Integer> inorderMap,int iLl,int iRl,int[] preorder,int pLl,int pRl ){

        if(iLl>iRl){
            return null;
        }

        int rootVal=preorder[pLl];
        TreeNode node=new TreeNode(rootVal);
        
        //Left of the root in inorder
        //iL1 to inOrderMap[rootVal]-1
        //pL1+1 to pll+lenghth of inorder 
        node.left=buildHelper(inorderMap,iLl,inorderMap.get(rootVal)-1,preorder,pLl+1,pLl+inorderMap.get(rootVal)-iLl);
        //Right of the root in inorder
        //inOrderMap[rootVal]+1 to iRl
        //pL1+length of inorder +1 to pR1
        node.right=buildHelper(inorderMap,inorderMap.get(rootVal)+1,iRl,preorder,pLl+inorderMap.get(rootVal)-iLl+1,pRl);

        return node;

    }
}
