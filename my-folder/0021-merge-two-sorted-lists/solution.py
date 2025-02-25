# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:

    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        #Check if any one of the list is none->Return not none
        #Start iterating while checking which one is smaller and go on forming the linked list
        if(list1 is None):
            return list2
        if(list2 is None):
            return list1
        
        h1=list1
        h2=list2


        finalHead=None
        finalTail=None
        
        while(h1 is not None and h2 is not None):
            if(h1.val<=h2.val):
                if(finalHead is None):
                    finalHead=h1
                    finalTail=h1
                    h1=h1.next
                else :
                    #move final tail to h1
                    #add h1 to the final tail
                    #move final tail to the last node
                    #move h1 one step
                    finalTail.next=h1
                    finalTail=h1
                    h1=h1.next

            else:
                if(finalHead is None):
                    finalHead=h2
                    finalTail=h2
                    h2=h2.next
                else :
                    #move final tail to h1
                    #add h1 to the final tail
                    #move final tail to the last node
                    #move h1 one step
                    finalTail.next=h2
                    finalTail=h2
                    h2=h2.next
        if(h1 is None):
            finalTail.next=h2
        if(h2 is None):
            finalTail.next=h1

        return finalHead
                    

        
