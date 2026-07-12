class Solution {
    public int maxProfit(int k, int[] prices) {

        if(k<1||prices==null||prices.length==0){
            return 0;
        }


        //If n days there can be at max n/2 transactions as we need to buy on one day and sell on the other day
        //If max allowed transaction is more than n/2 then we don't need to track the states as in that case we can buy just before the day price increases and the sell on the day price increased

        if(k>prices.length/2){
            return maxProfitUnlimited(prices);
        }
        
        //Since the profit of the current day depends on the max profit till yesterday or the last transaction we don't need 2 D array tp track the state

        //How am I gonna initialize my buy and sell states
        //I need to create the buy and sell arrays to track all my transactions
        //0th index I'll keep empty
        int[] sell=new int[k+1];
        int[] buy=new int[k+1];

        //Initialize the buy and sell array assuming that all the transactions took place on the first day
        Arrays.fill(buy,-prices[0]);

        //Iterate through the prices of all the days 
        for(int price:prices){
            //I need to iterate through all the states and update the max value
            for(int i=1;i<=k;i++){
                // buy[i]=Math.max(buy[i-1],sell[i-1]-price); This is not correct because the profit after ith buy on last day will be stored in buy[i] and not in buy[i-1]
                buy[i]=Math.max(buy[i],sell[i-1]-price);//sell[0] will always be 0 so 1st buy will always be -price 

                // I can buy only after last sell so the buy depends on the last sell
                //And also I can sell after last buy
                sell[i]=Math.max(sell[i],buy[i]+price);//sell[i] happens always after buy[i]
            }
        }

        return sell[k];

    }

    private int maxProfitUnlimited(int[] prices){

        int maxProfit=0;

        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                maxProfit+=prices[i]-prices[i-1];
            }
        }

        return maxProfit;
    }
}

// class Solution {
//     public int maxProfit(int k, int[] prices) {

//         //Each day I will change the state of each transaction

//         //What is the current days price

//         // With this price if I made k transaction what all can I change
//          //k = 2, prices = [2,4,1]
        
//         //We don't need only one array to store the states . We need two arrays  one to store the buy state and another to store the sell state
//          int [] buy=new int[k+1];

//          Arrays.fill(buy,Integer.MIN_VALUE);
//          buy[0]=0;

//          int [] sell=new int[k+1];





//         for(int p:prices){
//             //What will happen if I do all k transactions using this price
//             //  [2,4,1]
//             // System.out.println("For current price "+p);
//             for(int i=1;i<=k;i++){
//                 // System.out.println("After "+i+"th transaction");
//                 //Buying today
//                 buy[i]=Math.max(buy[i],sell[i-1]-p);
//                 // System.out.println("After buying today");
//                 // System.out.println(Arrays.toString(buy));
//                 //Buying today
//                 //Selling today
//                 sell[i]=Math.max(sell[i],buy[i]+p);
//                 // System.out.println("After selling today");
//                 // System.out.println(Arrays.toString(sell));

//             }

//         }

//         return sell[k];
//     }
// }
