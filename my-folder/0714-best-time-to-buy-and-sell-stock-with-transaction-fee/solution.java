class Solution {
    public int maxProfit(int[] prices, int fee) {

        //Do we need to maintain parallel realities, or we can go in one direction

        // I think we can simply solve this in one direction

        //But here optimization is needed as the each time transaction happens a transaction fee is charged.

        //So we need to track the state to manage parallel realities


        //Let's try this 
        /*
        Input: prices = [1,3,7,5,10,3], fee = 3
        Output: 6
        */

        //Hold -> -1 -> max(-1,-3)=-1 ->max(-1, -7)=-1 ->max(-1,3-5=-2)=-1// This is the most important step. Hold state says best profit today if we are holding a stock. Even if I made some profit yesterday by selling the stock the total profit today if I buy today is less than the profit if I bought on first day and still holding -> max(-1,3-10)=-1 ->max(-1,6-3)=3

        //Sold -> 0 -> max(0,-1+3-3)=0 ->max(0, -1+7-3=3)=3 ->  max(3,-1+5-3)=3-> max(3,-1+10-3)=6 -> max(6,-1+3-3)=6

        // The hold and sold depends on the precious day transaction and not the current day transaction

        /**
        Input: prices = [1,3,2,8,4,9], fee = 2
        Output: 8
         */

        //HOLD : -1 ->max(-1, 0-3)=-1 -> max(-1,0-2)=-1 -> max(-1,0-8)=-1 -> max(-1,5-4)=1 -> max(1,5-9)=1

        //SOLD : 0 ->max(0,-1+3-2)=0 -> max(0,-1+2-2)=0 -> max(0,-1+8-2)=5 -> max(5, -1+4-2)=5->max(5,1+9-2)=8


        //I need only the previous hold state as the hold state is changing within the transaction

        int prevHold=-prices[0];
        int hold=-prices[0];
        int sold =0;

        for(int i=1;i<prices.length;i++){
            hold= Math.max(hold,sold-prices[i]);
            sold=Math.max(sold,prevHold+prices[i]-fee);
            prevHold=hold;
            
        }
        return sold;


    }
}
