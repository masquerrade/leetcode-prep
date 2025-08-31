class Solution {
    public int characterReplacement(String s, int k) {
       int maxD=0;
       int start=0; 
       Map<Character,Integer> cc=new HashMap<>();
       int maxC=0;
       int maxL=0;


        for(int e=0;e<s.length();e++){

            cc.compute(s.charAt(e),(k1,v)->(v==null)?1:v+1);
            maxC=Math.max(maxC,cc.get(s.charAt(e)));
            maxD=e-start+1-maxC;

            //It maintains the maximum length substring
            if(maxD>k){
                char ch=s.charAt(start);
                cc.computeIfPresent(ch,(k1,v)->v-1);
                start++;
            }
            maxL=Math.max((e-start+1),maxL);
        }

        return maxL;
        
    }
}
