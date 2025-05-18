class Solution:
    def sortedSquares(self, nums: List[int]) -> List[int]:
        return sortedSquaredOptimized(nums)


def sortedSquaredOptimized(array):
    #write code here.make sure to return desired array
    newArray=[0]*len(array)
    start=0
    end=len(array)-1
    for i in reversed(range(len(newArray))):
        startSQ=array[start]**2
        endSQ=array[end]**2
        if (startSQ>endSQ):
            newArray[i]=startSQ
            start+=1
        else:
            newArray[i]=endSQ
            end-=1
    return newArray

