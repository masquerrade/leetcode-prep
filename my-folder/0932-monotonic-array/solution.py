class Solution:
    def isMonotonic(self, nums: List[int]) -> bool:
        return isMonotonicHelp(nums)


def isMonotonicHelp(array):
    #if array[0]<array[end]
    #if array[0]==array[end]
    #if array[0]>array[end]
    n=len(array)
    end=len(array)-1
    if array[0]<array[end]:
        for i in range(n-1):
            if array[i]>array[i+1]:
                return False
            
    if array[0]==array[end]:
        for i in range(n):
            if array[i]!=array[0]:
                return False
            
    if array[0]>array[end]:
        for i in range(n-1):
            if array[i]<array[i+1]:
                return False
            
    return True
