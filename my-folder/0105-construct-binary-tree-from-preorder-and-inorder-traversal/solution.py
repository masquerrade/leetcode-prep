# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
def constTreeInPre(inOrd,preOrd):
    
    if(len(preOrd)==0 or len(inOrd)==0):
        return None
    
    root=preOrd[0]
    currRoot=TreeNode(root)
    
    rootIndex=inOrd.index(root)
    leftInOrd=inOrd[:rootIndex]
    #print(leftInOrd)
    rightInOrd=inOrd[rootIndex+1:]

    leftPreOrd=preOrd[1:1+len(leftInOrd)]
    #print(leftPreOrd)
    rightPreOrd=preOrd[1+len(leftPreOrd):]

    currRoot.left=constTreeInPre(leftInOrd,leftPreOrd)
    currRoot.right=constTreeInPre(rightInOrd,rightPreOrd)

    return currRoot


def constInPreIndex(inOrd,preOrd,inS,inE,preS,preE):

    if(inS>inE or preS>preE or inE<0):
        return None
    
    root=preOrd[preS]
    currRoot=TreeNode(root)

    for i in range(inS,inE+1):
        if root==inOrd[i]:
            rootInd=i
            break
    #rootInd=0
    #print(rootInd)

    inSLeft=inS
    #inSLeft=0
    inELeft=rootInd-1
    #inELeft=-1   
    inSRight=rootInd+1
    #inSRight=1

    inERight=inE
    #inERight=0


    preSLeft=preS+1
    #preSLeft=1
    preELeft=preSLeft+(inELeft-inSLeft)
    #preELeft=0
    preSRight=preELeft+1
    #preSRight=1
    preERight=preSRight+(inERight-inSRight)

    #preELeft=1


    currRoot.left=constInPreIndex(inOrd,preOrd,inSLeft,inELeft,preSLeft,preELeft)
    currRoot.right=constInPreIndex(inOrd,preOrd,inSRight,inERight,preSRight,preERight)

    return currRoot



class Solution:
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        return constInPreIndex(inorder,preorder,0,len(preorder)-1,0,len(inorder)-1)

        
