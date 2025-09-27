class Solution {
    public int maxProfit(int[] prices) {
        //So if I have to solve this problem then what i wilk have to do lets see
        //So I will maintain two variables in this case to maintain the current state one for transaction 1 and the other for transaction 2
        int b1=Integer.MIN_VALUE;
        int b2=Integer.MIN_VALUE;
        int s1=0;
        int s2=0;

        for(int p:prices){
            b1=Math.max(b1,-p);
            s1=Math.max(s1,b1+p);
            b2=Math.max(b2,s1-p);
            s2=Math.max(s2,b2+p);
        }

        return s2;
            
    }
}
