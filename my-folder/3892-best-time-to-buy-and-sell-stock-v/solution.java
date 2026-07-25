
class Solution {
    public long maximumProfit(int[] prices, int k) {

        if(prices==null||prices.length==0){
            return 0;
        }

        //I need to take short sell also in consideration to maintain the profit
        //B -> S

        //Forward direction solution
        //Declaring previous array with base case 
        //All states are for k transactions
        long [] prevEmpty=new long[k+1];
        long [] prevLong=new long[k+1];
        long [] prevShort=new long[k+1];

        Arrays.fill(prevEmpty,Integer.MIN_VALUE);
        Arrays.fill(prevLong,Integer.MIN_VALUE);
        Arrays.fill(prevShort,Integer.MIN_VALUE);

        //If I do nothing on day 0
        prevEmpty[0]=0;
        //If I buy a stock on day 0
        prevLong[0]=-prices[0];
        prevShort[0]=prices[0];

        //This loop is pretty complicated 
        // //So on every day I'm calculating the max for that state.
        // //So I need to traverse through all the day
        // for(int i=1;i<prices.length;i++){

        //     //For each day I need to store the current Max profit for each number of transaction 
        //     //Max no of transaction completed till now is i/2
        //     //So we will take min of i/2 and k and iterate till there
        //     //Arrays to store the current profit for each state for each transaction
        //     //Since current arrays will carry over to the previous array we need to initialize to Integer.MIN_VALUE
        //     int [] currEmpty=new int[k+1];
        //     int [] currLong=new int[k+1];
        //     int [] currShort=new int[k+1];

        //     Arrays.fill(currEmpty,Integer.MIN_VALUE);
        //     Arrays.fill(currLong,Integer.MIN_VALUE);
        //     Arrays.fill(prevShort,Integer.MIN_VALUE);

        //     //We can't skip 0th transaction 
        //     // for(int j=1;j<Math.min(i/2,k);j++){
        //     //In short selling case there are nor exactly n/2 transactions
        //     //for(int j=0;j<Math.min(i/2,k);j++){
        //     for(int j=0;j<=k;j++){

        //         //There are two paths , one for when we do nothing and carry over the previous statret
        //         if(j==0){
        //             currLong[0]=Math.max(currLong[0],-prices[i]);
        //             currShort[0]=Math.max(currShort[0],prices[i]);
        //             continue;
        //         }

        //         //Here we need to take the number of transactions carefully
        //         currEmpty[j]=Math.max(Math.max(prevEmpty[j],prevLong[j-1]+prices[i]),prevShort[j-1]!=Integer.MIN_VALUE?prevShort[j-1]-prices[i]:Integer.MIN_VALUE));
        //         //There is no transaction for coming from empty to long or short
        //         // currLong[j]=Math.max(prevEmpty[j-1]!=Integer.MIN_VALUE?prevEmpty[j-1]-prices[i]:Integer.MIN_VALUE,prevLong[j-1]);
        //         currLong[j]=Math.max(prevEmpty[j]!=Integer.MIN_VALUE?prevEmpty[j]-prices[i]:Integer.MIN_VALUE,prevLong[j]);
        //         currShort[j]=Math.max(prevEmpty[j]+prices[i],prevShort[j]);
        //     }

        //     prevEmpty=currEmpty;
        //     prevLong=currLong;
        //     prevShort=currShort;

        // }

        //Gemini loop
        for (int i = 1; i < prices.length; i++) {
            long[] currEmpty = new long[k + 1];
            long[] currLong = new long[k + 1];
            long[] currShort = new long[k + 1];

            // Protect against default 0s
            Arrays.fill(currEmpty, Integer.MIN_VALUE);
            Arrays.fill(currLong, Integer.MIN_VALUE);
            Arrays.fill(currShort, Integer.MIN_VALUE); // Fixed the typo here!

            for (int j = 0; j <= k; j++) {
                // --- PATH A: Stays on the same transaction count (j) ---
                // 1. "Do nothing" (carry over yesterday's state)
                currEmpty[j] = prevEmpty[j];
                currLong[j] = prevLong[j];
                currShort[j] = prevShort[j];

                // 2. "Open a position" (Empty -> Long or Short)
                if (prevEmpty[j] != Integer.MIN_VALUE) {
                    currLong[j] = Math.max(currLong[j], prevEmpty[j] - prices[i]);
                    currShort[j] = Math.max(currShort[j], prevEmpty[j] + prices[i]);
                }

                // --- PATH B: Completes a transaction (requires j > 0) ---
                if (j > 0) {
                    // "Close a position" (Long/Short -> Empty)
                    if (prevLong[j - 1] != Integer.MIN_VALUE) {
                        currEmpty[j] = Math.max(currEmpty[j], prevLong[j - 1] + prices[i]);
                    }
                    if (prevShort[j - 1] != Integer.MIN_VALUE) {
                        currEmpty[j] = Math.max(currEmpty[j], prevShort[j - 1] - prices[i]);
                    }
                }
            }

            // Get ready for tomorrow
            prevEmpty = currEmpty;
            prevLong = currLong;
            prevShort = currShort;
        }

        long maxProfit=prevEmpty[0];
        for(long profit:prevEmpty){
            maxProfit=Math.max(maxProfit,profit);
        }

        return maxProfit;
        
    }
}
