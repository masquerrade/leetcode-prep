class Solution {
    public int lengthOfLongestSubstring(String s) {

        int maxLen=0;
        int l=0;
        Map<Character,Integer> mp=new HashMap<>();

        if(s==null||s.isEmpty()){
            return 0;
        }


        for(int r=0;r<s.length();r++){
            Character c=s.charAt(r);
            if(mp.containsKey(c) && mp.get(c)>=l){
                l=mp.get(c)+1;
                mp.put(c,r);
            }
             mp.put(c,r);           

            // System.out.println(mp);

            maxLen=Math.max(r-l+1,maxLen);
            // System.out.println(maxLen);
        }
        return maxLen;
        
    }
}
