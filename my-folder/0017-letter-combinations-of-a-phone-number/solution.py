def Combinations(digits):  
    numberDict={"2":"abc","3":"def","4":"ghi","5":"jkl","6":"mno","7":"pqrs","8":"tuv","9":"wxyz"}
    lst=[]
    for d in digits:
        lst.append(numberDict[d])
    if(digits==''):
        return []
    return seqCalc(lst)


def seqCalc(lst):
    if(len(lst)==0):
        return ['']
    
    smallSeq=seqCalc(lst[1:])
    finalSeq=[]
    for s in lst[0]:
        for seq in smallSeq:
            finalSeq.append(s+seq)

    return finalSeq
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        return Combinations(digits)

        
