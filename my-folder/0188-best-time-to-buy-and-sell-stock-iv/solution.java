class Solution {
    public int maxProfit(int k, int[] prices) {

        //Each day I will change the state of each transaction

        //What is the current days price

        // With this price if I made k transaction what all can I change
         //k = 2, prices = [2,4,1]
        
        //We don't need only one array to store the states . We need two arrays  one to store the buy state and another to store the sell state
         int [] buy=new int[k+1];

         Arrays.fill(buy,Integer.MIN_VALUE);
         buy[0]=0;

         int [] sell=new int[k+1];





        for(int p:prices){
            //What will happen if I do all k transactions using this price
            //  [2,4,1]
            // System.out.println("For current price "+p);
            for(int i=1;i<=k;i++){
                // System.out.println("After "+i+"th transaction");
                //Buying today
                buy[i]=Math.max(buy[i],sell[i-1]-p);
                // System.out.println("After buying today");
                // System.out.println(Arrays.toString(buy));
                //Buying today
                //Selling today
                sell[i]=Math.max(sell[i],buy[i]+p);
                // System.out.println("After selling today");
                // System.out.println(Arrays.toString(sell));

            }

        }

        return sell[k];
    }
}
