class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        //Start and end
        //
        int start=-1;
        int n=s.length();
        int max=0;
        HashMap<Character,Integer> h=new HashMap<>();
        s.chars().forEach(c->h.put((char)c,-1));
        //System.out.println(h);
        int ms=0;


        for(int i=0;i<n;i++){
                start=Math.max(h.get(s.charAt(i)),start);
                int ws=i-start;
                ms=Math.max(ws,ms);
                h.put(s.charAt(i),i);               
        }

        return ms;        
    }
}
