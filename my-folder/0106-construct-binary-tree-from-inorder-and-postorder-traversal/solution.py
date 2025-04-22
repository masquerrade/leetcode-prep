
def treeFromPostAndIn(inorder,postorder,ioS,ioE,poS,poE):
    #print(f"ios-{ioS},ioE-{ioE},poS-{poS},poE-{poE}")
    if(ioS>ioE or poS>poE):
        return None
    root_data=postorder[poE]
    rootNode=TreeNode(root_data)
    i=0
    for elm in inorder:
        if elm==root_data:
            iORootInd=i
            break
        i+=1 
    
    lIoSt=ioS
    lIoEn=iORootInd-1
    rIoSt=iORootInd+1
    rIoEn=ioE

    noELeft=lIoEn-lIoSt
    noERht=rIoEn-rIoSt

    lPoSt=poS
    lPoEn=poS+noELeft
    rPoSt=lPoEn+1
    rPoEn=rPoSt+noERht

    rootNode.left=treeFromPostAndIn(inorder, postorder, lIoSt, lIoEn, lPoSt, lPoEn)
    rootNode.right=treeFromPostAndIn(inorder, postorder, rIoSt,rIoEn,rPoSt,rPoEn)

    return rootNode


# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def buildTree(self, inorder: List[int], postorder: List[int]) -> Optional[TreeNode]:
        return treeFromPostAndIn(inorder,postorder,0,len(inorder)-1,0,len(postorder)-1)
        
