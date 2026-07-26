//Backward traversal method
class Solution {
    public long maximumProfit(int[] prices, int k) {
        
        //2D array to track the the futurre profit for each day
        if(prices==null || prices.length==0){
            return 0;
        }

        //Array which stores future profit 
        // int[][] dp=new int[k+1][3]; Profit can exceed integer limit
        long [][] dp=new long[k+1][3];

        //Current scratchpad which becomes the future profit array
        long[][] nextDp=new long[k+1][3];

        int n=prices.length;

        //Let's initialize the backward dp array for imaginary day n
        for(int i=0;i<=k;i++){
            //THis means on a future n+1th day when the markets closed we can't make any more profit
            dp[i][0]=0;
            dp[i][1]=Integer.MIN_VALUE;
            dp[i][2]=Integer.MIN_VALUE;
        }

        //Now I need to traverse backward and keep adding profit for each day by increasing the number of transactions left

        for(int i=n-1; i>=0; i--){

            //Do I need to iterate through all the transactions for each day
            //Yes absolutely
            for(int j=0; j<=k; j++){
                //On n-1 day I've to iterate through what if 0 transactions remaining to all k transactions remaining

                //What I need to calculate is nextDp[j] for each state

                //Today I'm in neutral state so what could have been my maximum profit using profit from all states from tomorrow
                //I can open long from today
                long openLong=dp[j][1]-prices[i];
                long openShort=dp[j][2]+prices[i];
                long stayNeutral=dp[j][0];
                //So I'm deciding the max profit I can make by being in neutral state today by using all the states I can be tomorrow and choosing the max out of them
                nextDp[j][0]=Math.max(Math.max(openLong,openShort),stayNeutral);

                //Today I want to be in long state
                //Then for tomorrow either I can close the long state or stay on the long state for tomorrow also
                //A trade can be closed only when j>0 , when j==0, I need to make it to a minimum  value
                long closeLong=Integer.MIN_VALUE;
                if(j>0){
                    closeLong=dp[j-1][0]+prices[i];
                }
                long holdLong=dp[j][1];
                nextDp[j][1]=Math.max(closeLong,holdLong);

                //Today I want to be in short state
                //Then for tomorrow I can either hold the short state or close the short state
                long closeShort=Integer.MIN_VALUE;
                if(j>0){
                    closeShort=dp[j-1][0]-prices[i];
                }
                long holdShort=dp[j][2];
                nextDp[j][2]=Math.max(closeShort,holdShort);


            }
            //For the next iteration I need to assign nextDP to dp
            //I want to copy the value of nextDp to dp and not make both of them same
            //Instead of copying each value I can simply swap them in each iteration so that they can keep pointing to the different arrays

            long[][] temp=dp;
            dp=nextDp;
            nextDp=temp;

        }

        //Now I want to return the dp entry where profit for the closed state is stored which stores the max future profit when we can make k transactions

        return dp[k][0];

    }
}


// //Gemini forward traversal
// class Solution {
//     public long maximumProfit(int[] prices, int k) {
//         // Base case: if there are no prices, we can't make any profit
//         if (prices == null || prices.length == 0) {
//             return 0;
//         }

//         // Initialize our 1D previous state arrays
//         long[] prevEmpty = new long[k + 1];
//         long[] prevLong = new long[k + 1];
//         long[] prevShort = new long[k + 1];

//         // Fill with a deeply negative value to represent impossible states
//         Arrays.fill(prevEmpty, Integer.MIN_VALUE);
//         Arrays.fill(prevLong, Integer.MIN_VALUE);
//         Arrays.fill(prevShort, Integer.MIN_VALUE);

//         // Day 0 valid starting states
//         prevEmpty[0] = 0;
//         prevLong[0] = -prices[0];
//         prevShort[0] = prices[0];

//         // Outer loop: iterate through each day
//         for (int i = 1; i < prices.length; i++) {
//             // Temporary arrays for the current day's calculations
//             long[] currEmpty = new long[k + 1];
//             long[] currLong = new long[k + 1];
//             long[] currShort = new long[k + 1];

//             Arrays.fill(currEmpty, Integer.MIN_VALUE);
//             Arrays.fill(currLong, Integer.MIN_VALUE);
//             Arrays.fill(currShort, Integer.MIN_VALUE); 

//             // Inner loop: iterate through all possible transaction counts
//             for (int j = 0; j <= k; j++) {
                
//                 // --- PATH A: Stays on the same transaction count (j) ---
//                 // 1. "Do nothing" (carry over yesterday's state)
//                 currEmpty[j] = prevEmpty[j];
//                 currLong[j] = prevLong[j];
//                 currShort[j] = prevShort[j];

//                 // 2. "Open a position" (Empty -> Long or Short)
//                 if (prevEmpty[j] != Integer.MIN_VALUE) {
//                     currLong[j] = Math.max(currLong[j], prevEmpty[j] - prices[i]);
//                     currShort[j] = Math.max(currShort[j], prevEmpty[j] + prices[i]);
//                 }

//                 // --- PATH B: Completes a transaction (requires j > 0) ---
//                 if (j > 0) {
//                     // "Close a position" (Long/Short -> Empty)
//                     if (prevLong[j - 1] != Integer.MIN_VALUE) {
//                         currEmpty[j] = Math.max(currEmpty[j], prevLong[j - 1] + prices[i]);
//                     }
//                     if (prevShort[j - 1] != Integer.MIN_VALUE) {
//                         currEmpty[j] = Math.max(currEmpty[j], prevShort[j - 1] - prices[i]);
//                     }
//                 }
//             }

//             // Move current day's data to previous for tomorrow's loop
//             prevEmpty = currEmpty;
//             prevLong = currLong;
//             prevShort = currShort;
//         }

