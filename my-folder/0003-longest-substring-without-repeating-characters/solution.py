class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        i=0
        j=0
        ml=0

        visited={ch:False for ch in s}

        for j in range(len(s)):
            while visited[s[j]]==True:
                #print("Inside sliding")
                visited[s[i]]=False
                i=i+1
                #Do 
            ml=max(j-i+1,ml)
            #print(ml)
            visited[s[j]]=True

        return ml

            


        
