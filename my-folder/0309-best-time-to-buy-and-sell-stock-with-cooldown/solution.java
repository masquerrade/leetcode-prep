//O(1) solution
class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int prevHold=-prices[0];
        int prevSold=Integer.MIN_VALUE;
        int prevRest=0;

        for(int i=1;i<n;i++){
            int hold=Math.max(prevHold,prevRest-prices[i]);
            int sold=prevHold+prices[i];
            int rest=Math.max(prevRest,prevSold);

            //Updating prev variables
            prevHold=hold;
            prevSold=sold;
            prevRest=rest;

        }

        return Math.max(prevSold,prevRest);

    }
}

// //O(N) solution
// class Solution {
//     public int maxProfit(int[] prices) {

//         //How can I think about this 
//         //Here there three hold ,sold or rest
//         int n=prices.length;
//         int[] hold= new int[n];
//         int[] sold= new int[n];
//         int[] rest= new int[n];

//         //I bought  the stock on day 0
//         hold[0]=-prices[0];
//         //I can't sell on the same day. So make it minimum so that this path is never taken
//         sold[0]=Integer.MIN_VALUE;
//         //If I do nothing on the first day
//         rest[0]=0;

        
//         //I was confused between rest and cool down
//         //If I was in rest yesterday I can easily buy a stock today
//         //Everyday I'm tracking profit for each state so I can track each possibility

//         for(int i=1;i<n;i++){
//             // Hold of today depends on previous day hold if I keep on holding today or if I was in rest yesterday and buy a new stock today to start a new hold
//             hold[i]=Math.max(hold[i-1],rest[i-1]-prices[i]);
//             // If I'm trying to sell a stock today,  I should have been in the hold state yesterday and no other state
//             sold[i]=hold[i-1]+prices[i];
//             //If I'm trying to be in rest state today then either I should have sold yesterday or I should have been in rest state yesterday
//             rest[i]=Math.max(rest[i-1],sold[i-1]);
//         }

//         return Math.max(sold[n-1],rest[n-1]);       

        
//     }
// }