//         // Find the absolute maximum profit among all possible transaction counts
//         long maxProfit = prevEmpty[0];
//         for (long profit : prevEmpty) {
//             maxProfit = Math.max(maxProfit, profit);
//         }

//         return maxProfit;
//     }
// }


// class Solution {
//     public long maximumProfit(int[] prices, int k) {

//         if(prices==null||prices.length==0){
//             return 0;
//         }

//         //I need to take short sell also in consideration to maintain the profit
//         //B -> S

//         //Forward direction solution
//         //Declaring previous array with base case 
//         //All states are for k transactions
//         long [] prevEmpty=new long[k+1];
//         long [] prevLong=new long[k+1];
//         long [] prevShort=new long[k+1];

//         Arrays.fill(prevEmpty,Integer.MIN_VALUE);
//         Arrays.fill(prevLong,Integer.MIN_VALUE);
//         Arrays.fill(prevShort,Integer.MIN_VALUE);

//         //If I do nothing on day 0
//         prevEmpty[0]=0;
//         //If I buy a stock on day 0
//         prevLong[0]=-prices[0];
//         prevShort[0]=prices[0];

//         //This loop is pretty complicated 
//         // //So on every day I'm calculating the max for that state.
//         // //So I need to traverse through all the day
//         // for(int i=1;i<prices.length;i++){

//         //     //For each day I need to store the current Max profit for each number of transaction 
//         //     //Max no of transaction completed till now is i/2
//         //     //So we will take min of i/2 and k and iterate till there
//         //     //Arrays to store the current profit for each state for each transaction
//         //     //Since current arrays will carry over to the previous array we need to initialize to Integer.MIN_VALUE
//         //     int [] currEmpty=new int[k+1];
//         //     int [] currLong=new int[k+1];
//         //     int [] currShort=new int[k+1];

//         //     Arrays.fill(currEmpty,Integer.MIN_VALUE);
//         //     Arrays.fill(currLong,Integer.MIN_VALUE);
//         //     Arrays.fill(prevShort,Integer.MIN_VALUE);

//         //     //We can't skip 0th transaction 
//         //     // for(int j=1;j<Math.min(i/2,k);j++){
//         //     //In short selling case there are nor exactly n/2 transactions
//         //     //for(int j=0;j<Math.min(i/2,k);j++){
//         //     for(int j=0;j<=k;j++){

//         //         //There are two paths , one for when we do nothing and carry over the previous statret
//         //         if(j==0){
//         //             currLong[0]=Math.max(currLong[0],-prices[i]);
//         //             currShort[0]=Math.max(currShort[0],prices[i]);
//         //             continue;
//         //         }

//         //         //Here we need to take the number of transactions carefully
//         //         currEmpty[j]=Math.max(Math.max(prevEmpty[j],prevLong[j-1]+prices[i]),prevShort[j-1]!=Integer.MIN_VALUE?prevShort[j-1]-prices[i]:Integer.MIN_VALUE));
//         //         //There is no transaction for coming from empty to long or short
//         //         // currLong[j]=Math.max(prevEmpty[j-1]!=Integer.MIN_VALUE?prevEmpty[j-1]-prices[i]:Integer.MIN_VALUE,prevLong[j-1]);
//         //         currLong[j]=Math.max(prevEmpty[j]!=Integer.MIN_VALUE?prevEmpty[j]-prices[i]:Integer.MIN_VALUE,prevLong[j]);
//         //         currShort[j]=Math.max(prevEmpty[j]+prices[i],prevShort[j]);
//         //     }

//         //     prevEmpty=currEmpty;
//         //     prevLong=currLong;
//         //     prevShort=currShort;

//         // }

//         //Gemini loop
//         for (int i = 1; i < prices.length; i++) {
//             long[] currEmpty = new long[k + 1];
//             long[] currLong = new long[k + 1];
//             long[] currShort = new long[k + 1];

//             // Protect against default 0s
//             Arrays.fill(currEmpty, Integer.MIN_VALUE);
//             Arrays.fill(currLong, Integer.MIN_VALUE);
//             Arrays.fill(currShort, Integer.MIN_VALUE); // Fixed the typo here!

//             for (int j = 0; j <= k; j++) {
//                 // --- PATH A: Stays on the same transaction count (j) ---
//                 // 1. "Do nothing" (carry over yesterday's state)
//                 currEmpty[j] = prevEmpty[j];
//                 currLong[j] = prevLong[j];
//                 currShort[j] = prevShort[j];

//                 // 2. "Open a position" (Empty -> Long or Short)
//                 if (prevEmpty[j] != Integer.MIN_VALUE) {
//                     currLong[j] = Math.max(currLong[j], prevEmpty[j] - prices[i]);
//                     currShort[j] = Math.max(currShort[j], prevEmpty[j] + prices[i]);
//                 }

//                 // --- PATH B: Completes a transaction (requires j > 0) ---
//                 if (j > 0) {
//                     // "Close a position" (Long/Short -> Empty)
//                     if (prevLong[j - 1] != Integer.MIN_VALUE) {
//                         currEmpty[j] = Math.max(currEmpty[j], prevLong[j - 1] + prices[i]);
//                     }
//                     if (prevShort[j - 1] != Integer.MIN_VALUE) {
//                         currEmpty[j] = Math.max(currEmpty[j], prevShort[j - 1] - prices[i]);
//                     }
//                 }
//             }

//             // Get ready for tomorrow
//             prevEmpty = currEmpty;
//             prevLong = currLong;
//             prevShort = currShort;
//         }

//         long maxProfit=prevEmpty[0];
//         for(long profit:prevEmpty){
//             maxProfit=Math.max(maxProfit,profit);
//         }

//         return maxProfit;
        
//     }
// }
