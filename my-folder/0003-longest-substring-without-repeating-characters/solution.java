class Solution {
    public int lengthOfLongestSubstring(String s) {
        //Sliding window solution
        Set<Character> st=new HashSet<>();
        int start=0;
        int maxLen=0;
        for(int e=0;e<s.length();e++){
           
           //Removing all the elements till the duplicate element which was added before is removed from set
            while(st.contains(s.charAt(e))){
                st.remove(s.charAt(start));
                start++;
            }
            st.add(s.charAt(e));
            int currLen=e-start+1;
            maxLen=Math.max(currLen,maxLen);
            
        }

        return maxLen;
                
    }
}
