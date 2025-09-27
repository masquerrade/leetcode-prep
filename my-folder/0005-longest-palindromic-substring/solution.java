class Solution {
    public String longestPalindrome(String s) {

        if(s.length()==1){
            return s;
        }

        
        int r=0;
        int l=0;

        for(int i=0;i<s.length();i++){
            int currLen1=findPalinLen(i,i,s);
            int currLen2=findPalinLen(i,i+1,s);

            //Here I'm getting the length of the current palindrome . 
            int len=Math.max(currLen1,currLen2);
            
            // If the current lenght is greater than the length of the current window then only update the window
            //odd -> 1,2,3 l= 2-(3-1)/2=1    r= 2+(3/2)=3
            if(len>(r-l+1)){
                l=i-((len-1)/2);
                r=i+(len/2);                
            }
        }

        return s.substring(l,r+1);
        
    }

    public int findPalinLen(int l,int r,String s){
        //caba
        //2,2
        //1,3
        //0,4
        while(l>=0 && r<s.length() &&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }

        return r-l-1;

    }
}
