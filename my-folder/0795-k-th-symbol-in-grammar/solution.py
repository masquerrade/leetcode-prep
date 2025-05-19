class Solution:
    def kthGrammar(self, n: int, k: int) -> int:
        return KthSymbol(n,k)        


def KthSymbol(j,k):
    if (j==1 and k==1):
        return 0
    if k%2==0:
        prevNo=KthSymbol(j-1,k//2)
    else:
        prevNo=KthSymbol(j-1,(k+1)//2)

    if(prevNo==0):
        if k%2==0:
            return 1
        else:
            return 0
        
    if(prevNo==1):
        if k%2==0:
            return 0
        else:
            return 1
