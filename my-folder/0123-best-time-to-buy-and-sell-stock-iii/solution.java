class Solution {
    public int maxProfit(int[] prices) {

        //I've to maximize each day the profits in both transaction
        int b1=-prices[0];
        int s1=0;
        int b2=Integer.MIN_VALUE;
        int s2=0;

        for(int price:prices){

            //max profit after first buy

            b1=Math.max(b1,-price);

            //max profit after first sell

            s1=Math.max(s1,b1+price);

            //max profit after second buy

            b2=Math.max(b2,s1-price);

            //Max profit after second sell
            s2=Math.max(s2,b2+price);

        }

        return s2;

        
    }
}
